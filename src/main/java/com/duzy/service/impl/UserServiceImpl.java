package com.duzy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.common.Constant;
import com.duzy.converter.UserConverter;
import com.duzy.dao.UserDao;
import com.duzy.dto.UserDTO;
import com.duzy.model.UserModel;
import com.duzy.repository.UserRepository;
import com.duzy.service.UserService;
import com.duzy.vo.UserVO;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

import static com.duzy.common.Constant.DEFAULT_ORDER_ASC;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author zhiyuandu
 * @since 2022-08-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserDao, UserModel> implements UserService {
    @Autowired
    UserConverter userConverter;
    @Autowired
    UserDao userDao;
    @Autowired
    UserRepository userRepository;

    @Override
    public void save(UserDTO dto) {
        UserModel userModel = userConverter.dto2Model(dto);
        save(userModel);
        userRepository.save(userModel);
    }

    @Override
    @CachePut(cacheNames = "user", key = "#dto.id")
    public void update(UserDTO dto) {
        UserModel userModel = userConverter.dto2Model(dto);
        updateById(userModel);
        userRepository.save(userModel);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public UserVO getById(Integer id) {
        UserModel model = super.getById(id);
        return userConverter.model2Vo(model);
    }

    @Override
    public List<UserVO> list(UserDTO dto) {
        List<UserModel> list = list();
        return userConverter.model2VoList(list);
    }

    @Override
    public Page<UserVO> page(UserDTO dto) {
        LambdaQueryWrapper<UserModel> queryWrapper = new LambdaQueryWrapper<>();
        boolean asc = Objects.isNull(dto.getAsc()) ? DEFAULT_ORDER_ASC : dto.getAsc();
        queryWrapper.and(!Strings.isNullOrEmpty(dto.getNick()), sql -> sql.like(UserModel::getNick, dto.getNick()))
                    .and(!Strings.isNullOrEmpty(dto.getUsername()),
                         sql -> sql.like(UserModel::getUsername, dto.getUsername()))
                    .and(Objects.nonNull(dto.getCreatedBy()),
                         sql -> sql.eq(UserModel::getCreatedBy, dto.getCreatedBy()))
                    .and(Objects.nonNull(dto.getId()), sql -> sql.eq(UserModel::getId, dto.getId()))
                    .and(Objects.nonNull(dto.getPhone()), sql -> sql.eq(UserModel::getPhone, dto.getPhone()))
                    .orderBy(true, asc, UserModel::getUpdatedTime);

        Integer dtoPageIndex = dto.getPageIndex();
        Integer dtoPageSize = dto.getPageSize();
        int pageIndex = Objects.isNull(dtoPageIndex) ? Constant.DEFAULT_PAGE_INDEX : dtoPageIndex;
        int pageSize = Objects.isNull(dtoPageSize) ? Constant.DEFAULT_PAGE_SIZE : dtoPageSize;

        Page<UserModel> pageModelResult = page(Page.of(pageIndex, pageSize), queryWrapper);
        Page<UserVO> pageVoResult = new Page<>();
        BeanUtil.copyProperties(pageModelResult, pageVoResult, "records");
        List<UserModel> records = pageModelResult.getRecords();
        List<UserVO> vos = userConverter.model2VoList(records);
        pageVoResult.setRecords(vos);
        return pageVoResult;
    }
}
