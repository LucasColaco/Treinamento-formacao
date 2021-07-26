package com.basis.campina.xtarefas.repositorio;

import com.basis.campina.xtarefas.dominio.Responsavel;
import com.basis.campina.xtarefas.dominio.document.ResponsavelDocument;
import feign.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

    @Query("SELECT NEW com.basis.campina.xtarefas.dominio.document.ResponsavelDocument(r.id, r.nome, r.email, r.dtNascimento)"
            + " from Responsavel r where r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Integer id);

    @Query("SELECT NEW com.basis.campina.xtarefas.dominio.document.ResponsavelDocument(r.id, r.nome, r.email, r.dtNascimento)"
            + " from Responsavel r order by r.id")
    Page<ResponsavelDocument> reindexPage(Pageable pageable);
}
