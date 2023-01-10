package com.example.docpro.features.review;

import com.example.docpro.features.user.User;
import com.example.docpro.features.user.UserMinimalDetailsDto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

public class ReviewMapper {

  public static ReviewDto reviewToReviewDto(Review review) {

    return ReviewDto.builder()
        .id(review.getId())
        .creatorId(review.getCreator().getId())
        .description(review.getDescription())
        .facilitatorId(review.getFacilitator().getId())
        .stars(review.getStars())
        .facilitator(UserMinimalDetailsDto.builder()
            .id(review.getFacilitator().getId())
            .userSpecialization(review.getFacilitator().getUserSpecialization())
            .firstName(review.getFacilitator().getFirstName())
            .lastName(review.getFacilitator().getLastName())
            .build())
        .creator(UserMinimalDetailsDto.builder()
            .id(review.getCreator().getId())
            .lastName(review.getCreator().getLastName())
            .firstName(review.getCreator().getFirstName())
            .userSpecialization(review.getCreator().getUserSpecialization())
            .build())
        .createdAt(LocalDate.now())
        .build();
  }

  public static Review reviewDtoToReview(ReviewDto dto, Map<String, Object> neededContent) {

    return Review.builder()
        .creator((User) neededContent.get("creator"))
        .facilitator((User) neededContent.get("facilitator"))
        .description(dto.getDescription())
        .stars(dto.getStars())
        .build();
  }
}
