package com.meetravel.module_common.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TravelDest {
    NATION_WIDE("전국"),
    SEOUL("서울"),
    GYEONGGI_SOUTH("경기 남부"),
    GYEONGGI_NORTH("경기 북부"),
    INCHEON("인천"),
    BUSAN("부산"),
    JEJU("제주"),
    DAEGU("대구"),
    DAEJEON("대전"),
    GWANGJU("광주"),
    ULSAN("울산"),
    SEJONG("세종"),
    GANGWON("강원"),
    CHUNGBUK("충북"),
    CHUNGNAM("충남"),
    JEONBUK("전북"),
    JEONNAM("전남"),
    GYEONGBUK("경북"),
    GYEONGNAM("경남");

    private final String destName;
}
