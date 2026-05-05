package com.qing.bringit.repository;

import com.qing.bringit.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    
    // ✨ 终极魔法：我们只是写了一个长长的方法名，连花括号都不需要！
    // 翻译：寻找 (find) 通过 (By) 出发地 (Departure) 包含 (Containing) 忽略大小写 (IgnoreCase) 
    // 或者 (Or) 目的地 (Destination) 包含 (Containing) 忽略大小写 (IgnoreCase) 的所有帖子！
    List<Post> findByDepartureContainingIgnoreCaseOrDestinationContainingIgnoreCase(String departure, String destination);
 // 新增魔法：找出某个用户发的所有帖子
    List<Post> findByUserIdOrderByCreatedAtDesc(Long userId);
}