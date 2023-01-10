package com.example.docpro.features.review;

import com.example.docpro.features.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

  List<Review> findAllByFacilitator(User facilitator);
}
