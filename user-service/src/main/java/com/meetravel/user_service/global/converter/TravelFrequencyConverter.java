package com.meetravel.user_service.global.converter;

import com.meetravel.module_common.converter.EnumToValueConverter;
import com.meetravel.user_service.domain.user.enums.TravelFrequency;

public class TravelFrequencyConverter extends EnumToValueConverter<TravelFrequency> {
    public TravelFrequencyConverter() {
        super(TravelFrequency.class);
    }
}
