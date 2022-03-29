package com.huateng.web;

import com.alibaba.fastjson.JSONObject;
import com.huateng.service.MenuService;
import com.huateng.service.UserService;
import com.pojo.AddUser;
import com.pojo.Menu1;
import com.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
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
       /* String s = redisTemplate.opsForValue().get("user:id:" + id);
        if(s!=null && !"".equals(s)){
            User user = JSONObject.parseObject(s, User.class);
            return user;
        }*/
        return userService.getById(id);
    }
    @PutMapping("/update")
    void delete(@RequestBody User user){
        boolean b = userService.updateById(user);
        System.out.println("用户更新状态========:"+b);
    }
   /* 添加事务，指定步回滚的异常Exception,统一接收的异常配置类可以接收到该信息
    校验指定组,只有添加的时候才会校验该规则*/
    @Transactional(rollbackFor = Exception.class )
    @PostMapping("/save")
    public boolean insert(@Validated({AddUser.class}) @RequestBody User user){
        boolean save = userService.save(user);
        System.out.println(1/0);
        return true;
    }

    /**
     * 测试三级菜单递归。使用Comparable的排序方法
     */

    @GetMapping("/menu/findTree")
    public List<Menu1> create(@RequestBody(required = false) Menu1 menu) {
        List<Menu1> list = menuService.findAll(menu);
        return list;
    }

}
