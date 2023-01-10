package com.example.docpro.features.user;

import com.example.docpro.features.service.ServiceRepository;
import com.example.docpro.features.utils.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final ServiceRepository serviceRepository;

  @Autowired
  public UserService(UserRepository userRepository, ServiceRepository serviceRepository) {
    this.userRepository = userRepository;
    this.serviceRepository = serviceRepository;
  }

  public UserDto getById(Long id) {

    return UserMapper.userToUserDto(userRepository.getById(id));
  }

  public UserDto register(UserDto userDto) {

    if (Boolean.TRUE.equals(userRepository.existsByEmail(userDto.getEmail())))
      throw new BadRequestException("This user already exists!");

    return create(userDto);
  }

  private UserDto create(UserDto userDto) {

    User user = UserMapper.userDtoToUser(userDto);

    user.setPassword(PasswordHasher.hashPassword(userDto.getPassword()));

    return UserMapper.userToUserDto(userRepository.save(user));
  }

  public Boolean login(UserLoginDto loginDto) {

    if (Boolean.FALSE.equals(userRepository.existsByEmail(loginDto.getEmail())))
      throw new BadRequestException("This user does not exist!");

    User user = userRepository.findByEmail(loginDto.getEmail());

    return PasswordVerifier.verifyPassword(loginDto.getPassword(), user.getPassword());

  }

  public List<UserViewDto> getAllBySpecialization(UserSpecialization specialization) {

    return userRepository.findAllByUserSpecialization(specialization).stream()
        .map(UserMapper::mapUserToUserViewDto)
        .collect(Collectors.toList());
  }

  public List<UserViewDto> findByName(String firstName, String lastName) {

    return userRepository.findAllByFirstNameLikeAndLastNameLike(firstName, lastName).stream()
        .map(UserMapper::mapUserToUserViewDto)
        .collect(Collectors.toList());
  }

  public List<UserViewDto> getAll() {

    return userRepository.findAll().stream()
        .map(UserMapper::mapUserToUserViewDto)
        .collect(Collectors.toList());
  }

  public void delete(Long id) {

    //to be implemented
  }

  public UserDto assignServices(Long userId, List<Long> servicesIds) {

    User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

    Set<com.example.docpro.features.service.Service> allByIdIn = serviceRepository.findAllByIdIn(servicesIds);

    user.setServices(allByIdIn);

    return UserMapper.userToUserDto(userRepository.save(user));
  }

  public UserDto unassignServices(Long userId, List<Long> servicesIds) {

    User user = userRepository.findById(userId).orElseThrow(EntityNotFoundException::new);

    Set<com.example.docpro.features.service.Service> allByIdIn = serviceRepository.findAllByIdIn(servicesIds);

    user.getServices().removeAll(allByIdIn);

    return UserMapper.userToUserDto(userRepository.save(user));

  }

  public UserDto update(UserDto userDto) {

    return userRepository.findById(userDto.getId())
        .map(user -> {

          user.setUserSpecialization(userDto.getUserSpecialization());
          user.setUserType(userDto.getUserType());
          user.setAddress(userDto.getAddress());
          user.setEmail(userDto.getEmail());
          user.setFirstName(userDto.getFirstName());
          user.setLastName(userDto.getLastName());
          user.setPhoneNumber(userDto.getPhoneNumber());
          user.setProfileImageUrl(userDto.getProfileImageUrl());
          user.setTimeSchedule(userDto.getTimeSchedule());
          user.setDescription(userDto.getDescription());

          userRepository.save(user);
          return UserMapper.userToUserDto(user);
        }).orElseThrow(EntityNotFoundException::new);
  }
}
