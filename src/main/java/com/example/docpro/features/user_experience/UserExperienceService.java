package com.example.docpro.features.user_experience;

import com.example.docpro.features.user.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;

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

  public UserExperienceDto update(UserExperienceDto userExperienceDto) {

    return userExperienceRepository.findById(userExperienceDto.getId())
        .map(userExperience -> {
          userExperience.setDescription(userExperienceDto.getDescription());

          return UserExperienceMapper.userExperienceToUserExperienceDto(userExperienceRepository.save(userExperience));
        }).orElseThrow(EntityExistsException::new);
  }
}
