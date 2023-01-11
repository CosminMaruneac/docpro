package com.example.docpro.features.appointment;

import com.example.docpro.features.service.Service;
import com.example.docpro.features.service.ServiceMapper;
import com.example.docpro.features.user.User;
import com.example.docpro.features.user.UserMapper;

import java.util.Map;

public class AppointmentMapper {

  public static Appointment appointmentDtoToAppointment(AppointmentDto appointmentDto, Map<String, Object> neededContent) {

    return Appointment.builder()
        .creator((User) neededContent.get("creator"))
        .doctor((User) neededContent.get("doctor"))
        .service((Service) neededContent.get("service"))
        .date(appointmentDto.getDate())
        .phoneNumber(appointmentDto.getPhoneNumber())
        .price(appointmentDto.getPrice())
        .build();
  }

  public static AppointmentDto appointmentToAppointmentDto(Appointment appointment) {

    return AppointmentDto.builder()
        .id(appointment.getId())
        .creatorId(appointment.getCreator().getId())
        .doctorId(appointment.getDoctor().getId())
        .serviceId(appointment.getService().getId())
        .phoneNumber(appointment.getPhoneNumber())
        .price(appointment.getPrice())
        .service(ServiceMapper.mapServiceToServiceDto(appointment.getService()))
        .creator(UserMapper.mapUserToUserViewDto(appointment.getCreator()))
        .date(appointment.getDate())
        .build();
  }
}
