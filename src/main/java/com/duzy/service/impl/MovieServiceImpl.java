package com.duzy.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.converter.MovieConverter;
import com.duzy.dao.MovieDao;
import com.duzy.model.MovieModel;
import com.duzy.service.MovieService;
import com.duzy.vo.MovieVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 影视播放源 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-12
 */
@Service
public class MovieServiceImpl extends ServiceImpl<MovieDao, MovieModel> implements MovieService {

    @Autowired
    private MovieConverter movieConverter;

    @Override
    public List<MovieVo> listAll() {
        List<MovieModel> list = list();
        List<MovieVo> result = movieConverter.model2Volist(list);
        return result;
    }
}
