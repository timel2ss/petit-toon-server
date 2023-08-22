package com.petit.toon.exception.badrequest;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class AuthorityNotMatchException extends PetitToonException {
    public static final String MESSAGE = "사용자 권한이 없습니다.";

    public AuthorityNotMatchException() {
        super(MESSAGE);
    }

    public AuthorityNotMatchException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
