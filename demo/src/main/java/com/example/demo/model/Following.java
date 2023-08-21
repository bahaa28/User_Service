package com.example.demo.model;

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

    // the user who had been followed
    @Id
    @ManyToOne
    @JoinColumn(name = "followed_id")
    private UserEntity followed;

    @Column(name = "folowingDate")
    @NotNull(message = "folowing date must not be null")
    private Date folowingDate;

}
