package com.qing.bringit.controller;

import com.qing.bringit.entity.Message;
import com.qing.bringit.entity.Post;
import com.qing.bringit.entity.User;
import com.qing.bringit.repository.MessageRepository;
import com.qing.bringit.repository.PostRepository;
import com.qing.bringit.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    @Autowired private MessageRepository messageRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private PostRepository postRepository;

    @PostMapping("/api/messages")
    public Message sendMessage(@RequestBody Map<String, Object> payload) {
        Long senderId = 1L;
        if (payload.containsKey("senderId") && payload.get("senderId") != null) {
            senderId = Long.valueOf(payload.get("senderId").toString());
        }
        User sender = userRepository.findById(senderId).orElseThrow();
        
        Long receiverId = Long.valueOf(payload.get("receiverId").toString());
        User receiver = userRepository.findById(receiverId).orElseThrow();

        Message message = new Message();
        message.setSender(sender);
        message.setReceiver(receiver);
        message.setContent(payload.get("content").toString());

        if (payload.containsKey("postId") && payload.get("postId") != null) {
            Long postId = Long.valueOf(payload.get("postId").toString());
            Post post = postRepository.findById(postId).orElse(null);
            message.setPost(post);
        }

        return messageRepository.save(message);
    }

    // ✨ 升级版收件箱：不再只查“收到的”，而是查“所有参与的”对话！
    @GetMapping("/api/messages/my")
    public List<Message> getMyMessages(@RequestParam(defaultValue = "1") Long userId) {
        return messageRepository.findBySenderIdOrReceiverIdOrderByCreatedAtAsc(userId, userId);
    }

    @GetMapping("/api/init-max")
    public String initMax() {
        try {
            User max = new User();
            max.setUsername("Max");
            max.setEmail("max@test.de");
            max.setPassword("123");
            max.setAvatarUrl("https://api.dicebear.com/7.x/adventurer/svg?seed=Max");
            max.setCreatedAt(new java.sql.Timestamp(System.currentTimeMillis()));
            userRepository.save(max);

            Post post = new Post();
            post.setUser(max);
            post.setRole("NEED_HELP");
            post.setDeparture("Berlin");
            post.setDestination("Guangzhou");
            post.setTravelDate(LocalDate.now().plusDays(10));
            post.setPrice(new BigDecimal("12.00"));
            post.setCategory("Snacks");
            post.setDescription("能帮忙带点德国零食去广州吗？十分感谢！");
            post.setStatus("AVAILABLE");
            postRepository.save(post);

            return "Max 创建成功！";
        } catch (Exception e) {
            return "Max 已存在！";
        }
    }
}