package com.duzy.converter;

import com.duzy.model.MovieModel;
import com.duzy.vo.MovieVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2023/1/12 22:18
 * @description
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface MovieConverter {

    List<MovieVo> model2Volist(List<MovieModel> list);

    MovieVo model2Vo(MovieModel model);
}
