package com.petit.toon.exception.badrequest;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class TokenNotValidException extends PetitToonException {
    public static final String MESSAGE = "유효하지 않거나 만료된 토큰입니다.";

    public TokenNotValidException() {
        super(MESSAGE);
    }

    public TokenNotValidException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
