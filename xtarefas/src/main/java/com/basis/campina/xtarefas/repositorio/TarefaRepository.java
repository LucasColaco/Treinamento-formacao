package com.basis.campina.xtarefas.repositorio;

import com.basis.campina.xtarefas.dominio.Tarefa;
import com.basis.campina.xtarefas.dominio.document.TarefaDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query("SELECT NEW com.basis.campina.xtarefas.dominio.document.TarefaDocument(t.id, t.nome, t.dtInicio, t.dtConclusao, t.status, t.responsavel.nome)"
            + " from Tarefa t where t.id = :id")
    TarefaDocument getDocument(@Param("id") Integer id);

    @Query(value = "SELECT t.nome FROM Tarefa t WHERE t.responsavel.id = :id")
    List<String> buscarNomesTarefas(@Param("id") Integer id);

    @Query(value="SELECT new com.basis.campina.xtarefas.dominio.document.TarefaDocument(t.id,t.nome,t.dtInicio,t.dtConclusao, t.status, t.responsavel.nome) FROM Tarefa t")
    Page<TarefaDocument> reindexPage(Pageable pageable);

}
