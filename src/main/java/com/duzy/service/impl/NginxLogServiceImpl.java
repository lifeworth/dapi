package com.duzy.service.impl;

import com.duzy.model.NginxLogModel;
import com.duzy.dao.NginxLogDao;
import com.duzy.service.NginxLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * nginx日志 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-01-08
 */
@Service
public class NginxLogServiceImpl extends ServiceImpl<NginxLogDao, NginxLogModel> implements NginxLogService {

}
