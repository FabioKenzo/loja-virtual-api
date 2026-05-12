package br.com.kenzowebstudio.loja_virtual.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import br.com.kenzowebstudio.loja_virtual.dto.ErroResposta;
import jakarta.servlet.http.HttpServletRequest;

//diz pro spring para observar todos os controllers
@ControllerAdvice
public class GlobalExceptionHandler {

    //captura qualquer RuntimeException que usamos nos services
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErroResposta> handleRuntimeException(RuntimeException ex, HttpServletRequest request) {
        
        ErroResposta erro = new ErroResposta(
                HttpStatus.BAD_REQUEST.value(), // status 400
                "Erro na requisição",
                ex.getMessage(), //mensagem do service
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }


    //captura erros genéricos como erro de digitação no json e no postman
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleGenericException(Exception ex, HttpServletRequest request) {
        
        ErroResposta erro = new ErroResposta(
                HttpStatus.INTERNAL_SERVER_ERROR.value(), // Status 500
                "Erro interno no servidor",
                "Ocorreu um erro inesperado. Tente novamente mais tarde.",
                request.getRequestURI()
        );

        return new ResponseEntity<>(erro, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}