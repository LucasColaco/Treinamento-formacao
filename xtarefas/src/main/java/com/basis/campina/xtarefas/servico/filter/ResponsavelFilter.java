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
public class ResponsavelFilter extends DefaultFilter implements BaseFilter, Serializable {
    private static final long serialVersionUID = -3415069618442377905L;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();
        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder,"nome","email");
        addShouldTermQuery(queryBuilder, "dtNascimento", query);
        return queryBuilder;
    }
}
