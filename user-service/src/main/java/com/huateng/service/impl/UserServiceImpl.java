package com.huateng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huateng.mapper.UserMapper;
import com.huateng.service.UserService;
import com.pojo.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {


}
