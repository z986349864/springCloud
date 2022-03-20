package com.huateng.config;

import com.alibaba.fastjson.JSONObject;
import com.huateng.service.UserService;
import com.pojo.User;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * redis热加载配置
 * 项目启动后开始加载所需要的热点数据存放到redis中
 * 缓存预热需要在项目启动时完成，并且必须是拿到RedisTemplate之后。
 这里我们利用InitializingBean接口来实现，因为InitializingBean可以在对象被Spring创建并且成员变量全部注入后执行。
 */
@Component
public class RedisHandler implements InitializingBean {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Override
    public void afterPropertiesSet() throws Exception {
//        List<User> list = userService.list();
//        for (User user : list) {
//            String json = JSONObject.toJSONString(user);
//            redisTemplate.opsForValue().set("user:id:" + user.getId(), json);
//        }

    }
}
