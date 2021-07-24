package com.basis.campina.xtarefas.repositorio;

import com.basis.campina.xtarefas.dominio.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Integer> {
}
