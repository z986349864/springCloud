package com.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@ToString
@Data
@TableName("tb_user")
public class User implements Serializable{
    private Long id;
    @NotEmpty(message = "用户名不能为空",groups = AddUser.class)
    private String username;
    private String address;
}