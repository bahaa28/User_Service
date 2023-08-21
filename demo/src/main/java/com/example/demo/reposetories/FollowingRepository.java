package com.example.demo.reposetories;

import com.example.demo.model.Following;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {
    List<Following> findByFollower_Id(Long followerId);

    List<Following> findByFollowed_Id(Long followedId);

}
