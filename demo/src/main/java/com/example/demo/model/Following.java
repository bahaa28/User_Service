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
public class Following {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "folowingDate")
    @NotNull(message = "folowing date must not be null")
    private Date folowingDate;

    @ManyToOne
    @JoinColumn(name = "follower_id", referencedColumnName = "id")
    private UserEntity follower;

    @ManyToOne
    @JoinColumn(name = "followed_id", referencedColumnName = "id")
    private UserEntity followed;

}
