package com.ghsong.myboard.modules.user.controller;

import com.ghsong.myboard.advice.exception.CSignInFailedException;
import com.ghsong.myboard.config.response.SingleResult;
import com.ghsong.myboard.config.security.JwtTokenProvider;
import com.ghsong.myboard.modules.common.service.ResponseService;
import com.ghsong.myboard.modules.user.User;
import com.ghsong.myboard.modules.user.dto.UserDto;
import com.ghsong.myboard.modules.user.repository.UserRepository;
import com.ghsong.myboard.modules.user.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"로그인 API"})
@RestController
@RequiredArgsConstructor
public class SignController {

    private final UserService userService;
    private final UserRepository userRepository;
    private final ResponseService responseService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @ApiOperation(value = "회원가입", notes = "회원가입을 처리한다.")
    @PostMapping("/signup")
    public SingleResult signup(@RequestBody UserDto userDto) {
        return responseService.getSingleResult(userService.saveUser(userDto));
    }

    @ApiOperation(value = "로그인", notes = "로그인 처리한다.")
    @PostMapping("/signin")
    public SingleResult signin(@RequestBody UserDto userDto) {
        User user = userRepository.findByUid(userDto.getUid()).orElseThrow(CSignInFailedException::new);
        if (!passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            throw new CSignInFailedException();
        }

        return responseService.getSingleResult(jwtTokenProvider.createToken(user.getUid(), user.getRoles()));
    }


}
