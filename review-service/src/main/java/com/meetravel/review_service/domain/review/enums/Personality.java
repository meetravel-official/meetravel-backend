package com.meetravel.review_service.domain.review.enums;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Personality {

    CONSIDERATE("잘 배려해줘요"),
    CHARMING("매력있어요"),
    WITTY("재치있어요"),
    TALKATIVE("대화가 잘 통해요"),
    KIND("친절해요"),
    POSITIVE("긍정적이에요"),
    CUTE("귀여워요"),
    PASSIONATE("열정적이에요"),
    EMPATHETIC("잘 공감해줘요");

    private final String desc;

}
