package com.basis.campina.xtarefas.servico.mapper;

import com.basis.campina.xtarefas.dominio.Responsavel;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ResponsavelMapper extends EntityMapper<ResponsavelDTO, Responsavel> {
}
