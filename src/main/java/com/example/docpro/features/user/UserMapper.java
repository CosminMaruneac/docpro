package com.example.docpro.features.user;

import com.example.docpro.features.service.ServiceMapper;
import com.example.docpro.features.user_experience.UserExperienceMapper;

import java.util.stream.Collectors;

public class UserMapper {

  static UserDto userToUserDto(User user) {

    UserDto userDto = new UserDto();

    userDto.setFirstName(user.getFirstName());
    userDto.setLastName(user.getLastName());
    userDto.setUserSpecialization(user.getUserSpecialization());
    userDto.setAddress(user.getAddress());
    userDto.setEmail(user.getEmail());
    userDto.setPhoneNumber(user.getPhoneNumber());
    userDto.setId(user.getId());
    userDto.setUserType(user.getUserType());
    userDto.setUserExperience(UserExperienceMapper.userExperienceDtoSetToUserExperienceSet(user.getUserExperience()));
    userDto.setServices(user.getServices().stream().map(ServiceMapper::mapServiceToServiceDto).collect(Collectors.toList()));
    userDto.setProfileImageUrl(user.getProfileImageUrl());

    return userDto;
  }

  static User userDtoToUser(UserDto dto) {

    User user = new User();

    user.setUserSpecialization(dto.getUserSpecialization());
    user.setUserType(dto.getUserType());
    user.setAddress(dto.getAddress());
    user.setEmail(dto.getEmail());
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setPhoneNumber(dto.getPhoneNumber());
    user.setProfileImageUrl(dto.getProfileImageUrl());

    return user;
  }

  public static UserViewDto mapUserToUserViewDto(User user) {

    return UserViewDto.builder()
        .id(user.getId())
        .address(user.getAddress())
        .email(user.getEmail())
        .userSpecialization(user.getUserSpecialization())
        .firstName(user.getFirstName())
        .lastName(user.getLastName())
        .phoneNumber(user.getPhoneNumber())
        .userType(user.getUserType())
        .profileImageUrl(user.getProfileImageUrl())
        .build();
  }
}
