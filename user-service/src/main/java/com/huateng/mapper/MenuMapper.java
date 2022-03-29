package com.huateng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pojo.Menu1;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MenuMapper extends BaseMapper<Menu1> {
}
