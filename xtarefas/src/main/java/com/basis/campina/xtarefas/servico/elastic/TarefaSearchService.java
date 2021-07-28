package com.basis.campina.xtarefas.servico.elastic;


import com.basis.campina.xtarefas.dominio.document.TarefaDocument;
import com.basis.campina.xtarefas.repositorio.TarefaRepository;
import com.basis.campina.xtarefas.repositorio.elasticsearch.TarefaSearchRepository;
import com.basis.campina.xtarefas.servico.event.TarefaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TarefaSearchService {

    private final TarefaRepository tarefaRepository;
    private final TarefaSearchRepository tarefaSearchRepository;

    @TransactionalEventListener(fallbackExecution = true)
    public void reindex(TarefaEvent event) {
        log.info("Iniciando Indexação de tarefa {}", event.getId());
        TarefaDocument tarefaDocument = tarefaRepository.getDocument(event.getId());
        tarefaSearchRepository.save(tarefaDocument);
    }
}
