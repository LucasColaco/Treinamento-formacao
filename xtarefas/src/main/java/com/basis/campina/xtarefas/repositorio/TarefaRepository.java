package com.basis.campina.xtarefas.repositorio;

import com.basis.campina.xtarefas.dominio.Tarefa;
import com.basis.campina.xtarefas.dominio.document.TarefaDocument;
import com.basis.campina.xtarefas.servico.dto.DominioFixoDTO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefaRepository extends JpaRepository<Tarefa, Integer> {

    @Query("SELECT NEW com.basis.campina.xtarefas.dominio.document.TarefaDocument(t.id, t.nome, t.dtInicio, t.dtConclusao, t.status, t.responsavel.nome)"
            + " from Tarefa t where t.id = :id")
    TarefaDocument getDocument(@Param("id") Integer id);

    @Query(value = "SELECT NEW com.basis.campina.xtarefas.servico.dto.DominioFixoDTO(t.id,t.nome) FROM Tarefa t WHERE t.responsavel.id = :id")
    List<DominioFixoDTO> buscarNomesTarefas(@Param("id") Integer id);
}
