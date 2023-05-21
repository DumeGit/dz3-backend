package dz3.tt.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler({MethodArgumentNotValidException.class,})
    public ResponseEntity handleBindingErrors(MethodArgumentNotValidException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getBindingResult().getAllErrors().stream().map((DefaultMessageSourceResolvable::getDefaultMessage)).collect(Collectors.toList()).get(0));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    }

}