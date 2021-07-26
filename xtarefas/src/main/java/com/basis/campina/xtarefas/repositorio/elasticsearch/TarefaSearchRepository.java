package com.basis.campina.xtarefas.repositorio.elasticsearch;

import com.basis.campina.xtarefas.dominio.document.TarefaDocument;

public interface TarefaSearchRepository extends ElasticEntity<TarefaDocument, Integer> {

    default Class<TarefaDocument> getEntityClass(){
        return TarefaDocument.class;
    }
}
