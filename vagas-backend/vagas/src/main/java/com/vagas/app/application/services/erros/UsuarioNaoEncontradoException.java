package com.vagas.app.application.services.erros;

public class UsuarioNaoEncontradoException extends RuntimeException {

   public UsuarioNaoEncontradoException(String msg){
       super(msg);
   }


}
