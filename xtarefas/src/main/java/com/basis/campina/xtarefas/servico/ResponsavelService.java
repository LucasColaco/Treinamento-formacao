package com.basis.campina.xtarefas.servico;

import com.basis.campina.xtarefas.dominio.Responsavel;
import com.basis.campina.xtarefas.repositorio.ResponsavelRepository;
import com.basis.campina.xtarefas.repositorio.elasticsearch.ResponsavelSearchRepository;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.servico.event.ResponsavelEvent;
import com.basis.campina.xtarefas.servico.exception.ParametrizedMessageException;
import com.basis.campina.xtarefas.servico.mapper.ResponsavelMapper;
import com.basis.campina.xtarefas.servico.util.ConstantsUtil;
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

    public void alterar(ResponsavelDTO responsavelDTO){
        consultarExistencia(responsavelDTO.getId());
        salvar(responsavelDTO);
    }

    private void consultarExistencia(Integer id) {
        if(!responsavelRepository.existsById(id)){
            throw new ParametrizedMessageException(ConstantsUtil.RESPONSAVEL_NAO_ENCONTRADO, ConstantsUtil.ERROR_TITLE);
        }
    }

    public void remover(Integer id){
        buscarPorId(id);
        responsavelRepository.deleteById(id);
    }
}
