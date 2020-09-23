package com.ghsong.myboard.modules.repository;

import com.ghsong.myboard.modules.entiry.Board;
import com.ghsong.myboard.modules.entiry.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PostRespoitory extends JpaRepository<Post, Long> {
    List<Post> findByBoard(Board board);
}
