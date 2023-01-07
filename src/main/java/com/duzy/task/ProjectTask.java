package com.duzy.task;

import cn.hutool.core.util.RandomUtil;
import com.duzy.model.UserModel;
import com.duzy.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhiyuandu
 * @since 2022/9/8 11:05
 * @description
 */
@Component
@Slf4j
public class ProjectTask {

    @Autowired
    UserRepository userRepository;

    String baseString = "abcsdjsdfhlsaiughjkbxiuakhjsbgogsejlkdmvaolfhkj思考九点半v呢爱u高数课就放不下诶过生日就不得分口角是非的女吗";

    /**
     *
     * 每隔5秒执行一次
     */
    @Scheduled(fixedRate = 5000)
    public void redisAddTask() {
        UserModel model = new UserModel();

        model.setUsername(RandomUtil.randomString(baseString, 8));
        model.setNick(RandomUtil.randomString(baseString, 6));
        model.setPassword(RandomUtil.randomString(baseString, 6));
        model.setPhone(String.valueOf(RandomUtil.randomDouble(10000000000d, 99999999999d)));
        model.setCreatedBy("system");
        model.setCreatedTime(LocalDateTime.now());
        model.setUpdatedBy("system");
        model.setUpdatedTime(LocalDateTime.now());
        userRepository.save(model);
    }

    @Scheduled(fixedRate = 6000)
    public void redisRemoveTask() {
        List<Integer> ids = new ArrayList<>();
        Iterable<UserModel> all = userRepository.findAll();
        all.forEach(user -> {
            ids.add(user.getId());
            log.info("{}", user);
        });
        ids.stream().limit(ids.size() - 1).forEach(id -> {
            userRepository.deleteById(id);
        });

    }


}
