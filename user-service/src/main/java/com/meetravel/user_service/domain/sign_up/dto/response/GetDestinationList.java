package com.meetravel.user_service.domain.sign_up.dto.response;

import com.meetravel.user_service.domain.user.enums.TravelDest;
import lombok.Builder;
import lombok.Getter;

import java.util.List;


@Getter
@Builder
public class GetDestinationList {

    private List<DestinationResponse> destinationList;

    @Getter
    @Builder
    public static class DestinationResponse {
        private TravelDest travelDestId;
        private String destName;
    }

}
