package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.dominio.document.TarefaDocument;
import com.basis.campina.xtarefas.servico.TarefaService;
import com.basis.campina.xtarefas.servico.dto.TarefaDTO;
import com.basis.campina.xtarefas.servico.elastic.ElasticsearchService;
import com.basis.campina.xtarefas.servico.elastic.TarefaSearchService;
import com.basis.campina.xtarefas.servico.filter.TarefaFilter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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
@RequestMapping("/api/tarefas")
public class TarefaResource {

    private final TarefaService tarefaService;

    private final TarefaSearchService tarefaSearchService;

    @GetMapping
    public ResponseEntity<List<TarefaDTO>> buscar(){
        return ResponseEntity.ok(tarefaService.buscar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TarefaDTO> buscarPorId(@PathVariable Integer id){
        return ResponseEntity.ok(tarefaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TarefaDTO> salvar(@RequestBody TarefaDTO tarefaDTO) throws URISyntaxException {
        TarefaDTO dto = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.created(new URI("/api/tarefas/")).body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TarefaDTO> editar(@PathVariable Integer id, @RequestBody TarefaDTO tarefaDTO){
        tarefaDTO.setId(id);
        TarefaDTO dto = tarefaService.salvar(tarefaDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){
        tarefaService.remover(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/search")
    public ResponseEntity<Page<TarefaDocument>> search(@RequestBody TarefaFilter filter, Pageable pageable) {
        Page<TarefaDocument> tarefas = tarefaSearchService.search(filter, pageable);
        return new ResponseEntity<>(tarefas, HttpStatus.OK);
    }

}
