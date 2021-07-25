package com.basis.campina.xtarefas.dominio;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
public class Tarefa {

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

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinColumn(name = "TAREFA_ID")
//    private List<Anexo> anexos;
}
