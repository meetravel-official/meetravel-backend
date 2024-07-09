package com.meetravel.user_service.global.converter;

import com.meetravel.module_common.converter.EnumToValueConverter;
import com.meetravel.user_service.domain.user.enums.PlanningType;

public class PlanningTypeConverter extends EnumToValueConverter<PlanningType> {
    public PlanningTypeConverter() {
        super(PlanningType.class);
    }
}
