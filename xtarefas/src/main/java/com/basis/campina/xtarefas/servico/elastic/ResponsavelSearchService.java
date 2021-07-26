package com.basis.campina.xtarefas.servico.elastic;


import com.basis.campina.xtarefas.dominio.document.ResponsavelDocument;
import com.basis.campina.xtarefas.repositorio.ResponsavelRepository;
import com.basis.campina.xtarefas.repositorio.TarefaRepository;
import com.basis.campina.xtarefas.repositorio.elasticsearch.Reindexer;
import com.basis.campina.xtarefas.repositorio.elasticsearch.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.servico.dto.DominioFixoDTO;
import com.basis.campina.xtarefas.servico.event.ResponsavelEvent;
import com.basis.campina.xtarefas.servico.filter.ResponsavelFilter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ResponsavelSearchService implements Reindexer {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelSearchRepository responsavelSearchRepository;

    private final TarefaRepository tarefaRepository;

    public Page<ResponsavelDocument> search(ResponsavelFilter filtro, Pageable pageable) {
        return responsavelSearchRepository.search(filtro.getFilter(), pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    @Transactional(readOnly = true)
    public void indexar(ResponsavelEvent event) {
        log.info("[XTAREFAS] Indexando Responsavel: {}", event.getId());
        ResponsavelDocument document = responsavelRepository.getDocument(event.getId());
        processarResponsavelDocument(document, event.getId());
        responsavelSearchRepository.save(document);
    }

    private void processarResponsavelDocument(ResponsavelDocument document, Integer id){
        List<DominioFixoDTO> nomeTarefas = tarefaRepository.buscarNomesTarefas(id);
        document.setTarefas(Objects.isNull(nomeTarefas) ? "" : nomeTarefas.stream().map(DominioFixoDTO::getLabel).collect(Collectors.joining(", ")));
    }

    @Override
    public String getEntity() {
        return "responsaveis";
    }

    @Override
    public Page<ResponsavelDocument> reindexPage(Pageable pageable) throws IllegalAccessException {
        Page<ResponsavelDocument> documentsPage = responsavelRepository.reindexPage(pageable);
        documentsPage.getContent().forEach(document -> {
            processarResponsavelDocument(document, document.getId());
        });
        return Reindexer.super.reindexPage(documentsPage.getPageable());
    }
}
