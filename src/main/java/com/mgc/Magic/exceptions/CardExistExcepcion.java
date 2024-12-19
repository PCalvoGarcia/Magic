package com.mgc.Magic.exceptions;

public class CardExistExcepcion extends AppException {
    public CardExistExcepcion() {
        super("This card alredy exist in data base.");
    }
}
