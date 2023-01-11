package com.example.docpro.features.user_experience;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/user-experiences")
@AllArgsConstructor
public class UserExperienceController {

  private final UserExperienceService userExperienceService;

  @PostMapping(path = "")
  public UserExperienceDto create(@RequestBody UserExperienceDto userExperienceDto) {

    return userExperienceService.create(userExperienceDto);
  }

  @PutMapping(path = "")
  public UserExperienceDto update(@RequestBody UserExperienceDto userExperienceDto) {

    return userExperienceService.update(userExperienceDto);
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable(name = "id") Long id) {
    userExperienceService.delete(id);
  }

  @GetMapping(path = "/{id}")
  public UserExperienceDto getById(@PathVariable(name = "id") Long id) {
    return userExperienceService.getById(id);
  }
}
