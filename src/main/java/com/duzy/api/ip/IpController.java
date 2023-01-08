package com.duzy.api.ip;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.IpQueryDTO;
import com.duzy.service.IpLocationService;
import com.duzy.vo.IpVo;
import com.duzy.vo.ResultVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * @author zhiyuandu
 * @since 2023/1/7 16:39
 * @description
 */
@RestController
@RequestMapping("/api/ip")
@Api(tags = "log查询")
public class IpController {
    @Autowired
    IpLocationService ipService;

    @PostMapping
    @ApiOperation(value = "分页查询ssh日志", notes = "分页查询ssh日志", httpMethod = "GET")
    public ResultVO<Page<IpVo>> login(@RequestBody IpQueryDTO queryDTO) {
        Page<IpVo> result = ipService.list(queryDTO);
        return ResultVO.SUCCESS(result);
    }
}
