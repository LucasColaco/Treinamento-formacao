package com.basis.campina.xtarefas.repositorio.elasticsearch;

import com.basis.campina.xtarefas.dominio.document.ResponsavelDocument;

public interface ResponsavelSearchRepository extends ElasticEntity<ResponsavelDocument, Integer> {

    default Class<ResponsavelDocument> getEntityClass(){
        return ResponsavelDocument.class;
    }
}
