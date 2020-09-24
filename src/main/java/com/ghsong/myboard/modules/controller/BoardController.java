package com.ghsong.myboard.modules.controller;

import com.ghsong.myboard.config.response.ListResult;
import com.ghsong.myboard.config.response.SingleResult;
import com.ghsong.myboard.modules.entiry.Board;
import com.ghsong.myboard.modules.entiry.dto.PostDto;
import com.ghsong.myboard.modules.repository.BoardRepository;
import com.ghsong.myboard.modules.repository.PostRespoitory;
import com.ghsong.myboard.modules.service.BoardService;
import com.ghsong.myboard.modules.service.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = "게시판 API")
@RestController
@RequestMapping(value = "/board")
@RequiredArgsConstructor
public class BoardController {

    private final ResponseService responseService;

    private final BoardService boardService;
    private final BoardRepository boardRepository;

    private final PostRespoitory postRespoitory;

    // 게시판 정보 조회
    @ApiOperation(value = "게시판 정보 조회", notes = "게시판 정보를 조회합니다.")
    @GetMapping("/{boardName}")
    public SingleResult getBoardInfo(@ApiParam(value = "게시판 이름", required = true, defaultValue = "free") @PathVariable String boardName) {
        return responseService.getSingleResult(boardService.getBoard(boardName));
    }

    // 게시판 글 리스트 조회
    @ApiOperation(value = "게시글 리스트", notes = "게시글 리스트를 조회한다.")
    @GetMapping("/{boardName}/posts")
    public ListResult getPosts(@ApiParam(value = "게시판 이름", required = true, defaultValue = "free") @PathVariable String boardName) {
        return responseService.getListResult(boardService.getPosts(boardName));
    }

    // 게시글 작성
    @ApiOperation(value = "게시글 작성", notes = "게시글을 작성한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 받은 access-token", required = true, dataType = "String", paramType = "header")
    })
    @PostMapping("/{boardName}/posts")
    public SingleResult savePost(@ApiParam(value = "게시판 이름", required = true, defaultValue = "free") @PathVariable String boardName,
                                  @RequestBody PostDto postDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardService.savePost(postDto, boardName, uid));
    }

    // 게시글 상세보기
    @ApiOperation(value = "게시글 상세조회", notes = "게시글 상세내용을 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 받은 access-token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/{boardName}/posts/{postSeq}")
    public SingleResult getPost(@ApiParam(value = "게시판 이름", required = true, defaultValue = "free") @PathVariable String boardName,
                                @ApiParam(value = "게시글 번호", required = true) @PathVariable long postSeq) {
        return responseService.getSingleResult(boardService.getPost(postSeq, boardName));
    }

    // 게시글 수정
    @ApiOperation(value = "게시글 수정", notes = "게시글을 수정한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 받은 access-token", required = true, dataType = "String", paramType = "header")
    })
    @PutMapping("/{boardName}/posts/{postSeq}")
    public SingleResult updatePost(@ApiParam(value = "게시판 이름", required = true, defaultValue = "free") @PathVariable String boardName,
                                   @ApiParam(value = "게시글 번호", required = true) @PathVariable long postSeq,
                                   @RequestBody PostDto postDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardService.updatePost(postDto, postSeq, uid, boardName));
    }


    // 게시글 삭제
    @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 받은 access-token", required = true, dataType = "String", paramType = "header")
    })
    @DeleteMapping("/{boardName}/posts/{postSeq}")
    public SingleResult deletePost(@ApiParam(value = "게시판 이름", required = true, defaultValue = "free") @PathVariable String boardName,
                                   @ApiParam(value = "게시글 번호", required = true) @PathVariable long postSeq) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String uid = authentication.getName();
        return responseService.getSingleResult(boardService.deletePost(postSeq, uid, boardName));
    }
}
