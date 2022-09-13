package com.example.demo.exceptions;

import static java.lang.String.format;

public class PersonNotFoundException extends RuntimeException{
    public PersonNotFoundException(long id) {
        super(format("person not found (%d)", id));
    }
}
