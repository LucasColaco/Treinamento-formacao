package com.basis.campina.xtarefas.repositorio.elasticsearch;

import com.basis.campina.xtarefas.dominio.document.AnexoDocument;

public interface AnexoSearchRepository extends ElasticEntity<AnexoDocument, Integer> {

    default Class<AnexoDocument> getEntityClass(){
        return AnexoDocument.class;
    }

}
