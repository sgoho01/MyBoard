package com.ghsong.myboard.modules.repository;

import com.ghsong.myboard.modules.entiry.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUid(String uid);

}
