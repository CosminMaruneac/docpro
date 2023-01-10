package com.example.docpro.features.user_experience;

import com.example.docpro.features.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserExperienceService {

  private final UserExperienceRepository userExperienceRepository;
  private final UserRepository userRepository;

  public UserExperienceDto create(UserExperienceDto userExperienceDto) {

    UserExperience userExperience = UserExperienceMapper.userExperienceDtoToUserExperience(userExperienceDto);

    userExperience.setUser(userRepository.getReferenceById(userExperienceDto.getUserId()));

    return UserExperienceMapper.userExperienceToUserExperienceDto(userExperienceRepository.save(userExperience));
  }

  public void delete(Long id) {

    userExperienceRepository.deleteById(id);
  }
}
