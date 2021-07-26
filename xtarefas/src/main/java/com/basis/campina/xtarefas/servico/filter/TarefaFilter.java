package com.basis.campina.xtarefas.servico.filter;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import org.elasticsearch.index.query.BoolQueryBuilder;

@Getter
@Setter
public class TarefaFilter implements Serializable, BasicFilter {

    private Integer id;
    private String nome;
    private LocalDate dtInicio;
    private LocalDate dtConclusao;
    private String status;
    private String responsavel;
    private String anexos;

    @Override
    public BoolQueryBuilder getFilter() {
        return null;
    }
}
