package com.basis.campina.xtarefas.repositorio.elasticsearch;

import com.basis.campina.xtarefas.dominio.elasticsearch.ResponsavelDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ResponsavelSearchRepository extends ElasticsearchRepository<ResponsavelDocument, Integer> {
}
