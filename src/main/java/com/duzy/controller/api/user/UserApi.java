package com.duzy.controller.api.user;

import com.duzy.dto.UserDto;
import com.duzy.dto.validater.UserLoginValidGroup;
import com.duzy.service.UserService;
import com.duzy.vo.ResultVO;
import com.duzy.vo.TokenVO;
import com.duzy.vo.UserVO;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhiyuandu
 * @description
 * @since 2023/1/3 14:32
 */
@RestController
@RequestMapping("/api")
@Tag(name = "用户api")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    public ResultVO<UserVO> currentUser() {
        UserVO resultVo = userService.currentUser();
        return ResultVO.success(resultVo);
    }

    @PostMapping("/login")
    public ResultVO<TokenVO> login(@Validated(UserLoginValidGroup.class) @RequestBody UserDto userDto) {
        TokenVO result = userService.login(userDto);
        return ResultVO.success(result);
    }

    @GetMapping("/outLogin")
    public ResultVO<Object> outLogin() {
        userService.outLogin();
        return ResultVO.success();
    }
}
