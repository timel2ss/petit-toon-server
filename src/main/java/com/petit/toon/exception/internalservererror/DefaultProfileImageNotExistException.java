package com.petit.toon.exception.internalservererror;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class DefaultProfileImageNotExistException extends PetitToonException {
    public static final String MESSAGE = "서버 내부 오류가 발생했습니다. 나중에 다시 시도해주세요.";

    public DefaultProfileImageNotExistException() {
        super(MESSAGE);
    }

    public DefaultProfileImageNotExistException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.INTERNAL_SERVER_ERROR.value();
    }
}
