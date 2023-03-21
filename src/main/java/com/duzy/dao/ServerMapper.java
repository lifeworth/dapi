package com.duzy.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.duzy.model.Server;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuelong
 * @description 针对表【m_server(服务器列表)】的数据库操作Mapper
 * @createDate 2022-11-22 20:27:58
 * @Entity meblog.online.server.entity.Server
 */
@Repository
public interface ServerMapper extends BaseMapper<Server> {

    /**
     * 批量逻辑删除
     * @param ids
     * @return
     */
    int batchLogicDelete(@Param("ids") List<Integer> ids);

}
