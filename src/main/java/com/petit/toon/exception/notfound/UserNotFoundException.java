package com.petit.toon.exception.notfound;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends PetitToonException {

    public static final String MESSAGE = "존재하지 않는 사용자입니다.";

    public UserNotFoundException() {
        super(MESSAGE);
    }

    public UserNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
