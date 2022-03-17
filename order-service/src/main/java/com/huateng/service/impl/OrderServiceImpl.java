package com.huateng.service.impl;

import com.api.UserClient;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huateng.mapper.OrderMapper;
import com.pojo.Order;
import com.pojo.User;
import com.huateng.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,Order> implements OrderService{

    @Autowired
    private UserClient userClient;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public Order queryOrderById(Long id) {
        Order order = orderMapper.selectById(id);
        User user = userClient.findById(order.getUserId());
        order.setUser(user);
        return order;
    }

    @Override
    @GlobalTransactional
    public void updateOrderAndUser(Long id) {
        Order order = orderMapper.selectById(id);
        order.setName("test2222");
        int i = orderMapper.updateById(order);
        System.out.println("order更新条数=====:"+i);
        User user = userClient.findById(order.getUserId());
        user.setUsername("zzzzz2222");
        System.out.println(1/0);
        userClient.updateUser(user);
        System.out.println("user更新================");


    }
}
