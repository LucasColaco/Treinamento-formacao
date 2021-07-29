//package com.basis.campina.xtarefas.web.rest;
//
//import com.basis.campina.xtarefas.dominio.document.AnexoDocument;
//import com.basis.campina.xtarefas.servico.AnexoService;
//import com.basis.campina.xtarefas.servico.dto.AnexoDTO;
//import com.basis.campina.xtarefas.servico.dto.DocumentoDTO;
//import com.basis.campina.xtarefas.servico.feign.DocumentClient;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.domain.Page;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api/anexos")
//@RequiredArgsConstructor
//public class AnexoResource {
//
//    private final AnexoService service;
//
//    private final AnexoElasticsearchService elasticsearchService;
//
//    @GetMapping("/{id}")
//    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Long id) {
//        log.info("[XTAREFAS] Requisição REST para obter Anexo por id {}", id);
//        return ResponseEntity.ok(service.obterPorId(id));
//    }
//
//    @GetMapping("/documento/{uuId}")
//    public ResponseEntity<DocumentoDTO> obterDocumento(@PathVariable String uuId) {
//        log.info("[XTAREFAS] Requisição REST para obter Documento por uuId {}", uuId);
//        return ResponseEntity.ok(service.obterDocumento(uuId));
//    }
//
//    @PostMapping("/_search")
//    public ResponseEntity<Page<AnexoDocument>> search(@RequestBody AnexoFiltro filter, Pageable pageable) {
//        Page<AnexoDocument> anexos = elasticsearchService.search(filter, pageable);
//        return new ResponseEntity<>(anexos, HttpStatus.OK);
//    }
//}
