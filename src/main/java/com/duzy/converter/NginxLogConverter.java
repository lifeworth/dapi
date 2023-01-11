package com.duzy.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.model.NginxLogModel;
import com.duzy.vo.NginxLogVO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

/**
 * @author zhiyuandu
 * @since 2023/1/11 14:13
 * @description
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface NginxLogConverter {
    Page<NginxLogVO> model2PageVo(Page<NginxLogModel> page);
}
