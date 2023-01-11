package com.example.docpro.features.appointment;

import com.example.docpro.features.service.ServiceDto;
import com.example.docpro.features.user.UserDto;
import com.example.docpro.features.user.UserViewDto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppointmentDto {

  private Long id;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime date;

  private String phoneNumber;

  private Integer price;

  private ServiceDto service;

  private Long serviceId;

  private UserViewDto creator;

  private UserDto doctor;

  private Long doctorId;

  private Long creatorId;
}
