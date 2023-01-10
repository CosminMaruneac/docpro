package com.example.docpro.features.appointment;

import com.example.docpro.features.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  void deleteAllByCreatorOrDoctor(User creator, User doctor);
}
