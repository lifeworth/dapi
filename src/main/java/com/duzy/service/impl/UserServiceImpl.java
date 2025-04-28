package com.duzy.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.duzy.common.Constant;
import com.duzy.common.enums.HttpCodeAndMessageEnum;
import com.duzy.common.exception.BizException;
import com.duzy.common.handler.SecurityUserContext;
import com.duzy.common.util.JwtUtil;
import com.duzy.common.util.RedisUtil;
import com.duzy.converter.UserConverter;
import com.duzy.dao.SysUserDao;
import com.duzy.dto.UserDto;
import com.duzy.dto.query.UserQueryDto;
import com.duzy.fetures.redis.repository.UserRepository;
import com.duzy.model.SysUserModel;
import com.duzy.service.UserService;
import com.duzy.vo.TokenVO;
import com.duzy.vo.UserVO;
import com.google.common.base.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.StringRedisTemplate;
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
public class UserServiceImpl extends ServiceImpl<SysUserDao, SysUserModel> implements UserService {
    @Autowired
    UserConverter userConverter;
    @Autowired
    SysUserDao sysUserDao;
    @Autowired
    UserRepository userRepository;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public void save(UserDto dto) {
        SysUserModel sysUserModel = userConverter.dto2Model(dto);
        save(sysUserModel);
        userRepository.save(sysUserModel);
    }

    @Override
    @CachePut(cacheNames = "user", key = "#dto.id")
    public void update(UserDto dto) {
        SysUserModel sysUserModel = userConverter.dto2Model(dto);
        updateById(sysUserModel);
        userRepository.save(sysUserModel);
    }

    @Override
    @Cacheable(cacheNames = "user", key = "#id")
    public UserVO getById(Integer id) {
        SysUserModel model = super.getById(id);
        return userConverter.model2Vo(model);
    }

    @Override
    public List<UserVO> list(UserQueryDto dto) {
        List<SysUserModel> list = list();
        return userConverter.model2VoList(list);
    }

    @Override
    public Page<UserVO> page(UserQueryDto dto) {
        LambdaQueryWrapper<SysUserModel> queryWrapper = new LambdaQueryWrapper<>();
        boolean asc = Objects.isNull(dto.getAsc()) ? DEFAULT_ORDER_ASC : dto.getAsc();
        queryWrapper.and(!Strings.isNullOrEmpty(dto.getNick()), sql -> sql.like(SysUserModel::getNick, dto.getNick()))
                .and(!Strings.isNullOrEmpty(dto.getUsername()),
                        sql -> sql.like(SysUserModel::getUsername, dto.getUsername()))
                .and(Objects.nonNull(dto.getCreatedBy()),
                        sql -> sql.eq(SysUserModel::getCreatedBy, dto.getCreatedBy()))
                .and(Objects.nonNull(dto.getId()), sql -> sql.eq(SysUserModel::getId, dto.getId()))
                .and(Objects.nonNull(dto.getPhone()), sql -> sql.eq(SysUserModel::getPhone, dto.getPhone()))
                .orderBy(true, asc, SysUserModel::getUpdatedTime);

        Integer dtoPageIndex = dto.getCurrent();
        Integer dtoPageSize = dto.getSize();
        int pageIndex = Objects.isNull(dtoPageIndex) ? Constant.DEFAULT_PAGE_INDEX : dtoPageIndex;
        int pageSize = Objects.isNull(dtoPageSize) ? Constant.DEFAULT_PAGE_SIZE : dtoPageSize;

        Page<SysUserModel> pageModelResult = page(Page.of(pageIndex, pageSize), queryWrapper);
        Page<UserVO> pageVoResult = new Page<>();
        BeanUtil.copyProperties(pageModelResult, pageVoResult, "records");
        List<SysUserModel> records = pageModelResult.getRecords();
        List<UserVO> vos = userConverter.model2VoList(records);
        pageVoResult.setRecords(vos);
        return pageVoResult;
    }

    @Override
    public TokenVO login(UserDto userDto) {
        TokenVO tokenVO = new TokenVO();

        SysUserModel sysUserModel = sysUserDao.selectOne(new LambdaQueryWrapper<SysUserModel>()
                .eq(SysUserModel::getUsername, userDto.getUsername())
                .eq(SysUserModel::getCanView, true)
        );
        if (Objects.isNull(sysUserModel)) {
            throw new BizException(HttpCodeAndMessageEnum.USER_NOT_EXIST);
        }
        if (!sysUserModel.getPassword().equals(userDto.getPassword())) {
            throw new BizException(HttpCodeAndMessageEnum.USER_IDENTIFICATION);
        }
        // 创建token
        String token = JwtUtil.createToken(sysUserModel);

        tokenVO.setAccessToken(token);
        tokenVO.setTokenType(JwtUtil.verifyToken(token).getType());
        tokenVO.setNick(sysUserModel.getNick());
        Long id = sysUserModel.getId();
        tokenVO.setUserId(id);

        //存入redis
        redisUtil.saveToken(id, tokenVO);

        return tokenVO;

    }

    @Override
    public UserVO currentUser() {
        SysUserModel userInfo = SecurityUserContext.getUserInfo();
        return userConverter.model2Vo(userInfo);
    }

    @Override
    public void outLogin() {
        SysUserModel userInfo = SecurityUserContext.getUserInfo();
        redisUtil.removeToken(userInfo.getId());
    }
}
