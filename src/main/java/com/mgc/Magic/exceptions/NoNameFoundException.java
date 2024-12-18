package com.mgc.Magic.exceptions;

public class NoNameFoundException extends AppException {
    public NoNameFoundException(String name) {

        super("Entity with name " + name + " not found in data base");
    }
}
