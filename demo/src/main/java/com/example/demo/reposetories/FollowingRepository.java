package com.example.demo.reposetories;

import com.example.demo.model.Following;
import com.example.demo.model.FollowingId;
import com.example.demo.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowingRepository extends JpaRepository<Following, FollowingId> {
    List<Following> findByFollower_Id(Long followerId);

    List<Following> findByFollowed_Id(Long followedId);

    Optional<Following> findByFollowerAndFollowed(UserEntity follower, UserEntity followed);
    boolean existsByFollowerAndFollowed(UserEntity follower, UserEntity followed);

}
