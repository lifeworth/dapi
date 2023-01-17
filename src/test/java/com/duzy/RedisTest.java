package com.duzy;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.duzy.model.UserModel;
import com.duzy.fetures.redis.repository.UserRepository;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.redisson.api.RRateLimiter;
import org.redisson.api.RateIntervalUnit;
import org.redisson.api.RateType;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.duzy.common.Constant.MAX_BATCH_SIZE;

/**
 * @author zhiyuandu
 * @since 2022/8/24 16:25
 * @description
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    UserRepository userRepository;
    String valueKeyPrefix = "test";
    String hashKeyPrefix = "hash:";
    String listKeyPrefix = "list:";
    String bitmapKeyPrefix = "bit:";
    Map<String, Object> map = new HashMap<>();
    List<String> keys;
    @Autowired
    private RedissonClient redissonClient;
    @Value("${ip-api-com.lock}")
    private String key;

    {
        map.put("name", "张三");
        map.put("age", "10");

        keys = Lists.newArrayList("zhangsan ", "里斯", "zhanga", "深爱的");
    }

    @Test
    public void template() {
        stringRedisTemplate.opsForValue().set(valueKeyPrefix, "test中文", 10, TimeUnit.SECONDS);
        stringRedisTemplate.opsForHash().put(hashKeyPrefix, "1", JSONUtil.toJsonStr(map));
        stringRedisTemplate.opsForList().leftPushAll(listKeyPrefix, keys);
        stringRedisTemplate.opsForValue().setBit(bitmapKeyPrefix, 123, true);

        String test = stringRedisTemplate.opsForValue().get(valueKeyPrefix);
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries(hashKeyPrefix + 1);
        List<String> range = stringRedisTemplate.opsForList().range(hashKeyPrefix + 1, 0, 100);
        Boolean bit123 = stringRedisTemplate.opsForValue().getBit(bitmapKeyPrefix, 123);
        Boolean bit456 = stringRedisTemplate.opsForValue().getBit(bitmapKeyPrefix, 456);
        System.out.println(test);
        System.out.println(entries);
        System.out.println(range);
        System.out.println(bit123);
        System.out.println(bit456);

    }

    @Test
    public void repository() {
        UserModel model = new UserModel();
        int id = RandomUtil.randomInt();
        model.setId(id);
        model.setUsername(RandomUtil.randomString("usernameusername", 8));
        model.setNick(RandomUtil.randomString("nicknicknick", 8));
        model.setPassword(RandomUtil.randomString("passwordpasswordpassword", 8));
        model.setPhone(RandomUtil.randomString("1234567890123456789012345678901234567", 8));
        model.setCreatedBy("test");
        model.setCreatedTime(LocalDateTime.now());
        model.setUpdatedBy("test");
        model.setUpdatedTime(LocalDateTime.now());
        userRepository.save(model);
        Optional<UserModel> modelFromRedisRepository = userRepository.findById(id);
        System.out.println(modelFromRedisRepository.get());
    }

    @Test
    public void lock() {
        RRateLimiter rateLimiter = redissonClient.getRateLimiter("lock");
        // 设置速率，4秒中产生1个令牌
        rateLimiter.trySetRate(RateType.OVERALL, 1, 4, RateIntervalUnit.SECONDS);

        List<String> ips = new ArrayList<String>();
        for (int i = 0; i < 1000; i++) {
            ips.add(RandomUtil.randomString("abcsahdjbasd", 2));
        }
        List<List<String>> split = CollUtil.split(ips, MAX_BATCH_SIZE);
        IntStream.range(0, split.size()).forEach(i -> {
            rateLimiter.acquire();
            System.out.println("获取到锁" + i);
        });

    }

    @Test
    public void testLock() {

    }
}
