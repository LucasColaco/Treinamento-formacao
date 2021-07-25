package com.basis.campina.xtarefas.repositorio;

import com.basis.campina.xtarefas.dominio.Anexo;
import com.basis.campina.xtarefas.dominio.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnexoRepository extends JpaRepository<Anexo, Integer> {
}
