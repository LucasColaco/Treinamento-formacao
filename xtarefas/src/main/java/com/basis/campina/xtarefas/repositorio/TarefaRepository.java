package com.basis.campina.xtarefas.repositorio;

import com.basis.campina.xtarefas.dominio.Tarefa;
import com.basis.campina.xtarefas.dominio.document.TarefaDocument;
import feign.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query("SELECT NEW com.basis.campina.xtarefas.dominio.document.TarefaDocument(t.id, t.nome, t.dtInicio, t.dtConclusao, t.status, t.responsavel.nome)"
            + " from Tarefa t where t.id = :id")
    TarefaDocument getDocument(@Param("id") Integer id);
}
