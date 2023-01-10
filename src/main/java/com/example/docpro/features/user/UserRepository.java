package com.example.docpro.features.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  Boolean existsByEmail(String email);

  User findByEmail(String email);

  List<User> findAllByUserSpecialization(UserSpecialization specialization);

  List<User> findAllByFirstNameLikeAndLastNameLike(String firstName, String lastName);
}
