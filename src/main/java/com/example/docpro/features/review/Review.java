package com.example.docpro.features.review;

import com.example.docpro.features.user.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table
@Builder
public class Review {


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "creator_id")
  private User creator;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "facilitator_id")
  private User facilitator;

  private String description;

  private Integer stars;

  private LocalDate createdAt;
}
