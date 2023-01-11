package com.example.docpro.features.service;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/services")
@AllArgsConstructor
public class ServiceController {

  private final ServiceService serviceService;

  @PostMapping(path = "")
  public ServiceDto createService(@RequestBody ServiceDto serviceDto) {
    return serviceService.createService(serviceDto);
  }

  @DeleteMapping(path = "/{id}")
  public void deleteService(@PathVariable(name = "id") Long id) {
    serviceService.deleteService(id);
  }

  @GetMapping(path = "/{id}")
  public ServiceDto getById(@PathVariable(name = "id") Long id) {
    return serviceService.getById(id);
  }

  @GetMapping(path = "")
  public List<ServiceDto> getAll() {

    return serviceService.getAll();
  }
}
