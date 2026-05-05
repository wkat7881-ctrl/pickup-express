package com.qing.bringit.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.sql.Timestamp;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String type;

    // ✨ 新增核心字段：角色！"NEED_HELP" (找人帮带) 或 "PROVIDE_HELP" (我可以带)
    @Column(nullable = false)
    private String role = "NEED_HELP"; 

    @Column(nullable = false)
    private String departure;

    @Column(nullable = false)
    private String destination;

    private LocalDate travelDate;
    private BigDecimal price;
    private String category;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String status = "AVAILABLE";

    private Timestamp createdAt = new Timestamp(System.currentTimeMillis());

    // --- 枯燥的 Getters and Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getRole() { return role; } 
    public void setRole(String role) { this.role = role; } 
    public String getDeparture() { return departure; }
    public void setDeparture(String departure) { this.departure = departure; }
    public String getDestination() { return destination; }
    public void setDestination(String destination) { this.destination = destination; }
    public LocalDate getTravelDate() { return travelDate; }
    public void setTravelDate(LocalDate travelDate) { this.travelDate = travelDate; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public Timestamp getCreatedAt() { return createdAt; }
    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }
}