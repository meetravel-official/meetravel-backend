package com.meetravel.matching_form_service.global.converter;

import com.meetravel.matching_form_service.domain.matching_form.enums.Cost;
import com.meetravel.module_common.converter.EnumToValueConverter;

public class CostConverter extends EnumToValueConverter<Cost> {
    public CostConverter() {
        super(Cost.class);
    }
}
