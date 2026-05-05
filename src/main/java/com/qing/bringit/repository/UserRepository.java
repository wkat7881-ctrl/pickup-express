package com.qing.bringit.repository;

import com.qing.bringit.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 只要继承了 JpaRepository，你就自动拥有了保存、删除、查找用户的所有基本能力！
    // 以后如果我们需要“根据邮箱查找用户”，可以在这里加一句代码，现在先空着。
}
