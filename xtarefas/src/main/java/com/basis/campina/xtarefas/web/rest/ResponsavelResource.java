package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.dominio.document.ResponsavelDocument;
import com.basis.campina.xtarefas.servico.ResponsavelService;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.servico.elastic.ResponsavelSearchService;
import com.basis.campina.xtarefas.servico.filter.ResponsavelFilter;
import java.net.URI;
import java.net.URISyntaxException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/responsaveis")
public class ResponsavelResource {

    private final ResponsavelService responsavelService;
    private final ResponsavelSearchService responsavelSearchService;

    @GetMapping("/{id}")
    public ResponseEntity<ResponsavelDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(responsavelService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ResponsavelDTO> salvar(@RequestBody ResponsavelDTO responsavelDTO) throws URISyntaxException {
        ResponsavelDTO dto = responsavelService.salvar(responsavelDTO);
        return ResponseEntity.created(new URI("/api/responsaveis/")).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> editar(@PathVariable Integer id, @RequestBody ResponsavelDTO responsavelDTO){
        responsavelDTO.setId(id);
        responsavelService.alterar(responsavelDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){
        responsavelService.remover(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<ResponsavelDocument>> search(@RequestBody ResponsavelFilter filter, Pageable pageable) {
        Page<ResponsavelDocument> responsaveis = responsavelSearchService.search(filter, pageable);
        return new ResponseEntity<>(responsaveis, HttpStatus.OK);
    }
}
