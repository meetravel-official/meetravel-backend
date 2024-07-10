package com.meetravel.matching_form_service.domain.matching_form.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.meetravel.module_common.enums.BaseEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GenderRatio implements BaseEnum {
    ANY("상관없음"),
    MALE_FEMALE_1_1("남녀 1:1"),
    SAME_GENDER("동성끼리");

    @JsonValue
    private final String value;

    @JsonCreator
    public static GenderRatio fromValueToEnum(String value) {
        for (GenderRatio gender : GenderRatio.values()) {
            if (gender.getValue().equals(value)) {
                return gender;
            }
        }
        return null;
    }
}
