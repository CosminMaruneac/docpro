package com.example.docpro.features.review;

import com.example.docpro.features.user.UserMinimalDetailsDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {

  private Long id;

  private Long creatorId;

  private UserMinimalDetailsDto creator;

  private Long facilitatorId;

  private UserMinimalDetailsDto facilitator;

  private String description;

  private Integer stars;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
  private LocalDate createdAt;
}
