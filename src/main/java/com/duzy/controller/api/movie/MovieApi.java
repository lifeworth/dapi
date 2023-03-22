package com.duzy.controller.api.movie;

import com.duzy.service.MovieService;
import com.duzy.vo.MovieVo;
import com.duzy.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2023/1/12 21:53
 * @description
 */
@RestController
@RequestMapping("/api/movie")
@Tag(name = "电影")
public class MovieApi {
    @Autowired
    private MovieService movieService;

    @GetMapping
    @Operation(summary = "log查询", description = "查询播放源的列表")
    public ResultVO<List<MovieVo>> list() {
        List<MovieVo> result = movieService.listAll();
        return ResultVO.success(result);
    }
}
