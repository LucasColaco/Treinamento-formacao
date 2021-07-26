package com.basis.campina.xtarefas.servico.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ResponsavelEvent extends DefaultEvent{

    public ResponsavelEvent(Integer id){
        super(id);
    }
}
