package com.duzy.controller.api.user;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.UserDto;
import com.duzy.dto.UserQueryDto;
import com.duzy.service.UserService;
import com.duzy.vo.ResultVO;
import com.duzy.vo.UserVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/manage/user")
@Tag(name = "用户")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    @Operation(summary = "创建用户")
    public ResultVO<Object> insert(@Valid UserDto dto) {
        userService.save(dto);
        return ResultVO.success();
    }

    @PutMapping
    @Operation(summary = "更新用户")
    public ResultVO<Object> update(@Valid UserDto dto) {
        userService.update(dto);
        return ResultVO.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "删除用户")
    public ResultVO<Object> delete(@PathVariable Integer id) {
        userService.removeById(id);
        return ResultVO.success();
    }

    @GetMapping("/{id}")
    @Operation(summary = "通过id获取用户")
    public ResultVO<UserVO> getById(@PathVariable Integer id) {
        UserVO vo = userService.getById(id);
        return ResultVO.success(vo);
    }

    @GetMapping
    @Operation(summary = "获取列表")
    public ResultVO<List<UserVO>> list(UserQueryDto dto) {
        List<UserVO> list = userService.list(dto);
        return ResultVO.success(list);
    }

    @PostMapping("/page")
    @Operation(summary = "分页获取")
    public ResultVO<Page<UserVO>> page(UserQueryDto dto) {
        Page<UserVO> page = userService.page(dto);
        return ResultVO.success(page);
    }
}
