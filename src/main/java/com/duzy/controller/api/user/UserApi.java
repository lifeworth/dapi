package com.duzy.controller.api.user;

import com.duzy.vo.ResultVO;
import com.duzy.vo.TokenVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiyuandu
 * @since 2023/1/3 14:32
 * @description
 */
@RestController
@RequestMapping("/api")
public class UserApi {

    @GetMapping("/currentUser")
    public ResultVO currentUser() {
        return null;
    }

    @GetMapping("/login")
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
