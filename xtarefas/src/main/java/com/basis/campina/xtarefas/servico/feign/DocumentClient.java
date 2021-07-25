package com.basis.campina.xtarefas.servico.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "xdocs",url = "${application.feign.url-documents}")
public interface DocumentClient {

    @GetMapping("/api/documents")
    String getAll();
}
