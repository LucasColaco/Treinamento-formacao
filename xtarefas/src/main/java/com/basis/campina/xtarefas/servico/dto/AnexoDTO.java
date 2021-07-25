package com.basis.campina.xtarefas.servico.dto;

import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AnexoDTO implements Serializable {
    private static final long serialVersionUID = 7903389797228217841L;

    private Integer id;
    private String file;
    private String fileName;
    private Integer tarefaId;
    private DocumentoDTO documentoDTO;
}