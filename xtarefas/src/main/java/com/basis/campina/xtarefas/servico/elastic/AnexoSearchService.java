package com.basis.campina.xtarefas.servico.elastic;

import com.basis.campina.xtarefas.dominio.document.AnexoDocument;
import com.basis.campina.xtarefas.repositorio.AnexoRepository;
import com.basis.campina.xtarefas.repositorio.elasticsearch.AnexoSearchRepository;
import com.basis.campina.xtarefas.servico.event.AnexoEvent;
import com.basis.campina.xtarefas.servico.filter.AnexoFiltro;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class AnexoSearchService {

    private final AnexoRepository anexoRepository;
    private final AnexoSearchRepository anexoSearchRepository;

    public Page<AnexoDocument> search(AnexoFiltro filtro, Pageable pageable) {
        return anexoSearchRepository.search(filtro.getFilter(), pageable);
    }

    @TransactionalEventListener(fallbackExecution = true)
    @Transactional(readOnly = true)
    public void indexar(AnexoEvent event) {
        log.info("[XTAREFAS] Indexando anexo: {}", event.getId());
        AnexoDocument document = anexoRepository.getDocument(event.getId());
        anexoSearchRepository.save(document);
    }
}
