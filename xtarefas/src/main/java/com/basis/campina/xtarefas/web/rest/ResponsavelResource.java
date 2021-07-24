package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.servico.ResponsavelService;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
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
@RequestMapping("/api/responsaveis")
public class ResponsavelResource {

    private final ResponsavelService responsavelService;

    @GetMapping
    public ResponseEntity<List<ResponsavelDTO>> buscar(){
        return ResponseEntity.ok(responsavelService.buscar());
    }

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
    public ResponseEntity<ResponsavelDTO> editar(@PathVariable Integer id, @RequestBody ResponsavelDTO responsavelDTO){
        responsavelDTO.setId(id);
        ResponsavelDTO dto = responsavelService.salvar(responsavelDTO);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Integer id){
        responsavelService.remover(id);
        return ResponseEntity.ok().build();
    }

}
