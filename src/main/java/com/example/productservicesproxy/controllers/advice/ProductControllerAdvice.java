package com.example.productservicesproxy.controllers.advice;

import com.example.productservicesproxy.exceptions.ExceptionDto;
import com.example.productservicesproxy.exceptions.ProductNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ProductControllerAdvice {
    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)// we want the status to be as 404 to be displayed
    @ResponseBody // we want output to go in response body
    private ExceptionDto handleProductNotFoundException(ProductNotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage(e.getMessage());
        exceptionDto.setStatus("Failure");

        return exceptionDto;
    }
}


/**
 * As controller was also handling the routing and the Exception coming from service as response so it was voilating the SRP of SOLID so we make advice module for handling
 * the exception
 *
 *
 * Above is Not mapped with any perticular controller so it is a Global advice. So any controller if it gets ProductNotFoundException
 * this will get trigger.
 *
 * We can specify controller name as well @ControllerAdvice("Name_Of_Controller") for the specific controller we want to run the advice
 * @ControllerAdvice(assignableTypes = {ProductControllerAdvice.class})
 *
 *
 *
 * AOP -> Aspect oriented programing
 */