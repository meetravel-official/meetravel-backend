package com.meetravel.module_common.exception;

import org.springframework.security.core.AuthenticationException;

public class UnAuthorizedException extends AuthenticationException {

    public UnAuthorizedException(String msg) {
        super(msg);
    }
}

