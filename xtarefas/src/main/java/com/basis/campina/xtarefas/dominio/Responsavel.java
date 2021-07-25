package com.basis.campina.xtarefas.dominio;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "TB_RESPONSAVEL")
public class Responsavel implements Serializable {
    private static final long serialVersionUID = -6769149339650436377L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RESPONSAVEL")
    @SequenceGenerator(name = "SEQ_RESPONSAVEL", sequenceName = "SEQ_RESPONSAVEL", allocationSize = 1)
    private Integer id;

    @Column(name = "NOME")
    private String nome;

    @Column(name = "Email")
    private String email;

    @Column(name = "DT_NASCIMENTO")
    private LocalDate dtNascimento;
}
