package com.basis.campina.xtarefas.servico.filter;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DefaultFilter implements Serializable {
    private static final long serialVersionUID = 6681319063486148714L;

    protected String query;

    protected Boolean status;
}
