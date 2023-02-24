package com.duzy.controller.api.user;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.duzy.dto.UserDto;
import com.duzy.dto.validater.UserLoginValidGroup;
import com.duzy.service.UserService;
import com.duzy.vo.NoticeVO;
import com.duzy.vo.ResultVO;
import com.duzy.vo.TokenVO;
import com.duzy.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhiyuandu
 * @since 2023/1/3 14:32
 * @description
 */
@RestController
@RequestMapping("/api")
@io.swagger.v3.oas.annotations.tags.Tag(name = "用户api")
public class UserApi {

    @Autowired
    private UserService userService;

    @GetMapping("/currentUser")
    public ResultVO<UserVO> currentUser() {
        UserVO resultVo = userService.currentUser();
        return ResultVO.SUCCESS(resultVo);
    }

    @GetMapping("/notices")
    public ResultVO<List<NoticeVO>> notices() {
        List<NoticeVO> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            NoticeVO noticeVO = new NoticeVO();
            noticeVO.setId(String.valueOf(RandomUtil.randomInt(200)));
            noticeVO.setAvatar("https://gw.alipayobjects.com/zos/rmsportal/ThXAXghbEsBCCSDihZxY.png");
            noticeVO.setTitle("收到了 1 份新周报");
            noticeVO.setDatetime(RandomUtil.randomDate(new Date(), DateField.DAY_OF_WEEK, 1, 3).toMsStr());
            noticeVO.setType("notification");
            list.add(noticeVO);
        }
        return ResultVO.SUCCESS(list);
    }

    @PostMapping("/login")
    public ResultVO<TokenVO> login(@Validated(UserLoginValidGroup.class) @RequestBody UserDto userDto) {
        TokenVO result = userService.login(userDto);
        return ResultVO.SUCCESS(result);
    }

    @GetMapping("/outLogin")
    public ResultVO<Object> outLogin() {
        userService.outLogin();
        return ResultVO.SUCCESS();
    }
}
