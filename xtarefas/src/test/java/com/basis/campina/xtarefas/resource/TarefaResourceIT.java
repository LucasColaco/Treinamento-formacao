package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.dominio.Tarefa;
import com.basis.campina.xtarefas.servico.dto.TarefaDTO;
import com.basis.campina.xtarefas.servico.elastic.ElasticsearchService;
import com.basis.campina.xtarefas.servico.event.TarefaEvent;
import com.basis.campina.xtarefas.servico.filter.TarefaFilter;
import com.basis.campina.xtarefas.test.IntTestComum;
import com.basis.campina.xtarefas.test.builder.TarefaBuilder;
import com.basis.campina.xtarefas.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@RunWith(SpringRunner.class)
public class TarefaResourceIT extends IntTestComum {

    private static final String API_TAREFA = "/api/responsaveis";

    @Autowired
    private TarefaBuilder tarefaBuilder;

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Test
    @DisplayName("Salvar Tarefa com sucesso")
    public void salvarTarefa() throws Exception{
        TarefaDTO tarefaDTO = tarefaBuilder.construirObjetoDTO();
        getMockMvc().perform(post("/api/tarefas").contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar por Id")
    public void buscarPorId() throws Exception{
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(get("/api/tarefas/{id}", tarefa.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(tarefa)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Excluir Tarefa com sucesso")
    public void removerTarefa() throws Exception{
        Tarefa tarefa = tarefaBuilder.construir();

        getMockMvc().perform(delete("/api/tarefas/{id}", tarefa.getId())).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Listar Tarefas com sucesso")
    public void listarTarefas() throws Exception{
        TarefaDTO tarefaDTO = this.tarefaBuilder.construirDTO();

        this.elasticsearchService.reindex();
        new TarefaEvent(tarefaDTO.getId());
        TarefaFilter filtro = new TarefaFilter();
        filtro.setQuery(tarefaDTO.getNome());

        getMockMvc().perform(post("/api/tarefas/search").contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar tarefa com sucesso")
    public void editarTarefa() throws Exception {
        TarefaDTO tarefaDTO = tarefaBuilder.construirDTO();
        tarefaDTO.setNome("Tarefa nome editado");

        getMockMvc().perform(put("/api/tarefas/{id}", tarefaDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(tarefaDTO)))
                .andExpect(status().isOk());
    }


}
