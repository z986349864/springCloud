package com.huateng.web;

import com.alibaba.fastjson.JSONObject;
import com.huateng.service.MenuService;
import com.huateng.service.UserService;
import com.pojo.Menu;
import com.pojo.User;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private MenuService menuService;
    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id) {
        String s = redisTemplate.opsForValue().get("user:id:" + id);
        if(s!=null && !"".equals(s)){
            User user = JSONObject.parseObject(s, User.class);
            return user;
        }
        return userService.getById(id);
    }
    @PutMapping("/update")
    void delete(@RequestBody User user){
        boolean b = userService.updateById(user);
        System.out.println("用户更新状态========:"+b);
    }

    /**
     * 测试三级菜单递归。使用Comparable的排序方法
     */

    @GetMapping("/menu/findTree")
    public List<Menu> create(@RequestBody(required = false) Menu menu) {
        List<Menu> list = menuService.findAll(menu);
        return list;
    }

}
