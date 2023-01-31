package com.example.docpro.features.utils;

import com.example.docpro.features.service.ServiceDto;
import com.example.docpro.features.service.ServiceService;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CacheService {

  private final ServiceService serviceService;

  public CacheService(ServiceService serviceService) {
    this.serviceService = serviceService;
  }

  @Cacheable("services")
  public List<ServiceDto> getServices() {
    return serviceService.getAll();
  }

  @CacheEvict(value = "services", allEntries = true)
  public void deleteCache() {
  }
}
