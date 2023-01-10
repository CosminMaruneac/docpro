package com.example.docpro.features.service;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServiceDto {

  private Long id;

  private String name;

  private Integer price;
}
