package com.example.demo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;

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
