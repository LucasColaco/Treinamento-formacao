package com.basis.campina.xtarefas.servico.dto;

import com.sun.istack.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ResponsavelDTO implements Serializable {
    private static final long serialVersionUID = 7903389797228217841L;

    private Integer id;

    @NotNull
    private String nome;

    private String email;

    private LocalDate dtNascimento;
}
