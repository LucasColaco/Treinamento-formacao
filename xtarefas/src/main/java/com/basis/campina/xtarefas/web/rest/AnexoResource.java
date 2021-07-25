package com.basis.campina.xtarefas.web.rest;

import com.basis.campina.xtarefas.servico.feign.DocumentClient;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/anexos")
@RequiredArgsConstructor
public class AnexoResource {

    private final DocumentClient documentClient;

    @GetMapping("/string")
    public String getAll(){
        return documentClient.getAll();
    }
}
