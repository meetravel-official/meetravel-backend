package com.meetravel.user_service.domain.sign_up.dto.response;

import com.meetravel.module_common.enums.TravelDest;
import com.meetravel.user_service.domain.user.enums.PlanningType;
import com.meetravel.user_service.domain.user.enums.ScheduleType;
import com.meetravel.user_service.domain.user.enums.TravelFrequency;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class GetSignUpInfoList {

    private List<TravelFrequencyInfo> travelFrequencies;
    private List<ScheduleTypeInfo> scheduleTypes;
    private List<PlanningTypeInfo> planningTypes;
    private List<TravelDestInfo> travelDestInfoList;

    @Getter
    @Builder
    public static class TravelFrequencyInfo {
        private TravelFrequency travelFrequency;
        private String desc;
    }

    @Getter
    @Builder
    public static class ScheduleTypeInfo {
        private ScheduleType scheduleType;
        private String desc;
    }

    @Getter
    @Builder
    public static class PlanningTypeInfo {
        private PlanningType planningType;
        private String desc;
    }

    @Getter
    @Builder
    public static class TravelDestInfo {
        private TravelDest travelDestId;
        private String destName;
    }

}
