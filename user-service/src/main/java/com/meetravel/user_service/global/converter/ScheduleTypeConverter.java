package com.meetravel.user_service.global.converter;

import com.meetravel.module_common.converter.EnumToValueConverter;
import com.meetravel.user_service.domain.user.enums.ScheduleType;

public class ScheduleTypeConverter extends EnumToValueConverter<ScheduleType> {
    public ScheduleTypeConverter() {
        super(ScheduleType.class);
    }
}
