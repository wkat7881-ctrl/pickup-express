package com.qing.bringit.controller;

import com.qing.bringit.entity.Post;
import com.qing.bringit.entity.User;
import com.qing.bringit.repository.PostRepository;
import com.qing.bringit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/api/posts")
    public List<Post> getAllPosts(@RequestParam(required = false) String keyword) {
        if (keyword != null && !keyword.trim().isEmpty()) {
            return postRepository.findByDepartureContainingIgnoreCaseOrDestinationContainingIgnoreCase(keyword, keyword);
        }
        return postRepository.findAll();
    }

    @GetMapping("/api/init")
    public String initData() {
        if (userRepository.count() > 0) {
            return "数据库里已经有数据啦，你的数据现在非常安全！";
        }

        User user = new User();
        user.setUsername("Qing");
        user.setEmail("qing@example.com");
        user.setPassword("secret123");
        user.setBio("A very reliable student from Augsburg!");
        user.setAvatarUrl("https://api.dicebear.com/7.x/adventurer/svg?seed=Qing");
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);

        Post post1 = new Post();
        post1.setUser(user);
        post1.setRole("NEED_HELP");
        post1.setDeparture("Augsburg");
        post1.setDestination("Shanghai");
        post1.setTravelDate(LocalDate.now());
        post1.setPrice(new BigDecimal("15.00"));
        post1.setCategory("Documents");
        post1.setDescription("急需帮忙带一份文件回国！");
        post1.setStatus("AVAILABLE");
        postRepository.save(post1);

        Post post2 = new Post();
        post2.setUser(user);
        post2.setRole("PROVIDE_HELP");
        post2.setDeparture("Beijing");
        post2.setDestination("Munich");
        post2.setTravelDate(LocalDate.now());
        post2.setPrice(new BigDecimal("20.00"));
        post2.setCategory("Luggage Space");
        post2.setDescription("箱子还有空余，可以帮忙带一些不重的东西回德国。");
        post2.setStatus("AVAILABLE");
        postRepository.save(post2);

        return "双向魔法生效！初始数据已成功存入硬盘！";
    }

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody Post post) {
        // ✨ 升级版：动态获取当前发帖人的 ID (前端切换账号时会传过来)
        Long userId = 1L; // 默认保底是 1 号
        if (post.getUser() != null && post.getUser().getId() != null) {
            userId = post.getUser().getId();
        }
        
        User currentUser = userRepository.findById(userId).orElseThrow();
        post.setUser(currentUser);
        post.setStatus("AVAILABLE");
        if (post.getRole() == null) {
            post.setRole("NEED_HELP");
        }
        return postRepository.save(post); 
    }

    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post updatedPost) {
        Post existingPost = postRepository.findById(id).orElseThrow();
        if (updatedPost.getDeparture() != null) existingPost.setDeparture(updatedPost.getDeparture());
        if (updatedPost.getDestination() != null) existingPost.setDestination(updatedPost.getDestination());
        if (updatedPost.getPrice() != null) existingPost.setPrice(updatedPost.getPrice());
        if (updatedPost.getCategory() != null) existingPost.setCategory(updatedPost.getCategory());
        if (updatedPost.getDescription() != null) existingPost.setDescription(updatedPost.getDescription());
        if (updatedPost.getStatus() != null) existingPost.setStatus(updatedPost.getStatus());
        if (updatedPost.getRole() != null) existingPost.setRole(updatedPost.getRole());
        return postRepository.save(existingPost);
    }
}