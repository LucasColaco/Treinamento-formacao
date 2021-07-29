package com.basis.campina.xtarefas.config;

import com.basis.campina.xtarefas.servico.dto.DocumentoDTO;
import com.basis.campina.xtarefas.servico.feign.DocumentClient;
import java.util.Collections;
import javax.annotation.PostConstruct;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MockConfiguration {

    @MockBean
    private DocumentClient documentClient;

    @PostConstruct
    public void mock(){
        Mockito.when(documentClient.salvar(Collections.singletonList(new DocumentoDTO()))).thenReturn(null);
    }
}
