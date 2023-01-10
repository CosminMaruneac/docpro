package com.example.docpro.features.user;

import com.example.docpro.features.service.Service;
import com.example.docpro.features.user_experience.UserExperience;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@Table(name = "account")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", nullable = false)
  private Long id;

  private String firstName;

  private String lastName;

  private String address;

  private String phoneNumber;

  @Enumerated(EnumType.STRING)
  private UserType userType;

  @Enumerated(EnumType.STRING)
  private UserSpecialization userSpecialization;

  private String password;

  private String email;

  private String profileImageUrl;

  @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
  private Set<UserExperience> userExperience = new HashSet<>();

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
  @JoinTable(name = "user_service",
      joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
  @ToString.Exclude
  private Set<Service> services = new HashSet<>();
}
