package com.qing.bringit.repository;

import com.qing.bringit.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 魔法查询：找出针对某个人的所有评价，并按时间倒序排列
    List<Review> findByRevieweeIdOrderByCreatedAtDesc(Long revieweeId);
}