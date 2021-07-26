package com.basis.campina.xtarefas.servico.mapper;

import com.basis.campina.xtarefas.dominio.Anexo;
import com.basis.campina.xtarefas.servico.dto.AnexoDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AnexoMapper extends EntityMapper<AnexoDTO, Anexo> {

    @Override
    @Mapping(source = "tarefaId", target = "tarefa.id")
    Anexo toEntity(AnexoDTO anexoDTO);

    @Override
    @Mapping(source = "tarefa.id", target = "tarefaId")
    AnexoDTO toDto(Anexo anexo);
}
