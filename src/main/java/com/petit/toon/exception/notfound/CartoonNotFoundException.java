package com.petit.toon.exception.notfound;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class CartoonNotFoundException extends PetitToonException {
    public static final String MESSAGE = "존재하지 않는 웹툰입니다.";

    public CartoonNotFoundException() {
        super(MESSAGE);
    }

    public CartoonNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
