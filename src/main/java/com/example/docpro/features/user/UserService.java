package com.example.docpro.features.user;

import com.example.docpro.features.appointment.AppointmentRepository;
import com.example.docpro.features.service.ServiceRepository;
import com.example.docpro.features.utils.BadRequestException;
import com.example.docpro.features.utils.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final ServiceRepository serviceRepository;
  private final AppointmentRepository appointmentRepository;
  private final MailService mailService;

  @Autowired
  public UserService(UserRepository userRepository, ServiceRepository serviceRepository, AppointmentRepository appointmentRepository, MailService mailService) {
    this.userRepository = userRepository;
    this.serviceRepository = serviceRepository;
    this.appointmentRepository = appointmentRepository;
    this.mailService = mailService;
  }

  public UserDto getById(Long id) {

    return UserMapper.userToUserDto(userRepository.getById(id));
  }

  public UserDto register(UserDto userDto) throws MessagingException {

    if (Boolean.TRUE.equals(userRepository.existsByEmail(userDto.getEmail())))
      throw new BadRequestException("This user already exists!");

    return create(userDto);
  }

  private UserDto create(UserDto userDto) throws MessagingException {

    User user = UserMapper.userDtoToUser(userDto);

    user.setPassword(PasswordHasher.hashPassword(userDto.getPassword()));

    mailService.sendEmail(userDto.getEmail(),
        "Welcome to Docpro!",
        "Welcome, " + userDto.getFirstName() + "!");

    return UserMapper.userToUserDto(userRepository.save(user));
  }

  public UserDto login(UserLoginDto loginDto) {

    if (Boolean.FALSE.equals(userRepository.existsByEmail(loginDto.getEmail())))
      throw new BadRequestException("This user does not exist!");

    User user = userRepository.findByEmail(loginDto.getEmail());

    if (PasswordVerifier.verifyPassword(loginDto.getPassword(), user.getPassword())) {
      return UserMapper.userToUserDto(user);
    }
    return new UserDto();

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

  @Transactional
  public void delete(Long id) {

    User user = userRepository.findById(id).orElseThrow(EntityNotFoundException::new);

    appointmentRepository.deleteAllByCreatorOrDoctor(user, user);
    userRepository.deleteById(id);
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
