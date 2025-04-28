package com.duzy.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.duzy.common.exception.BizException;
import com.duzy.model.SysUserModel;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * @author zhiyuandu
 * @date 2021/12/22-18:35
 * @description TODO
 **/
@Component
public class MybatisPlusAutoFillHandler implements MetaObjectHandler {

    @Override
    @Transactional(transactionManager = "primaryTxManager", rollbackFor = {Exception.class, BizException.class, RuntimeException.class})
    public void insertFill(MetaObject metaObject) {
        SysUserModel userInfo = SecurityUserContext.getUserInfo();
        String userName = userInfo.getNick();
        Long userId = userInfo.getId();
        setFieldValByName("createTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);

        setFieldValByName("createBy", userId, metaObject);
        setFieldValByName("updateBy", userId, metaObject);

        setFieldValByName("createByName", userName, metaObject);
        setFieldValByName("updateByName", userName, metaObject);
    }

    @Override
    @Transactional(transactionManager = "primaryTxManager", rollbackFor = {Exception.class, BizException.class, RuntimeException.class})
    public void updateFill(MetaObject metaObject) {
        SysUserModel userInfo = SecurityUserContext.getUserInfo();

        Long userId = userInfo.getId();
        String userName = userInfo.getNick();
        setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        setFieldValByName("updateBy", userId, metaObject);

        setFieldValByName("updateByName", userName, metaObject);
    }


}
