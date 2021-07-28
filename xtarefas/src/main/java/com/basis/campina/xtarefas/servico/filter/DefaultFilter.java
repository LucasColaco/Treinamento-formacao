package com.basis.campina.xtarefas.servico.filter;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultFilter implements Serializable {
    private static final long serialVersionUID = -8455780716386770420L;

    protected String query;
    protected Boolean status;
}
