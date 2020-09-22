package com.ghsong.myboard.modules.user.controller;

import com.ghsong.myboard.config.response.ListResult;
import com.ghsong.myboard.config.response.SingleResult;
import com.ghsong.myboard.modules.common.service.ResponseService;
import com.ghsong.myboard.modules.user.User;
import com.ghsong.myboard.modules.user.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "사용자 API")
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor
public class UserController {

    private final ResponseService responseService;
    private final UserRepository userRepository;

    @ApiOperation(value = "사용자리스트 조회", notes = "사용자 전체를 조회한다.")
    @GetMapping
    public ListResult getUserList() {
        return responseService.getListResult(userRepository.findAll());
    }

    @ApiOperation(value = "사용자 조회", notes = "특정 사용자 한명을 조회한다.")
    @GetMapping("/{id}")
    public SingleResult getUser(@ApiParam(value = "회원 id", required = true) @PathVariable long id) {
        return responseService.getSingleResult(userRepository.findById(id).orElse(null));
    }

}
