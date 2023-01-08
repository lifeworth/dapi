package com.duzy.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duzy.dto.IpQueryDTO;
import com.duzy.model.IpLocationModel;
import com.duzy.vo.IpVo;

import java.util.List;

/**
 * <p>
 * ip地理位置信息 服务类
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-14
 */
public interface IpLocationService extends IService<IpLocationModel> {

    List<IpLocationModel> parseFromApi();

    Page<IpVo> list(IpQueryDTO queryDTO);
}
