package com.basis.campina.xtarefas.servico.exception;

public class ObjectNotFoundException extends RuntimeException{
    private static final long serialVersionUID = -3495677123674059641L;

    public ObjectNotFoundException(String msg){
        super(msg);
    }

    public ObjectNotFoundException(String msg, Throwable cause){
        super(msg, cause);
    }
}
