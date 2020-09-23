package com.ghsong.myboard.modules.entiry.dto;

import com.ghsong.myboard.modules.entiry.Board;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@ApiModel(description = "게시글 정보")
@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    @NotEmpty
    @Size(min = 2, max = 50)
    @ApiModelProperty(value = "작성자", required = true)
    private String author;
    @NotEmpty
    @Size(min = 2, max = 100)
    @ApiModelProperty(value = "제목", required = true)
    private String title;
    @NotEmpty
    @Size(min = 2, max = 500)
    @ApiModelProperty(value = "내용", required = true)
    private String content;

}
