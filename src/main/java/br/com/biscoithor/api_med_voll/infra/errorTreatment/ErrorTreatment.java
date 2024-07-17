package br.com.biscoithor.api_med_voll.infra.errorTreatment;

import br.com.biscoithor.api_med_voll.domain.ValidacaoException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorTreatment {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity handleError404()
    {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleError400(MethodArgumentNotValidException ex)
    {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(ValidationErrorDTO::new).toList());
    }

    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity handleErrorRegraDeNegocio(ValidacaoException ex)
    {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }


    private record ValidationErrorDTO(String field, String message)
    {
        public ValidationErrorDTO(FieldError error)
        {
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
