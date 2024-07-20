package com.meetravel.user_service.domain.user.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER("ROLE_USER","사용자"), ADMIN("ROLE_ADMIN","관리자");

    private final String value;
    private final String desc;

}
