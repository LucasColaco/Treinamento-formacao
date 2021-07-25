package com.basis.campina.xtarefas.repositorio;

import com.basis.campina.xtarefas.dominio.Responsavel;
import com.basis.campina.xtarefas.dominio.elasticsearch.ResponsavelDocument;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {

    @Query("SELECT NEW com.basis.campina.xtarefas.dominio.elasticsearch.ResponsavelDocument(r.id, r.nome, r.email, r.dtNascimento)"
            + " from Responsavel r where r.id = :id")
    ResponsavelDocument getDocument(@Param("id") Integer id);
}
