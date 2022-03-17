package com.huateng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pojo.Order;

public interface OrderService extends IService<Order>{
   Order queryOrderById(Long id);
   void updateOrderAndUser(Long id);
}
