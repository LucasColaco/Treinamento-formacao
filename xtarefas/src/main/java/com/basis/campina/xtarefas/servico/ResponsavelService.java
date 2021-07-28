package com.basis.campina.xtarefas.servico;

import com.basis.campina.xtarefas.dominio.Responsavel;
import com.basis.campina.xtarefas.repositorio.ResponsavelRepository;
import com.basis.campina.xtarefas.repositorio.elasticsearch.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.servico.event.ResponsavelEvent;
import com.basis.campina.xtarefas.servico.mapper.ResponsavelMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ResponsavelService {

    private final ResponsavelRepository responsavelRepository;
    private final ResponsavelMapper responsavelMapper;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final ResponsavelSearchRepository responsavelSearchRepository;

    public List<ResponsavelDTO> buscar(){
        return responsavelRepository.findAll().stream().map(responsavelMapper::toDto).collect(Collectors.toList());
    }

    public ResponsavelDTO buscarPorId(Integer id){
        Responsavel responsavel = responsavelRepository.findById(id).orElseThrow(()->new RuntimeException("Responsável não encontrado"));
        return responsavelMapper.toDto(responsavel);
    }

    public ResponsavelDTO salvar(ResponsavelDTO responsavelDTO){
        Responsavel obj = responsavelMapper.toEntity(responsavelDTO);
        obj = responsavelRepository.save(obj);
        applicationEventPublisher.publishEvent(new ResponsavelEvent(obj.getId()));
        return responsavelMapper.toDto(obj);
    }

    public void remover(Integer id){
        buscarPorId(id);
        responsavelRepository.deleteById(id);
    }
}
