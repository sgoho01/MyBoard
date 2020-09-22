package com.ghsong.myboard.modules.user.repository;

import com.ghsong.myboard.modules.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {
}