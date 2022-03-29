package com.huateng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pojo.Menu1;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MenuService extends IService<Menu1> {
    List<Menu1> findAll(Menu1 menu);
}
