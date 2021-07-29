package com.basis.campina.xtarefas.test.builder;

import com.basis.campina.xtarefas.dominio.Responsavel;
import com.basis.campina.xtarefas.servico.ResponsavelService;
import com.basis.campina.xtarefas.servico.dto.ResponsavelDTO;
import com.basis.campina.xtarefas.servico.mapper.ResponsavelMapper;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ResponsavelBuilder extends ConstrutorEntidade<Responsavel> {

    @Autowired
    private ResponsavelService responsavelService;

    @Autowired
    private ResponsavelMapper responsavelMapper;

    @Override
    public Responsavel construirEntidade() throws ParseException {
        Responsavel responsavel = new Responsavel();
        responsavel.setNome("Colacinho");
        responsavel.setEmail("colacinho@gmail.com");
        responsavel.setDtNascimento(LocalDate.now());
        return responsavel;
    }

    @Override
    protected Responsavel persistir(Responsavel entidade) {
        ResponsavelDTO responsavelDTO =responsavelService.salvar(responsavelMapper.toDto(entidade));
        return responsavelMapper.toEntity(responsavelDTO);
    }

    public ResponsavelDTO construirObjetoDTO() throws ParseException{
        return this.responsavelMapper.toDto(this.construirEntidade());
    }

    public ResponsavelDTO construirDTO() throws ParseException{
        return this.responsavelMapper.toDto(this.construir());
    }

    @Override
    protected Collection<Responsavel> obterTodos() {
        return null;
    }

    @Override
    protected Responsavel obterPorId(Integer id) {
        return null;
    }
}
