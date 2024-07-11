package com.meetravel.module_common.converter;

import com.meetravel.module_common.enums.TravelDest;

public class TravelDestConverter extends EnumToValueConverter<TravelDest>{
    public TravelDestConverter() {
        super(TravelDest.class);
    }
}
