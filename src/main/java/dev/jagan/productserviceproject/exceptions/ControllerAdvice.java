package dev.jagan.productserviceproject.exceptions;

import dev.jagan.productserviceproject.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    private ResponseEntity<ErrorDto> notFoundExceptionHandler(NotFoundException notFoundException){
        return new ResponseEntity<>( new ErrorDto(notFoundException.getMessage()), HttpStatus.NOT_FOUND);
    }

}
