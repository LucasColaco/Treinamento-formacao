package com.basis.campina.xtarefas.servico.filter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;

@Getter
@Setter
public class ResponsavelFilter extends DefaultFilter implements Serializable, BasicFilter {
    private static final long serialVersionUID = -3407815841110168053L;

    private String nome;
    private String email;
    private LocalDate dtNascimento;
    private String tarefas;

    @Override
    public BoolQueryBuilder getFilter() {
        BoolQueryBuilder queryBuilder = QueryBuilders.boolQuery();

        List<String> fields = new ArrayList<>();
        filterFields(fields, query, queryBuilder, "nome", "email", "tarefas");
        addShouldTermQuery(queryBuilder, "dataNasc", query);

        return  queryBuilder;
    }
}
