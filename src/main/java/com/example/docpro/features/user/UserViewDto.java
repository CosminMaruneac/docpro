package com.example.docpro.features.user;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserViewDto {

  private Long id;

  private String firstName;

  private String lastName;

  private String address;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  @Enumerated(EnumType.STRING)
  private UserSpecialization userSpecialization;

  private String email;
}
