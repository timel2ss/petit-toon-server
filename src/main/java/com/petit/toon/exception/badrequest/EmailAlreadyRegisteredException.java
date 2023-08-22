package com.petit.toon.exception.badrequest;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class EmailAlreadyRegisteredException extends PetitToonException {
    public static final String MESSAGE = "이미 사용 중인 이메일 주소입니다.";

    public EmailAlreadyRegisteredException() {
        super(MESSAGE);
    }

    public EmailAlreadyRegisteredException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
