package com.basis.campina.xtarefas.servico.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

@Setter
@Getter
public class AnexoFiltro extends DefaultFilter implements Serializable, BasicFilter {
    private static final long serialVersionUID = -5386196010361699745L;

    private String file;
    private String filename;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "file", "filename");

        return  queryBuilder;
    }

}
