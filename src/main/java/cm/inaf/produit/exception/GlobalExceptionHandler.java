package cm.inaf.produit.exception;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import cm.inaf.produit.model.ErrorEntityModel;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorEntityModel> handleNotFoundException(ResourceNotFoundException exception){

        ErrorEntityModel err = new ErrorEntityModel();
        err.setMessage(exception.getMessage());
        err.setDate(LocalDate.now());
        err.setAuthor(String.valueOf(HttpStatus.NOT_FOUND.value()));

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(InternalServerException.class)
    public ResponseEntity<ErrorEntityModel> handleInternalServerException(InternalServerException exception){

        ErrorEntityModel err = new ErrorEntityModel();
        err.setAuthor(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        err.setMessage(exception.getMessage());
        err.setDate(LocalDate.now());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(err);
    }

    
}
