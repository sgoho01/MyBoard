package com.ghsong.myboard.modules.user.controller;

import com.ghsong.myboard.advice.exception.CUserNotFoundException;
import com.ghsong.myboard.config.response.CommonResult;
import com.ghsong.myboard.config.response.ListResult;
import com.ghsong.myboard.config.response.SingleResult;
import com.ghsong.myboard.modules.common.service.ResponseService;
import com.ghsong.myboard.modules.user.dto.UserDto;
import com.ghsong.myboard.modules.user.repository.UserRepository;
import com.ghsong.myboard.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    @GetMapping
    public ListResult getUserList() {
        return responseService.getListResult(userRepository.findAll());
    }

    @ApiOperation(value = "사용자 조회", notes = "특정 사용자 한명을 조회한다.")
    @GetMapping("/{id}")
    public SingleResult getUser(@ApiParam(value = "사용자 id", required = true) @PathVariable long id) {
        return responseService.getSingleResult(userRepository.findById(id).orElseThrow(CUserNotFoundException::new));
    }

    @ApiOperation(value = "사용자 등록", notes = "사용자를 등록한다.")
    @PostMapping
    public SingleResult saveUser(@RequestBody UserDto userDto) {
        return responseService.getSingleResult(userService.saveUser(userDto));
    }

    @ApiOperation(value = "사용자 수정", notes = "사용자 정보를 수정한다.")
    @PutMapping("/{id}")
    public SingleResult updateUser(@ApiParam(value = "사용자 id", required = true) @PathVariable long id,
                                   @RequestBody UserDto userDto) throws Exception {
        return responseService.getSingleResult(userService.updateUser(id, userDto));
    }

    @ApiOperation(value = "사용자 제거", notes = "사용자를 삭제한다.")
    @DeleteMapping("/{id}")
    public CommonResult deleteUser(@ApiParam(value = "사용자 id", required = true) @PathVariable long id) throws Exception {
        userService.deleteUser(id);
        return responseService.getSuccessResult();
    }

}
