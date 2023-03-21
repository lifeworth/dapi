package com.duzy.service.impl;

import com.duzy.model.SysResourceModel;
import com.duzy.dao.SysResourceDao;
import com.duzy.service.SysResourceService;
import org.springframework.stereotype.Service;

import com.duzy.dto.SysResourceDto;
import com.duzy.vo.SysResourceVo;
/**
 * <p>
 * 权限/资源 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2023-03-21
 */
@Service
public class SysResourceServiceImpl extends CustomerServiceImpl<SysResourceModel,SysResourceVo,SysResourceDto,SysResourceDao> implements SysResourceService {

}
