package com.huateng.web;

import com.pojo.Order;
import com.huateng.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId) {
        // 根据id查询订单并返回
        return orderService.queryOrderById(orderId);
    }
    //更新order表和user表，测试分布式事务一致性
    @GetMapping("/update/{orderId}")
    public void updateOrderAndUser(@PathVariable("orderId") Long orderId){
        orderService.updateOrderAndUser(orderId);

    }

}
