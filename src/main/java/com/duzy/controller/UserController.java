package com.duzy.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.vo.ResultVO;
import com.duzy.dto.UserDTO;
import com.duzy.service.UserService;
import com.duzy.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 用户 前端控制器
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
@RestController
@RequestMapping("/user")
@io.swagger.annotations.Api(tags = "用户")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResultVO insert(@Valid UserDTO dto) {
        userService.save(dto);
        return ResultVO.SUCCESS();
    }

    @PutMapping
    public ResultVO update(@Valid UserDTO dto) {
        userService.update(dto);
        return ResultVO.SUCCESS();
    }

    @DeleteMapping("/{id}")
    public ResultVO delete(@PathVariable Integer id) {
        userService.removeById(id);
        return ResultVO.SUCCESS();
    }

    @GetMapping("/{id}")
    public ResultVO<UserVO> getById(@PathVariable Integer id) {
        UserVO vo = userService.getById(id);
        return ResultVO.SUCCESS(vo);
    }

    @GetMapping
    public ResultVO<List<UserVO>> list(UserDTO dto) {
        List<UserVO> list = userService.list(dto);
        return ResultVO.SUCCESS(list);
    }

    @PostMapping("/page")
    public ResultVO<Page<UserVO>> page(UserDTO dto) {
        Page<UserVO> page = userService.page(dto);
        return ResultVO.SUCCESS(page);
    }
}
