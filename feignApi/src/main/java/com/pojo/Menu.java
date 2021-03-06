package com.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@ToString
@Data
@TableName("menu")
public class Menu implements Comparable<Menu>,Serializable {
    private Integer id;
    private String name;
    private Integer parentId;
    private String url;
    private String icon;
    @TableField("`order`")
    private Integer order;
    //子菜单列表 (为空的时候不返回)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @TableField(exist = false)
    private List<Menu> children;

    @Override
    public int compareTo(Menu o) {
        if(this.order != o.order){
            return this.order - o.order;
        }
        return 0;
    }
}
