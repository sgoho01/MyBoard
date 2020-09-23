package com.ghsong.myboard.modules.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Api(tags = {"1. Test"})
@Controller
public class TestController {


    @ApiOperation(value = "테스트 1", notes = "테스트 1 설명")
    @GetMapping("/test")
    @ResponseBody
    public String test() {
        return "test";
    }

    @ApiOperation(value = "테스트 2", notes = "테스트 2 설명")
    @GetMapping("/test2")
    @ResponseBody
    public String test2(@ApiParam(value = "이름", required = true) @RequestParam String name){
        return "test2 - " + name;
    }

}
