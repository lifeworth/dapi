package com.duzy.converter;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.duzy.model.SshLogModel;
import com.duzy.vo.SshLogVo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhiyuandu
 * @since 2022/11/21 17:38
 * @description
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
@Component
public interface SshLogConverter {
    SshLogVo model2Vo(SshLogModel model);

    List<SshLogVo> model2Vo(List<SshLogModel> model);

    default Page<SshLogVo> model2PageVo(Page<SshLogModel> page) {
        Page<SshLogVo> result = new Page<>();
        BeanUtil.copyProperties(page, result, "record");
        List<SshLogModel> records = page.getRecords();
        List<SshLogVo> voList = model2Vo(records);
        result.setRecords(voList);
        return result;
    }
}
