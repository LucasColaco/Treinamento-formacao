package com.basis.campina.xtarefas.servico;

import com.basis.campina.xtarefas.dominio.Anexo;
import com.basis.campina.xtarefas.repositorio.AnexoRepository;
import com.basis.campina.xtarefas.servico.dto.AnexoDTO;
import com.basis.campina.xtarefas.servico.dto.DocumentoDTO;
import com.basis.campina.xtarefas.servico.feign.DocumentClient;
import com.basis.campina.xtarefas.servico.mapper.AnexoMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AnexoService {

    private final AnexoRepository anexoRepository;

    private final AnexoMapper anexoMapper;

    private final DocumentClient documentClient;

    public AnexoDTO salvar(AnexoDTO anexoDTO){
        Anexo obj = anexoMapper.toEntity(anexoDTO);
        obj = anexoRepository.save(obj);
        return anexoMapper.toDto(obj);
    }

    public DocumentoDTO obterDocumento(String uuId){
        return documentClient.obterDocumento(uuId).getBody();
    }

    public void salvarDocumentos(List<AnexoDTO> anexoDTOs){
        List<DocumentoDTO> documentoDTOS = anexoDTOs.stream().map(AnexoDTO::getDocumentoDTO).collect(Collectors.toList());
        documentClient.salvar(documentoDTOS);
    }
}
