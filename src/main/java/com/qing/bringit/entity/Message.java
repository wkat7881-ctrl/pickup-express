package com.qing.bringit.entity;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 谁发的消息？
    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender;

    // 发给谁的？
    @ManyToOne
    @JoinColumn(name = "receiver_id", nullable = false)
    private User receiver;

    // 是关于哪个帖子的咨询？
    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // 消息的具体内容
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    // 什么时候发的？
    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    // --- 下面是枯燥但必须有的 Getters 和 Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getSender() { return sender; }
    public void setSender(User sender) { this.sender = sender; }
    public User getReceiver() { return receiver; }
    public void setReceiver(User receiver) { this.receiver = receiver; }
    public Post getPost() { return post; }
    public void setPost(Post post) { this.post = post; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}
