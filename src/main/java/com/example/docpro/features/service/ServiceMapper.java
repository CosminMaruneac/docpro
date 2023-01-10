package com.example.docpro.features.service;

public class ServiceMapper {

  public static ServiceDto mapServiceToServiceDto(Service service) {

    return ServiceDto.builder()
        .id(service.getId())
        .serviceType(service.getServiceType())
        .price(service.getPrice())
        .build();
  }

  public static Service mapServiceDtoToService(ServiceDto serviceDto) {

    return Service.builder()
        .serviceType(serviceDto.getServiceType())
        .price(serviceDto.getPrice())
        .build();
  }
}
