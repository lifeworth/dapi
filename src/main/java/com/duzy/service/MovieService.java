package com.duzy.service;

import com.duzy.model.MovieModel;
import com.baomidou.mybatisplus.extension.service.IService;
import com.duzy.vo.MovieVo;

import java.util.List;

/**
 * <p>
 * 影视播放源 服务类
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-12
 */
public interface MovieService extends IService<MovieModel> {
    List<MovieVo>  listAll();
}
