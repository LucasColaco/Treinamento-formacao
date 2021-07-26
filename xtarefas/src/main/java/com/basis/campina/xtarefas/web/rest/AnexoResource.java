package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.dominio.document.AnexoDocument;
import com.basis.campina.xtarefas.repositorio.elasticsearch.AnexoSearchRepository;
import com.basis.campina.xtarefas.servico.AnexoService;
import com.basis.campina.xtarefas.servico.dto.AnexoDTO;
import com.basis.campina.xtarefas.servico.dto.DocumentoDTO;
import com.basis.campina.xtarefas.servico.elastic.AnexoSearchService;
import com.basis.campina.xtarefas.servico.feign.DocumentClient;
import com.basis.campina.xtarefas.servico.filter.AnexoFiltro;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
public class AnexoResource {

    private final AnexoService anexoService;

    private final AnexoSearchService anexoSearchService;

    @GetMapping("/documento/{uuId}")
    public ResponseEntity<DocumentoDTO> obterDocumento(@PathVariable String uuId) {
        return ResponseEntity.ok(anexoService.obterDocumento(uuId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AnexoDTO> obterPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(anexoService.buscarPorId(id));
    }

    @PostMapping("/_search")
    public ResponseEntity<Page<AnexoDocument>> search(@RequestBody AnexoFiltro filter, Pageable pageable) {
        Page<AnexoDocument> anexos = anexoSearchService.search(filter, pageable);
        return new ResponseEntity<>(anexos, HttpStatus.OK);
    }
}
