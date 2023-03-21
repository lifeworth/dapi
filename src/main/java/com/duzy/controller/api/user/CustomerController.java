package com.duzy.controller.api.user;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.dto.BaseQueryDTO;
import com.duzy.dto.CustomerDto;
import com.duzy.model.CustomerModel;
import com.duzy.service.CustomerService;
import com.duzy.vo.CustomerVo;
import com.duzy.vo.ResultVO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2023/3/18 17:17
 * @description
 */


@RestController
public abstract class CustomerController<T extends CustomerModel, V extends CustomerVo, D extends CustomerDto, Q extends BaseQueryDTO> {

    @Autowired
    private CustomerService<T, V, D> customerService;

    @GetMapping("/{id}")
    @Operation(summary = "通过id获取")
    public ResultVO<V> getById(@PathVariable Integer id) {
        V v = customerService.customerGetById(id);
        return ResultVO.success(v);
    }

    @PostMapping
    @Operation(summary = "创建")
    public ResultVO<Object> create(@Valid @RequestBody D d) {
        customerService.customerCreate(d);
        return ResultVO.success();
    }

    @PutMapping
    @Operation(summary = "更新")
    public ResultVO<Object> update(@Valid D d) {
        customerService.customerUpdate(d);
        return ResultVO.success();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "通过id删除")
    public ResultVO<Object> delete(@PathVariable Integer id) {
        customerService.customerDelete(id);
        return ResultVO.success();
    }

    @GetMapping
    @Operation(summary = "获取列表")
    public ResultVO<List<V>> list(Q q) {
        List<V> v = customerService.customerList(q);
        return ResultVO.success(v);
    }

    @PostMapping("/page")
    @Operation(summary = "分页获取")
    public ResultVO<Page<V>> page(Q q) {
        Page<V> vo = customerService.customerPage(q);
        return ResultVO.success(vo);
    }

}
