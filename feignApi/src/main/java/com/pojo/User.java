package com.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
@TableName("tb_user")
public class User implements Serializable{
    private Long id;
    private String username;
    private String address;
}