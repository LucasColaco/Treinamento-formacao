package com.basis.campina.xtarefas.repositorio.elasticsearch;

import com.basis.campina.xtarefas.dominio.document.TarefaDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface TarefaSearchRepository extends ElasticsearchRepository<TarefaDocument, Integer> {
}
