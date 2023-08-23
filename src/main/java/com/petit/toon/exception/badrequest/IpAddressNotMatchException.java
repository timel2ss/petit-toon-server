package com.petit.toon.exception.badrequest;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class IpAddressNotMatchException extends PetitToonException {
    public static final String MESSAGE = "잘못된 접근입니다. 다시 로그인하세요.";

    public IpAddressNotMatchException() {
        super(MESSAGE);
    }

    public IpAddressNotMatchException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.BAD_REQUEST.value();
    }
}
