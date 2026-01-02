package com.vivek.exception;

import jakarta.persistence.criteria.CriteriaBuilder;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException(String msg){
        super(msg);
    }
}
