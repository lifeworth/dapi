package com.duzy.service.impl;

import cn.hutool.http.HttpUtil;
import com.duzy.common.BizException;
import com.duzy.common.ProjectProps;
import com.duzy.service.PublicApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * @author zhiyuandu
 * @since 2022/11/21 15:23
 * @description
 */
@Service
@Slf4j
public class PublicApiServiceImpl implements PublicApiService {
    @Autowired
    private ProjectProps projectProps;

    @Override
    public String publicApi(String apiName) {
        Optional<Map<String, String>> optional = projectProps.getApis().stream().filter(map -> Objects.nonNull(map.get(apiName))).findAny();


        if (optional.isPresent()) {
            return HttpUtil.get(optional.get().get(apiName));
        } else {
            throw new BizException("未找到url");
        }


    }
}
