package com.meetravel.matching_form_service.global.converter;

import com.meetravel.matching_form_service.domain.matching_form.enums.GenderRatio;
import com.meetravel.module_common.converter.EnumToValueConverter;

public class GenderRatioConverter extends EnumToValueConverter<GenderRatio> {
    public GenderRatioConverter() {
        super(GenderRatio.class);
    }
}
