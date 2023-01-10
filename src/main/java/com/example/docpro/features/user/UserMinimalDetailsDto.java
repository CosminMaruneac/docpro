package com.example.docpro.features.user;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserMinimalDetailsDto {

  private Long id;

  private String firstName;

  private String lastName;

  @Enumerated(EnumType.STRING)
  private UserSpecialization userSpecialization;
}
