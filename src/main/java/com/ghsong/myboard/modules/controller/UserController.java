package com.ghsong.myboard.modules.controller;

import com.ghsong.myboard.advice.exception.CUserNotFoundException;
import com.ghsong.myboard.config.response.CommonResult;
import com.ghsong.myboard.config.response.ListResult;
import com.ghsong.myboard.config.response.SingleResult;
import com.ghsong.myboard.modules.entiry.dto.UserDto;
import com.ghsong.myboard.modules.repository.UserRepository;
import com.ghsong.myboard.modules.service.ResponseService;
import com.ghsong.myboard.modules.service.UserService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = "사용자 API")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;
    private final ResponseService responseService;

    @ApiOperation(value = "사용자리스트 조회", notes = "사용자 전체를 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 받은 access-token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping
    public ListResult getUserList() {
        return responseService.getListResult(userRepository.findAll());
    }

    @ApiOperation(value = "사용자 조회", notes = "특정 사용자 한명을 조회한다.")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 받은 access-token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/{id}")
    public SingleResult getUser(@ApiParam(value = "사용자 userSeq", required = true) @PathVariable long id) {
        return responseService.getSingleResult(userRepository.findById(id).orElseThrow(CUserNotFoundException::new));
    }

    @ApiOperation(value = "사용자 수정", notes = "사용자 정보를 수정한다.")
    @PutMapping("/{id}")
    public SingleResult updateUser(@ApiParam(value = "사용자 userSeq", required = true) @PathVariable long id,
                                   @RequestBody UserDto userDto) throws Exception {
        return responseService.getSingleResult(userService.updateUser(id, userDto));
    }

    @ApiOperation(value = "사용자 제거", notes = "사용자를 삭제한다.")
    @DeleteMapping("/{id}")
    public CommonResult deleteUser(@ApiParam(value = "사용자 userSeq", required = true) @PathVariable long id) throws Exception {
        userService.deleteUser(id);
        return responseService.getSuccessResult();
    }

}
