package com.basis.campina.xtarefas.servico;

import com.basis.campina.xtarefas.dominio.Tarefa;
import com.basis.campina.xtarefas.repositorio.TarefaRepository;
import com.basis.campina.xtarefas.servico.dto.TarefaDTO;
import com.basis.campina.xtarefas.servico.event.TarefaEvent;
import com.basis.campina.xtarefas.servico.mapper.TarefaMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final TarefaMapper tarefaMapper;
    private final ApplicationEventPublisher applicationEventPublisher;

    public List<TarefaDTO> buscar(){
        return tarefaRepository.findAll().stream().map(tarefaMapper::toDto).collect(Collectors.toList());
    }

    public TarefaDTO buscarPorId(Integer id){
        Tarefa tarefa = tarefaRepository.findById(id).orElseThrow(()->new RuntimeException("Tarefa não encontrada"));
        return tarefaMapper.toDto(tarefa);
    }

    public TarefaDTO salvar(TarefaDTO tarefaDTO){
        Tarefa obj = tarefaMapper.toEntity(tarefaDTO);
        obj = tarefaRepository.save(obj);
        applicationEventPublisher.publishEvent(new TarefaEvent(obj.getId()));
        return tarefaMapper.toDto(obj);
    }

    public void remover(Integer id){
        buscarPorId(id);
        tarefaRepository.deleteById(id);
    }
}
