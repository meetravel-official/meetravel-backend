package com.meetravel.matching_form_service.global.converter;

import com.meetravel.matching_form_service.domain.matching_form.enums.Duration;
import com.meetravel.module_common.converter.EnumToValueConverter;

public class DurationConverter extends EnumToValueConverter<Duration> {
    public DurationConverter() {
        super(Duration.class);
    }
}
