package io.github.pakiruchinoike.movies.exception;

public class ServiceRuleException extends RuntimeException{
    
    public ServiceRuleException() {
        super("Objeto não encontrado.");
    }

}
