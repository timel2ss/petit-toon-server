package com.petit.toon.exception.notfound;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class RefreshTokenNotFoundException extends PetitToonException {
    public static final String MESSAGE = "세션이 만료되었습니다. 다시 로그인하세요.";

    public RefreshTokenNotFoundException() {
        super(MESSAGE);
    }

    public RefreshTokenNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
