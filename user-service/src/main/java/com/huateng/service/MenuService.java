package com.huateng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pojo.Menu;
import com.pojo.User;

import java.util.List;

public interface MenuService extends IService<Menu> {
    List<Menu> findAll(Menu menu);
}
