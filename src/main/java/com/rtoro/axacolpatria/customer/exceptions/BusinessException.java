package com.rtoro.axacolpatria.customer.exceptions;

import lombok.Data;

/**
 *
 * @author Roberto Toro
 */

@Data
public class BusinessException extends RuntimeException{

    private String code = "";
    private String message;
    
    public BusinessException(String code, String message){
        super(message);
        this.code = code;
        this.message = message;
    }

    public BusinessException(String message){
        super(message);
        this.message = message;
    }

}
