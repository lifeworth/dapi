package com.duzy.api.movie;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.IpQueryDTO;
import com.duzy.vo.IpVo;
import com.duzy.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhiyuandu
 * @since 2023/1/12 21:53
 * @description
 */
@RestController
@RequestMapping("/api/movie")
@Api(tags = "电影")
public class MovieController {
//    @Autowired
//    private MovieService movieService;
//    @GetMapping
//    @ApiOperation(value = "查询播放源的列表", notes = "查询列表", httpMethod = "GET")
//    public ResultVO<Page<MovieVo>> list() {
//        Page<MovieVo> result = movieService.list();
//        return ResultVO.SUCCESS(result);
//    }
}
