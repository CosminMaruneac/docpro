package com.example.docpro.features.service;

public class ServiceMapper {

  public static ServiceDto mapServiceToServiceDto(Service service) {

    return ServiceDto.builder()
        .id(service.getId())
        .name(service.getName())
        .price(service.getPrice())
        .build();
  }

  public static Service mapServiceDtoToService(ServiceDto serviceDto) {

    return Service.builder()
        .name(serviceDto.getName())
        .price(serviceDto.getPrice())
        .build();
  }
}
