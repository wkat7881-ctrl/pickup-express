package com.qing.bringit.controller;

import com.qing.bringit.entity.Post;
import com.qing.bringit.entity.Review;
import com.qing.bringit.entity.User;
import com.qing.bringit.repository.PostRepository;
import com.qing.bringit.repository.ReviewRepository;
import com.qing.bringit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    // 通道 1：获取用户的基本信息 (比如查询 ID 为 1 的用户)
    @GetMapping("/api/users/{id}")
    public User getUserProfile(@PathVariable Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    // 通道 2：获取该用户发布过的所有历史帖子
    @GetMapping("/api/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable Long id) {
        return postRepository.findByUserIdOrderByCreatedAtDesc(id);
    }

    // 通道 3：获取别人对该用户的评价
    @GetMapping("/api/users/{id}/reviews")
    public List<Review> getUserReviews(@PathVariable Long id) {
        return reviewRepository.findByRevieweeIdOrderByCreatedAtDesc(id);
    }
    
    // (隐藏功能)：顺手写一个给别人打分的接口，以后前端留评用得上
    @PostMapping("/api/users/{id}/reviews")
    public Review leaveReview(@PathVariable Long id, @RequestBody Map<String, Object> payload) {
        User reviewer = userRepository.findById(1L).orElseThrow(); // 假设当前登录的是 1号
        User reviewee = userRepository.findById(id).orElseThrow(); // 被评价的人
        
        Review review = new Review();
        review.setReviewer(reviewer);
        review.setReviewee(reviewee);
        review.setRating(Integer.valueOf(payload.get("rating").toString()));
        review.setComment(payload.get("comment").toString());
        
        return reviewRepository.save(review);
        
        
    }
    
 // ✨ 新增接口：用户注册 (MVP极简版)
    @PostMapping("/api/users/register")
    public User registerUser(@RequestBody User newUser) {
        // 在真实的商业项目里，这里的密码需要被加密 (Hash)。
        // 为了我们目前的 MVP 跑通，我们先直接把新用户存进数据库！
        newUser.setAvatarUrl("https://api.dicebear.com/7.x/adventurer/svg?seed=" + newUser.getUsername());
        newUser.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
        return userRepository.save(newUser);
    }

    // ✨ 新增接口：修改个人资料 (比如更新 Bio 签名、用户名)
    @PutMapping("/api/users/{id}")
    public User updateUserProfile(@PathVariable Long id, @RequestBody User updatedInfo) {
        // 1. 先从数据库里把老资料找出来
        User existingUser = userRepository.findById(id).orElseThrow();
        
        // 2. 把传过来的新信息覆盖上去
        if (updatedInfo.getBio() != null) {
            existingUser.setBio(updatedInfo.getBio());
        }
        if (updatedInfo.getUsername() != null) {
            existingUser.setUsername(updatedInfo.getUsername());
        }
        
        // 3. 重新存回数据库
        return userRepository.save(existingUser);
    }
}