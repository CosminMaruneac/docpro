package com.example.docpro.features.service;

import com.example.docpro.features.utils.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/services")
@AllArgsConstructor
public class ServiceController {

  private final ServiceService serviceService;
  private final CacheService cacheService;

  @PostMapping(path = "")
  public ServiceDto createService(@RequestBody ServiceDto serviceDto) {

    ServiceDto service = serviceService.createService(serviceDto);

    cacheService.deleteCache();
    cacheService.getServices();

    return service;
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

    return cacheService.getServices();
  }
}
