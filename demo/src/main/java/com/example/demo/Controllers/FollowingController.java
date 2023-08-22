package com.example.demo.Controllers;

import com.example.demo.model.Following;
import com.example.demo.model.UserEntity;
import com.example.demo.service.FollowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/followings")
public class FollowingController {

    @Autowired
    private FollowingService followingService;

    @GetMapping
    public List<Following> getall(){
        return followingService.getAll();
    }

    @PostMapping
    public Following add(@RequestBody Following following){
        return followingService.add(following);
    }

    @GetMapping("followers/{followerId}")
    public List<Following> getByFollowerId(@PathVariable long followerId){
        return followingService.getByFollowerId(followerId);
    }

    @GetMapping("follows/{followedId}")
    public List<Following> getByFollowedId(@PathVariable long followedId){
        return followingService.getByFollowedId(followedId);
    }

    @GetMapping("followers/user/{user_id}")
    public List<UserEntity> getFollowersOfUser(@PathVariable long user_id){
        return followingService.getFollowersOfUser(user_id);
    }

    @GetMapping("follows/user/{user_id}")
    public List<UserEntity> getWhoUserFollow(@PathVariable long user_id){
        return followingService.getWhoUserFollow(user_id);
    }

    @GetMapping("folower/{follower_id}/followed/{followed_id}")
    public ResponseEntity<Following> getByCompositeIds(
            @PathVariable UserEntity follower_id,
            @PathVariable UserEntity followed_id
    ) {
       return followingService.getByCompositeIds(follower_id, followed_id);
    }

    @PutMapping("follower/{follower_id}/followed/{followed_id}")
    public ResponseEntity<Following> follow(
            @PathVariable long follower_id,
            @PathVariable long followed_id
    ){
        return followingService.follow(follower_id, followed_id);
    }

}
