package com.example.docpro.features.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface ServiceRepository extends JpaRepository<Service, Long> {

  Set<Service> findAllByIdIn(List<Long> ids);
}
