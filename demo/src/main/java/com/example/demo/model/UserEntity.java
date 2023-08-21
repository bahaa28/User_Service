package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    @NotNull(message = "username must not be null")
    @NotEmpty(message = "username must not be empty")
    private String username;

    @Column(name = "password")
    @NotNull(message = "password must not be null")
    @NotEmpty(message = "password must not be empty")
    private String password;

    @Column(name = "firstName")
    @NotNull(message = "first name must not be null")
    @NotEmpty(message = "first name must not be empty")
    private String firstName;

    @Column(name = "lastName")
    @NotNull(message = "last name must not be null")
    @NotEmpty(message = "last name must not be empty")
    private String lastName;

    @Column(name = "birthDate")
    @NotNull(message = "birth date must not be null")
    private Date birthDate;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<Role> user_role = new ArrayList<Role>();

    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Following> follow;

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Following> followers;


}
