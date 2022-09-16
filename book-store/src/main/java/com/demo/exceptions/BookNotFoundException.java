package com.demo.exceptions;

public class BookNotFoundException extends Exception{

    private static final long serialVersionUID = 1;

    public BookNotFoundException(String message){
        super(message);
    }
}
