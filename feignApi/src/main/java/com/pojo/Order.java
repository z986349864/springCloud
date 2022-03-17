package com.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.beans.Transient;
import java.io.Serializable;

@ToString
@Data
@TableName("tb_order")
public class Order implements Serializable {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    @TableField(exist = false)
    private User user;
    //测试一下1 a
}