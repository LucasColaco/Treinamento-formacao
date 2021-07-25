package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.servico.ResponsavelService;
import com.basis.campina.xtarefas.servico.TarefaService;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.servico.dto.TarefaDTO;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import lombok.RequiredArgsConstructor;
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

}
