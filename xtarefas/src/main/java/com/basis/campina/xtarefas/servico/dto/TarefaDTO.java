package com.basis.campina.xtarefas.servico.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TarefaDTO implements Serializable {
    private static final long serialVersionUID = 7903389797228217841L;

    private Integer id;
    private String nome;
    private LocalDate dtInicio;
    private LocalDate dtConclusao;
    private String status;
    private Integer responsavel;
}