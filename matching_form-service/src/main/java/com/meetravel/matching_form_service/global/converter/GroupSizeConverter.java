package com.meetravel.matching_form_service.global.converter;

import com.meetravel.matching_form_service.domain.matching_form.enums.GroupSize;
import com.meetravel.module_common.converter.EnumToValueConverter;

public class GroupSizeConverter extends EnumToValueConverter<GroupSize> {
    public GroupSizeConverter() {
        super(GroupSize.class);
    }
}
