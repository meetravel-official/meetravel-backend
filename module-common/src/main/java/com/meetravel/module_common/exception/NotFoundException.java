package com.meetravel.module_common.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.NOT_FOUND;

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode, HTTP_STATUS);
    }

}
