package com.example.docpro.features.appointment;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/appointments")
@AllArgsConstructor
public class AppointmentController {

  private final AppointmentService appointmentService;

  @PostMapping(path = "")
  public AppointmentDto create(@RequestBody AppointmentDto appointmentDto) throws MessagingException {
    return appointmentService.create(appointmentDto);
  }

  @GetMapping(path = "")
  public List<AppointmentDto> getAll(){

    return appointmentService.getAll();
  }
}
