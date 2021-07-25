package com.basis.campina.xtarefas.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_TAREFA")
public class Tarefa implements Serializable {
    private static final long serialVersionUID = -9193471228829562574L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESPONSAVEL")
    @SequenceGenerator(name = "SEQ_RESPONSAVEL", sequenceName = "SEQ_RESPONSAVEL", allocationSize = 1)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "DT_INICIO")
    private LocalDate dtInicio;

    @Column(name = "DT_CONCLUSAO")
    private LocalDate dtConclusao;

    @Column(name = "STATUS")
    private String status;

    @ManyToOne(fetch = FetchType.LAZY, targetEntity = Responsavel.class)
    private Responsavel responsavel;
}
