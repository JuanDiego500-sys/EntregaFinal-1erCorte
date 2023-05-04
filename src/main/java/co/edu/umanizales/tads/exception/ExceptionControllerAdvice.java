package co.edu.umanizales.tads.exception;

import co.edu.umanizales.tads.controller.dto.ErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(ListSEException.class)
    public ResponseEntity<Object>handleListSEException(ListSEException ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorDTO errorDTO = new ErrorDTO(400,"Validation error");
        errorDTO.setMessage(ex.getMessage());
        return new ResponseEntity<>(errorDTO,status);
    }


}
