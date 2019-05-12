package com.dlizarra.starter.item;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidItemInputException extends RuntimeException {
    public InvalidItemInputException(String message){
        super(message);
    }
}
