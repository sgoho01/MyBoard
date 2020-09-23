package com.ghsong.myboard.modules.repository;

import com.ghsong.myboard.modules.entiry.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional(readOnly = true)
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByName(String name);
}
