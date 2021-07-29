package com.basis.campina.xtarefas.resource;

import com.basis.campina.xtarefas.dominio.Responsavel;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.servico.elastic.ElasticsearchService;
import com.basis.campina.xtarefas.servico.event.ResponsavelEvent;
import com.basis.campina.xtarefas.servico.filter.ResponsavelFilter;
import com.basis.campina.xtarefas.test.IntTestComum;
import com.basis.campina.xtarefas.test.builder.ResponsavelBuilder;
import com.basis.campina.xtarefas.util.TestUtil;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@Transactional
public class ResponsavelResourceIT extends IntTestComum {

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Autowired
    private ElasticsearchService elasticsearchService;

    @Test
    @DisplayName("Salvar responsável com sucesso")
    public void salvarResponsavel() throws Exception{
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirObjetoDTO();
        getMockMvc().perform(post("/api/responsaveis").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Buscar por Id")
    public void buscarPorId() throws Exception{
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(get("/api/responsaveis/{id}", responsavel.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(responsavel)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Excluir responsável com sucesso")
    public void removerResponsavel() throws Exception{
        Responsavel responsavel = responsavelBuilder.construir();

        getMockMvc().perform(delete("/api/responsaveis/{id}", responsavel.getId())).andExpect(status().isOk());
    }

    @Test
    @Transactional
    @DisplayName("Listar Responsável com sucesso")
    public void listarResponsaveis() throws Exception{
        ResponsavelDTO responsavelDTO= this.responsavelBuilder.construirDTO();

        this.elasticsearchService.reindex();
        new ResponsavelEvent(responsavelDTO.getId());
        ResponsavelFilter filtro = new ResponsavelFilter();
        filtro.setQuery(responsavelDTO.getNome());

        getMockMvc().perform(post("/api/responsaveis/search").contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(filtro)))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Editar responsável com sucesso")
    public void editarResponsavel() throws Exception {
        ResponsavelDTO responsavelDTO = responsavelBuilder.construirDTO();
        responsavelDTO.setNome("Responsavel nome editado");

        getMockMvc().perform(put("/api/responsaveis/{id}", responsavelDTO.getId()).contentType(TestUtil.APPLICATION_JSON_UTF8)
                .content(com.basis.campina.xtarefas.util.TestUtil.convertObjectToJsonBytes(responsavelDTO)))
                .andExpect(status().isOk());
    }
}
