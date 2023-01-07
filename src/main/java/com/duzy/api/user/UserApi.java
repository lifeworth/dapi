package com.duzy.api.user;

import cn.hutool.core.date.DateField;
import cn.hutool.core.util.RandomUtil;
import com.duzy.vo.*;
import com.google.common.collect.Lists;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@Api(tags = "用户api")
public class UserApi {

    @GetMapping("/currentUser")
    public ResultVO<UserVO> currentUser() {
        UserVO userVO = new UserVO();
        userVO.setId(1);
        userVO.setUsername("Serati Ma");
        userVO.setAvatar("https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png");
        userVO.setEmail("123@admin.com");
        userVO.setSignature("海纳百川，有容乃大");
        userVO.setTitle("交互专家");
        userVO.setGroup("蚂蚁金服－某某某事业群－某某平台部－某某技术部－UED");
        ArrayList<Tag> tags = Lists.newArrayList();
        Tag tag1 = new Tag();
        tag1.setKey("1");
        tag1.setLabel("很有想法的");
        Tag tag2 = new Tag();
        tag2.setKey("2");
        tag2.setLabel("专注设计");
        Tag tag3 = new Tag();
        tag3.setKey("3");
        tag3.setLabel("大长腿");
        Tag tag4 = new Tag();
        tag4.setKey("4");
        tag4.setLabel("川妹子");

        tags.add(tag1);
        tags.add(tag2);
        tags.add(tag3);
        tags.add(tag4);
        userVO.setTags(tags);
        userVO.setNotifyCount(100);
        userVO.setUnreadCount(20);
        userVO.setCountry("China");
        userVO.setAccess("admin");
        Geographic geographic = new Geographic();
        Province province = new Province();
        province.setLabel("浙江省");
        province.setKey("330000");

        geographic.setProvince(province);
        City city = new City();
        city.setLabel("杭州市");
        city.setKey("330100");

        geographic.setCity(city);

        userVO.setGeographic(geographic);
        userVO.setAddress("西湖区工专路 77 号");
        userVO.setPhone("13333333333");

        return ResultVO.SUCCESS(userVO);
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
    public ResultVO<TokenVO> login() {
        TokenVO tokenVO = new TokenVO();
        tokenVO.setAccessToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrIjoi5p2c6Ie06L-cIiwiYXVkIjpbIm9yZGVycy1zZXJ2aWNlIiwibWVtYmVyLXNlcnZpY2UiLCJwcm9kdWN0LXNlcnZpY2UiXSwidXNlcl9pZCI6MzcsInVzZXJfbmFtZSI6ImR1emhpeXVhbiIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE2NzI3MzU3NDksImF1dGhvcml0aWVzIjpbImFkbWluIiwiYmlnZGF0YSIsImNvbW1vbiIsIm9wZXJhdG9yIl0sImp0aSI6IjQ1MjRkYTQ4LTg5MTAtNGVmMi04YzUyLTA1YjAzNTI0ZGRlZSIsImNsaWVudF9pZCI6ImFwcC1jbGllbnQifQ.wT6a207hf5uuWMIhOHo0l2ilPkFR6HlTRsRbFg41dRo");
        tokenVO.setTokenType("bearer");
        tokenVO.setExpiresIn(86399);
        tokenVO.setRefreshToken("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJuaWNrIjoi5p2c6Ie06L-cIiwiYXVkIjpbIm9yZGVycy1zZXJ2aWNlIiwibWVtYmVyLXNlcnZpY2UiLCJwcm9kdWN0LXNlcnZpY2UiXSwidXNlcl9pZCI6MzcsInVzZXJfbmFtZSI6ImR1emhpeXVhbiIsInNjb3BlIjpbImFsbCJdLCJhdGkiOiI0NTI0ZGE0OC04OTEwLTRlZjItOGM1Mi0wNWIwMzUyNGRkZWUiLCJleHAiOjE2NzI3MjEzNDksImF1dGhvcml0aWVzIjpbImFkbWluIiwiYmlnZGF0YSIsImNvbW1vbiIsIm9wZXJhdG9yIl0sImp0aSI6ImQ4ZWU3MmQzLTdjMWItNGM0OC1hZjM1LWJjMmE5NGMzMmJlZCIsImNsaWVudF9pZCI6ImFwcC1jbGllbnQifQ.5pdtzll8KKAdW6EUYLrzzUiydi66IDAnSzP3_bQlajI");
        tokenVO.setScope("all");
        tokenVO.setNick("fromServer");
        tokenVO.setUserId(1);
        tokenVO.setJti("4524da48-8910-4ef2-8c52-05b03524ddee");
        return ResultVO.SUCCESS(tokenVO);
    }
}
