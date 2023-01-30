package com.example.docpro.features.appointment;

import com.example.docpro.features.service.ServiceRepository;
import com.example.docpro.features.user.User;
import com.example.docpro.features.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AppointmentService {

  private final AppointmentRepository appointmentRepository;
  private final UserRepository userRepository;
  private final ServiceRepository serviceRepository;

  public AppointmentDto create(AppointmentDto appointmentDto) {

    User creator = userRepository.getReferenceById(appointmentDto.getCreatorId());
    User doctor = userRepository.getReferenceById(appointmentDto.getDoctorId());
    com.example.docpro.features.service.Service service = serviceRepository.getReferenceById(appointmentDto.getServiceId());

    Map<String,Object> neededContent = new HashMap<>();
    neededContent.put("creator", creator);
    neededContent.put("doctor", doctor);
    neededContent.put("service", service);

    Appointment appointment = AppointmentMapper.appointmentDtoToAppointment(appointmentDto, neededContent);


    appointment.setCreator(creator);
    appointment.setDoctor(doctor);
    appointment.setService(service);

    return AppointmentMapper.appointmentToAppointmentDto(appointmentRepository.save(appointment));

  }

  public List<AppointmentDto> getAll() {

    return appointmentRepository.findAll().stream()
        .map(AppointmentMapper::appointmentToAppointmentDto)
        .collect(Collectors.toList());
  }
}
