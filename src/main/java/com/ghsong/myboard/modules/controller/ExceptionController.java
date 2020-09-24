package com.ghsong.myboard.modules.controller;

import com.ghsong.myboard.advice.exception.CAuthenticationEnrtyPointException;
import com.ghsong.myboard.config.response.CommonResult;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@ApiIgnore
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/exception")
public class ExceptionController {

    @GetMapping("/entrypoint")
    public CommonResult entrypointException() {
        throw new CAuthenticationEnrtyPointException();
    }

}
