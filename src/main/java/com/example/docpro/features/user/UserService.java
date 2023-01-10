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

    if (userRepository.existsByEmail(userDto.getEmail()))
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
}
