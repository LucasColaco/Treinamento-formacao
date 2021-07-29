package com.basis.campina.xtarefas.servico.mapper;

import com.basis.campina.xtarefas.dominio.Tarefa;
import com.basis.campina.xtarefas.servico.dto.TarefaDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {AnexoMapper.class})
public interface TarefaMapper extends EntityMapper<TarefaDTO, Tarefa> {

    @Override
    @Mapping(source = "responsavel", target = "responsavel.id")
    Tarefa toEntity(TarefaDTO tarefaDTO);

    @Override
    @Mapping(source = "responsavel.id", target = "responsavel")
    TarefaDTO toDto(Tarefa tarefa);

//    @AfterMapping
//    default void converterListaParaEntity(@MappingTarget Tarefa tarefa){
//        tarefa.getAnexos().forEach(anexo -> anexo.setTarefa(tarefa));
//    }
}
