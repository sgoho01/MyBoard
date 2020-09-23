package com.ghsong.myboard.modules.entiry;

import com.ghsong.myboard.modules.entiry.dto.PostDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "post")
public class Post extends CommonDateEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postSeq;
    @Column(nullable = false, length = 50)
    private String author;
    @Column(nullable = false, length = 100)
    private String title;
    @Column(length = 500)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_seq")
    private Board board;                    // 게시글 - 게시판의 관계  - N:1

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq")
    private User user;                      // 게시글 - 회원의 관계 - N:1


    protected Board getBoard() {
        return this.board;
    }


    public void setUpdate(PostDto postDto) {
        this.author = postDto.getAuthor();
        this.title = postDto.getTitle();
        this.content = postDto.getContent();
    }

}
