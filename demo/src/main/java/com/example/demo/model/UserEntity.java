package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
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

    // ...
    @OneToMany(mappedBy = "follower", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("follower")
    @JsonIgnore
    private List<Following> follow = new ArrayList<>();

    @OneToMany(mappedBy = "followed", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("followed")
    @JsonIgnore
    private List<Following> followers = new ArrayList<>();
// ...


    public void addFollower(Following following){
        this.followers.add(following);
    }

    public void addFollowed(Following following){
        this.follow.add(following);
    }

    public void addRole(Role role){
        this.user_role.add(role);
    }

}
