package com.example.demo.service;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.Following;
import com.example.demo.model.UserEntity;
import com.example.demo.reposetories.FollowingRepository;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FollowingService {

    @Autowired
    private FollowingRepository followingRepository;

    public List<Following> getAll(){
        return followingRepository.findAll();
    }

    public Following add(@Valid Following following){
        return followingRepository.save(following);
    }

    public List<Following> getByFollowerId(Long followerId) {
        List<Following> followings = followingRepository.findByFollower_Id(followerId);
        if (followings.isEmpty()) {
            throw new ResourceNotFoundException("No followings found for follower with id: " + followerId);
        }
        return followings;
    }

    public List<Following> getByFollowedId(Long followedId) {
        List<Following> followings = followingRepository.findByFollowed_Id(followedId);
        if (followings.isEmpty()) {
            throw new ResourceNotFoundException("No followings found for followed user with id: " + followedId);
        }
        return followings;
    }

    public List<UserEntity> getFollowersOfUSer(Long userId) {
        List<Following> followings = followingRepository.findByFollowed_Id(userId);

        if (followings.isEmpty()) {
            throw new ResourceNotFoundException("no followers for the user with id: " + userId);
        }

        List<UserEntity> usersFollowing = new ArrayList<UserEntity>();
        for (Following following : followings) {
            usersFollowing.add(following.getFollower());
        }

        return usersFollowing;
    }

    public List<UserEntity> getWHoUserFollow(Long userId) {
        List<Following> followings = followingRepository.findByFollower_Id(userId);

        if (followings.isEmpty()) {
            throw new ResourceNotFoundException("The user with id: " + userId + " does not follow any other users");
        }

        List<UserEntity> usersFollowing = new ArrayList<UserEntity>();
        for (Following following : followings) {
            usersFollowing.add(following.getFollowed());
        }

        return usersFollowing;
    }
}
