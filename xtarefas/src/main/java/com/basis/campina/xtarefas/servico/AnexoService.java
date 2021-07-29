//package com.basis.campina.xtarefas.servico;
//
//import com.basis.campina.xtarefas.dominio.Anexo;
//import com.basis.campina.xtarefas.repositorio.AnexoRepository;
//import com.basis.campina.xtarefas.servico.dto.AnexoDTO;
//import com.basis.campina.xtarefas.servico.dto.DocumentoDTO;
//import com.basis.campina.xtarefas.servico.event.AnexoEvent;
//import com.basis.campina.xtarefas.servico.feign.DocumentClient;
//import com.basis.campina.xtarefas.servico.mapper.AnexoMapper;
//import java.util.List;
//import java.util.stream.Collectors;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.ApplicationEventPublisher;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@RequiredArgsConstructor
//@Transactional
//public class AnexoService {
//
//    private final AnexoRepository anexoRepository;
//
//    private final AnexoMapper anexoMapper;
//
//    private final DocumentClient documentoClient;
//
//    private final ApplicationEventPublisher applicationEventPublisher;
//
//    public AnexoDTO buscarPorId(Integer id){
//        Anexo anexo = anexoRepository.findById(id).orElseThrow(()->new RuntimeException("Responsável não encontrado"));
//        return anexoMapper.toDto(anexo);
//    }
//
//    public DocumentoDTO obterDocumento(String uuId){
//        return documentoClient.obterDocumento(uuId).getBody();
//    }
//
//    public void salvarDocumentos(List<AnexoDTO> anexoDTOs){
//        List<DocumentoDTO> documentoDTOS = anexoDTOs.stream().map(AnexoDTO::getDocumentoDTO).collect(Collectors.toList());
//        documentoClient.salvar(documentoDTOS);
//    }
//
//    public void lancarEvento(List<AnexoDTO> anexos){
//        anexos.forEach(anexo -> applicationEventPublisher.publishEvent(new AnexoEvent(anexo.getId())));
//    }
//}
