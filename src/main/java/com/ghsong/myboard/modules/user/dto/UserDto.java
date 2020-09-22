package com.ghsong.myboard.modules.user.dto;

import com.ghsong.myboard.modules.user.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@ApiModel(description = "사용자 정보")
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @ApiModelProperty(value = "사용자 UID", required = true, example = "ghsong")
    private String uid;

    @ApiModelProperty(value = "비밀번호", required = true, example = "1234")
    private String password;

    @ApiModelProperty(value = "사용자 이름", required = true, example = "송건호")
    private String name;

}
