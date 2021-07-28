package com.basis.campina.xtarefas.servico.elastic;


import com.basis.campina.xtarefas.dominio.document.ResponsavelDocument;
import com.basis.campina.xtarefas.repositorio.ResponsavelRepository;
import com.basis.campina.xtarefas.repositorio.elasticsearch.Reindexer;
import com.basis.campina.xtarefas.repositorio.elasticsearch.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.servico.event.ResponsavelEvent;
import com.basis.campina.xtarefas.servico.filter.ResponsavelFilter;
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
public class ResponsavelSearchService  {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelSearchRepository responsavelSearchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(ResponsavelEvent event) {
        log.info("Iniciando Indexação de responsavel {}", event.getId());
        ResponsavelDocument responsavelDocument = responsavelRepository.getDocument(event.getId());
        responsavelSearchRepository.save(responsavelDocument);
    }

    public Page<ResponsavelDocument> search(ResponsavelFilter filtro, Pageable pageable) {
        return responsavelSearchRepository.search(filtro.getFilter(), pageable);
    }
}
