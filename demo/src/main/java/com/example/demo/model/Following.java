package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Following")
@IdClass(FollowingId.class)
public class Following {


    // the user who follow
    @Id
    @ManyToOne
    @JoinColumn(name = "follower_id")
    private UserEntity follower;

    @Id
    @ManyToOne
    @JoinColumn(name = "followed_id")
    private UserEntity followed;

}
