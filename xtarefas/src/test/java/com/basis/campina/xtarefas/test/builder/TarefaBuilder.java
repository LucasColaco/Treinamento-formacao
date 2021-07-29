package com.basis.campina.xtarefas.test.builder;

import com.basis.campina.xtarefas.dominio.Tarefa;
import com.basis.campina.xtarefas.servico.TarefaService;
import com.basis.campina.xtarefas.servico.dto.TarefaDTO;
import com.basis.campina.xtarefas.servico.mapper.TarefaMapper;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TarefaBuilder extends ConstrutorEntidade<Tarefa>{

    @Autowired
    private TarefaService tarefaService;

    @Autowired
    private TarefaMapper tarefaMapper;

    @Autowired
    private ResponsavelBuilder responsavelBuilder;

    @Override
    public Tarefa construirEntidade() throws ParseException {
        Tarefa tarefa = new Tarefa();
        tarefa.setNome("Tarefa-criar");
        tarefa.setDtInicio(LocalDate.now());
        tarefa.setDtConclusao(LocalDate.now());
        tarefa.setStatus("ATIVA");
        tarefa.setResponsavel(responsavelBuilder.construir());
        tarefa.setAnexos((null));
        return tarefa;
    }

    public TarefaDTO construirObjetoDTO() throws ParseException{
        return this.tarefaMapper.toDto(this.construirEntidade());
    }

    public TarefaDTO construirDTO() throws ParseException{
        return this.tarefaMapper.toDto(this.construir());
    }

    @Override
    protected Tarefa persistir(Tarefa entidade) {
        TarefaDTO tarefaDTO = tarefaService.salvar(tarefaMapper.toDto(entidade));
        return this.tarefaMapper.toEntity(tarefaDTO);
    }

    @Override
    protected Collection<Tarefa> obterTodos() {
        return null;
    }

    @Override
    protected Tarefa obterPorId(Integer id) {
        return null;
    }
}
