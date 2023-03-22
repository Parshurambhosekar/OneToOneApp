package com.parshuram.OneToOneMapping.exception;

import com.parshuram.OneToOneMapping.entity.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException exception){

        String message = exception.getMessage();

        ApiResponse response=new ApiResponse();
        response.setMessage(message);
        response.setSuccess(true);

        return new ResponseEntity<ApiResponse>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgNotValidException(MethodArgumentNotValidException exception){

       Map<String,String> rsp=new HashMap<String,String>();

       exception.getBindingResult().getAllErrors().forEach(error->{

           String message = error.getDefaultMessage();

           String fieldName=((FieldError)error).getField();

           rsp.put(fieldName,message);

       });

       return new ResponseEntity<Map<String,String>>(rsp,HttpStatus.BAD_REQUEST);

    }


}
