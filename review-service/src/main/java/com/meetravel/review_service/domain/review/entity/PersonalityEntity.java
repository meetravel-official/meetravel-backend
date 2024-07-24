package com.meetravel.review_service.domain.review.entity;

import com.meetravel.review_service.domain.review.enums.Personality;
import com.meetravel.review_service.domain.review.enums.TravelMateReview;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "personality")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class PersonalityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PERSONALITY_ID")
    private Long id;

    @Column(name = "PERSONALITY")
    private Personality personality;


}
