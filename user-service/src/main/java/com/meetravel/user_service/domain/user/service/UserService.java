package com.meetravel.user_service.domain.user.service;

import com.meetravel.user_service.domain.travel_destination.repository.TravelDestRepository;
import com.meetravel.user_service.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {


    private final UserRepository userRepository;
    private final TravelDestRepository travelDestRepository;


}
