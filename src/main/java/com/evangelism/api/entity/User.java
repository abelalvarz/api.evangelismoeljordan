package com.evangelism.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "user", schema = "evangelism")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String firebaseId;
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phone;

    @Column(name = "password", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "status", nullable = false)
    private String status = "ACTIVE"; // ACTIVE, INACTIVE

    @Column(name = "last_logged_in")
    private LocalDateTime lastLoggedIn;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles; // ADMIN, TEACHER, SECRETARY

    public Set<String> getRolesList(){
        return roles.stream().map(Enum::name).collect(Collectors.toSet());
    }

    public boolean isAdmin(){
        return this.getRoles().contains(Role.ADMIN);
    }
    public boolean isTeacher(){
        return this.getRoles().contains(Role.TEACHER);
    }
    public boolean isSecretary(){
        return this.getRoles().contains(Role.SECRETARY);
    }
    public String getFullName(){
        return firstName + " " + lastName;
    }
}
