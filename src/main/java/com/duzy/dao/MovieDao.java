package com.duzy.dao;

import com.duzy.model.MovieModel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 影视播放源 Mapper 接口
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-12
 */
@Repository
public interface MovieDao extends BaseMapper<MovieModel> {

}
