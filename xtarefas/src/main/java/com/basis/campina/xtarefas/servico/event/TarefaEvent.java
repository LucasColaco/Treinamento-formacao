package com.basis.campina.xtarefas.servico.event;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TarefaEvent extends DefaultEvent{

    public TarefaEvent(Integer id){
        super(id);
    }
}
