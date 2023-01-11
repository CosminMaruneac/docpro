package com.example.docpro.features.service;

import com.example.docpro.features.utils.BadRequestException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ServiceService {

  private final ServiceRepository serviceRepository;


  public List<ServiceDto> getAll() {

    return serviceRepository.findAll().stream()
        .map(ServiceMapper::mapServiceToServiceDto)
        .collect(Collectors.toList());
  }

  public ServiceDto createService(ServiceDto serviceDto) {

    com.example.docpro.features.service.Service service = ServiceMapper.mapServiceDtoToService(serviceDto);

    return ServiceMapper.mapServiceToServiceDto(serviceRepository.save(service));
  }

  public void deleteService(Long id) {

    if (Boolean.FALSE.equals(serviceRepository.existsById(id)))
      throw new BadRequestException("This service does not exist!");

    serviceRepository.deleteById(id);
  }

  public ServiceDto getById(Long id) {

    return serviceRepository.findById(id)
        .map(ServiceMapper::mapServiceToServiceDto)
        .orElseThrow(EntityExistsException::new);
  }
}
