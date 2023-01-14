package com.duzy.repository;

import com.duzy.model.UserModel;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhiyuandu
 * @since 2022/8/24 19:22
 * @description
 */
@Repository
public interface UserRepository extends ListCrudRepository<UserModel, Integer> {

}
