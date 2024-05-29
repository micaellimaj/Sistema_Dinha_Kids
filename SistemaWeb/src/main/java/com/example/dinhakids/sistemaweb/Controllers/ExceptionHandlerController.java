package com.example.dinhakids.sistemaweb.Controllers;

import com.example.dinhakids.sistemaweb.DTO.StandardErrorDTO;
import com.example.dinhakids.sistemaweb.DTO.ValidationErrorDTO;
import com.example.dinhakids.sistemaweb.exceptions.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorDTO> validationException(MethodArgumentNotValidException e, HttpServletRequest request) {
        ValidationErrorDTO err = new ValidationErrorDTO(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Validation Error", "Validation Error", request.getRequestURI());
        String message = "Erro de validação no(s) atributo(s): ";
        int i = 0;
        for(FieldError o : e.getBindingResult().getFieldErrors()) {
            if(i > 0){
                message += ", " + o.getField();
            }else{
                message += o.getField();
            }
            i++;
            err.addError(o.getField(), o.getDefaultMessage());
        }
        err.setMessage(message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardErrorDTO> formatException(HttpMessageNotReadableException e, HttpServletRequest request) {
        StandardErrorDTO err = new StandardErrorDTO(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Format Exception", "Formato de dado inválido", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardErrorDTO> businessException(BusinessException e, HttpServletRequest request) {
        StandardErrorDTO err = new StandardErrorDTO(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Business Exception", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardErrorDTO> notFoundException(NotFoundException e, HttpServletRequest request) {
        StandardErrorDTO err = new StandardErrorDTO(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(PasswordEncodingException.class)
    public ResponseEntity<StandardErrorDTO> passwordEncodingException(PasswordEncodingException e, HttpServletRequest request) {
        StandardErrorDTO err = new StandardErrorDTO(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(UsernameAlreadyExistsException.class)
    public ResponseEntity<StandardErrorDTO> usernameAlreadyExistsException(UsernameAlreadyExistsException e, HttpServletRequest request) {
        StandardErrorDTO err = new StandardErrorDTO(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Username already exists", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<StandardErrorDTO> usernameNotFound(UsernameNotFoundException e, HttpServletRequest request) {
        StandardErrorDTO err = new StandardErrorDTO(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Username Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<StandardErrorDTO> productNotFound(ProductNotFoundException e, HttpServletRequest request) {
        StandardErrorDTO err = new StandardErrorDTO(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Product Not Found", e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
