package com.example.docpro.features.service;

import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDto {

  private Long id;

  @Enumerated(EnumType.STRING)
  private ServiceType serviceType;

  private Integer price;
}
