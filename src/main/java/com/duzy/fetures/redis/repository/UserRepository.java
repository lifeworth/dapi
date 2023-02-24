package com.duzy.fetures.redis.repository;

import com.duzy.model.SysUserModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhiyuandu
 * @since 2022/8/24 19:22
 * @description
 */
@Repository
public interface UserRepository extends ListCrudRepository<SysUserModel, Integer> {

}
