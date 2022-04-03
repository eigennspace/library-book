package com.harist.belajarspring.book;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BookOutOfStockException extends RuntimeException {

    public BookOutOfStockException() {
        super();
    }
}
