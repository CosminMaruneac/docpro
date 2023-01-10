package com.example.docpro.features.user;

import com.example.docpro.features.service.ServiceDto;
import com.example.docpro.features.user_experience.UserExperienceDto;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

  private Long id;

  private String firstName;

  private String lastName;

  private String address;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  @Enumerated(EnumType.STRING)
  private UserSpecialization userSpecialization;

  private String password;

  private String email;

  private List<UserExperienceDto> userExperience = new ArrayList<>();

  private List<ServiceDto> services = new ArrayList<>();

}
