package com.ghsong.myboard.modules.service;

import com.ghsong.myboard.advice.exception.CNotOwnerException;
import com.ghsong.myboard.advice.exception.CResourceNotExistsException;
import com.ghsong.myboard.advice.exception.CUserNotFoundException;
import com.ghsong.myboard.modules.entiry.Board;
import com.ghsong.myboard.modules.entiry.Post;
import com.ghsong.myboard.modules.entiry.User;
import com.ghsong.myboard.modules.entiry.dto.PostDto;
import com.ghsong.myboard.modules.repository.BoardRepository;
import com.ghsong.myboard.modules.repository.PostRespoitory;
import com.ghsong.myboard.modules.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final PostRespoitory postRespoitory;
    private final UserRepository userRepository;

    /**
     * 게시판 이름으로 게시판 검색
     *
     * @param name
     * @return
     */
    public Board getBoard(String name) {
        return boardRepository.findByName(name).orElseThrow(CResourceNotExistsException::new);
    }

    /**
     * 게시판 이름으로 게시글 리스트 조외
     *
     * @param boardName
     * @return
     */
    public List<Post> getPosts(String boardName) {
        return postRespoitory.findByBoard(getBoard(boardName));
    }

    /**
     * 게시글 id로 게시글 조회
     *
     * @param postSeq
     * @return
     */
    public Post getPost(long postSeq) {
        return postRespoitory.findById(postSeq).orElseThrow(CResourceNotExistsException::new);
    }
    public Post getPost(long postSeq, String boardName) {
        getBoard(boardName);
        return getPost(postSeq);
    }

    /**
     * 게시글 등록
     *
     * @param postDto
     * @param boardName
     * @param uid
     * @return
     */
    public Post savePost(PostDto postDto, String boardName, String uid) {
        Board board = getBoard(boardName);
        Post post = Post.builder()
                .user(userRepository.findByUid(uid).orElseThrow(CUserNotFoundException::new))
                .board(board)
                .author(postDto.getAuthor())
                .title(postDto.getTitle())
                .content(postDto.getContent())
                .build();
        return postRespoitory.save(post);
    }

    /**
     * 게시글 수정
     *
     * @param postDto
     * @param postSeq
     * @param uid
     * @return
     */
    public Post updatePost(PostDto postDto, long postSeq, String uid) {
        Post post = getPost(postSeq);
        User postUser = post.getUser();
        if (!uid.equals(postUser.getUid()))
            throw new CNotOwnerException();
        post.setUpdate(postDto);
        return post;
    }
    public Post updatePost(PostDto postDto, long postSeq, String uid, String boardName) {
        getBoard(boardName);
        return updatePost(postDto, postSeq, uid);
    }

    /**
     * 게시글 삭제
     *
     * @param postSeq
     * @param uid
     * @return
     */
    public boolean deletePost(long postSeq, String uid) {
        Post post = getPost(postSeq);
        User postUser = post.getUser();
        if (!uid.equals(postUser.getUid()))
            throw new CNotOwnerException();
        postRespoitory.delete(post);
        return true;
    }
    public boolean deletePost(long postSeq, String uid, String boardName) {
        getBoard(boardName);
        return deletePost(postSeq, uid);
    }


}
