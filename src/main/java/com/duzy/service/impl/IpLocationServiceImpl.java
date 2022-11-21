package com.duzy.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.converter.IpLocationConverter;
import com.duzy.dao.IpLocationDao;
import com.duzy.dto.IpLocationDTO;
import com.duzy.kafka.KafkaProducer;
import com.duzy.kafka.KafkaSampleMessage;
import com.duzy.model.IpLocationModel;
import com.duzy.service.IpLocationService;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * ip地理位置信息 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-11-14
 */
@Service
@Slf4j
public class IpLocationServiceImpl extends ServiceImpl<IpLocationDao, IpLocationModel> implements IpLocationService {
    @Value("${post.ip.url}")
    String postUrl;
    @Autowired
    IpLocationConverter ipLocationConverter;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private KafkaProducer kafkaProducer;

    @Override
    public List<IpLocationModel> parseFromApi() {
        String sql = "";
        List<IpLocationModel> models = list(new LambdaQueryWrapper<IpLocationModel>().isNull(IpLocationModel::getStatus));
        List<String> ips = models.stream().map(IpLocationModel::getIp).collect(Collectors.toList());
        List<IpLocationModel> result = new ArrayList<>();

        CollUtil.split(ips, 100).forEach(list -> {

            JSONArray body = requestApi(list);

            assert body != null;
            List<IpLocationDTO> dtos = body.toList(IpLocationDTO.class);
            for (int i = 0; i < dtos.size(); i++) {
                IpLocationDTO dto = dtos.get(i);
                toKafka(dto, i);
                toDb(dto, models);
            }
        });
        return result;
    }

    @Nullable
    private JSONArray requestApi(List<String> list) {
        ResponseEntity<JSONArray> response = restTemplate.exchange(postUrl, HttpMethod.POST, new HttpEntity<>(list), JSONArray.class);
        HttpStatus statusCode = response.getStatusCode();
        if (statusCode == HttpStatus.TOO_MANY_REQUESTS) {
            log.error("TOO_MANY_REQUESTS");
            throw new RuntimeException("请求API失败! TOO_MANY_REQUESTS");
        }
        List<String> rateLimit = response.getHeaders().get("X-Rl");
        List<String> ttl = response.getHeaders().get("X-Ttl");
        JSONArray body = response.getBody();
        log.info("rateLimit:{},ttl{}.", rateLimit, ttl);
        log.info("ips.size:{}", list.size());
        log.info("body:{}", body);
        log.warn("response:{}", JSONUtil.toJsonStr(response));
        return body;
    }

    private void toDb(IpLocationDTO dto, List<IpLocationModel> models) {
        models.stream().filter(model -> model.getIp().equals(dto.getQuery())).findFirst().ifPresent(model -> {
            dto.setId(model.getId());
            IpLocationModel ipLocationModel = ipLocationConverter.dto2Model(dto);
            log.info("更新id:{}",dto.getId());
            updateById(ipLocationModel);
        });
    }

    private void toKafka(IpLocationDTO ipLocationDto, int i) {
        try {
            KafkaSampleMessage kafkaSampleMessage = new KafkaSampleMessage();
            kafkaSampleMessage.setId(i);
            kafkaSampleMessage.setMessage(JSONUtil.toJsonStr(ipLocationDto));
            kafkaProducer.send(kafkaSampleMessage);
        } catch (Exception e) {
            log.error("发送到kafka失败:{}.", Throwables.getStackTraceAsString(e));
        }
    }
}
