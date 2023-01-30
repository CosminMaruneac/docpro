package com.example.docpro.features.user;

import org. springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }


  @PostMapping("/register")
  public UserDto register(@RequestBody UserDto userDto) {

    return userService.register(userDto);
  }

  @PostMapping("/login")
  public UserDto login(@RequestBody UserLoginDto loginDto) {

    return userService.login(loginDto);
  }

  @GetMapping(path = "/{id}")
  public UserDto getById(@PathVariable(name = "id") Long id) {
    return userService.getById(id);
  }

  @GetMapping(path = "")
  public List<UserViewDto> getAll() {
    return userService.getAll();
  }

  @GetMapping(path = "/specialization")
  public List<UserViewDto> getAllBySpecialization(@RequestParam(name = "specialization") UserSpecialization specialization) {

    return userService.getAllBySpecialization(specialization);
  }

  @GetMapping(path = "/doctor")
  public List<UserViewDto> findByName(@RequestParam(name = "firstName") String firstName,
                                      @RequestParam(name = "lastName") String lastName) {
    return userService.findByName(firstName, lastName);
  }

  @DeleteMapping(path = "/{id}")
  public void delete(@PathVariable(name = "id") Long id) {

    userService.delete(id);
  }

  @PatchMapping(path = "/assign-services")
  public UserDto assignServices(@RequestParam(name = "userId") Long userId,
                                @RequestParam(name = "serviceId") List<Long> servicesIds) {

    return userService.assignServices(userId, servicesIds);
  }

  @PatchMapping(path = "/unassign-services")
  public UserDto unassignServices(@RequestParam(name = "userId") Long userId,
                                @RequestParam(name = "serviceId") List<Long> servicesIds) {

    return userService.unassignServices(userId, servicesIds);
  }

  @PutMapping(path = "")
  public UserDto update(@RequestBody UserDto userDto){

    return userService.update(userDto);
  }
}
