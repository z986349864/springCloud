package com.huateng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface OrderMapper extends BaseMapper<Order>{

}
