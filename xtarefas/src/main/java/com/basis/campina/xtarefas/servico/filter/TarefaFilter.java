package com.basis.campina.xtarefas.servico.filter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

@Getter
@Setter
public class TarefaFilter extends DefaultFilter implements Serializable, BaseFilter {

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder,"nome");
        addShouldTermQuery(queryBuilder, "dtInicio", query);
        addShouldTermQuery(queryBuilder, "dtConclusao", query);
        return queryBuilder;
    }

}
