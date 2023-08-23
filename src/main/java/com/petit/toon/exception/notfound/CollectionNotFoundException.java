package com.petit.toon.exception.notfound;

import com.petit.toon.exception.PetitToonException;
import org.springframework.http.HttpStatus;

public class CollectionNotFoundException extends PetitToonException {
    public static final String MESSAGE = "존재하지 않는 콜렉션입니다.";

    public CollectionNotFoundException() {
        super(MESSAGE);
    }

    public CollectionNotFoundException(Throwable cause) {
        super(MESSAGE, cause);
    }

    @Override
    public int getStatusCode() {
        return HttpStatus.NOT_FOUND.value();
    }
}
