package com.example.docpro.features.user_experience;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class UserExperienceMapper {

  public static UserExperience userExperienceDtoToUserExperience(UserExperienceDto dto) {

    return UserExperience.builder()
        .date(dto.getDate())
        .description(dto.getDescription())
        .build();
  }

  public static UserExperienceDto userExperienceToUserExperienceDto(UserExperience userExperience) {

    return UserExperienceDto.builder()
        .id(userExperience.getId())
        .date(userExperience.getDate())
        .description(userExperience.getDescription())
        .userId(userExperience.getUser().getId())
        .build();
  }

  public static List<UserExperienceDto> userExperienceDtoSetToUserExperienceSet(Set<UserExperience> userExperience) {

    return userExperience.stream()
        .map(UserExperienceMapper::userExperienceToUserExperienceDto)
        .collect(Collectors.toList());
  }
}
