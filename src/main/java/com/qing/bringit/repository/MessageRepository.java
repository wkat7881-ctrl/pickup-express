package com.qing.bringit.repository;

import com.qing.bringit.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    // ✨ 全新魔法：找出跟我有关的所有消息（不管我是发件人还是收件人）
    // 并且按照时间正序排列 (Asc)，因为聊天界面的习惯是：最老的在上面，最新的在最下面！
    List<Message> findBySenderIdOrReceiverIdOrderByCreatedAtAsc(Long senderId, Long receiverId);
}