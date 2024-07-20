package com.meetravel.matching_form_service.feign;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "user-service")
public interface UserServiceFeignClient {



}
