package com.example.demo.service;

import com.example.demo.exception.DataIntegrityException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.exception.ValidationException;
import com.example.demo.model.Following;
import com.example.demo.model.UserEntity;
import com.example.demo.reposetories.FollowingRepository;
import com.example.demo.reposetories.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Service
public class FollowingService {

    @Autowired
    private FollowingRepository followingRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    public List<Following> getAll() {
        return followingRepository.findAll();
    }

    public Following add(@Valid Following following) {
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

    public List<UserEntity> getFollowersOfUser(long userId) {
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

    public List<UserEntity> getWhoUserFollow(long userId) {
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

    public ResponseEntity<Following> getByCompositeIds(UserEntity follower, UserEntity followed) {
        Following following = followingRepository.findByFollowerAndFollowed(follower, followed)
                .orElseThrow(() -> new ResourceNotFoundException("Following not found for the given composite IDs"));

        return ResponseEntity.ok(following);
    }

    public ResponseEntity<Following> follow(long follower, long followed) {
        UserEntity user_follower = userEntityRepository.findById(follower).orElseThrow(() ->
                new ResourceNotFoundException("user not exists with id: " + follower));
        UserEntity user_followed = userEntityRepository.findById(followed).orElseThrow(() ->
                new ResourceNotFoundException("user not exists with id: " + followed));

        if (followingRepository.existsByFollowerAndFollowed(user_follower, user_followed)) {
            throw new DataIntegrityException("the user of id: " + follower + " is already folow the user of id: " + followed);
        } else {
            Following following = new Following();
            following.setFollower(user_follower);
            following.setFollowed(user_followed);


            followingRepository.save(following);

            return ResponseEntity.ok(following);
        }
    }
}
