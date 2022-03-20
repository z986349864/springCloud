package com.huateng.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huateng.mapper.MenuMapper;
import com.huateng.service.MenuService;
import com.pojo.Menu;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper,Menu> implements MenuService {

    @Override
    public List<Menu> findAll(Menu menu) {
        List<Menu> allMenu = baseMapper.selectList(null);
        //根节点存储
        List<Menu> rootMenu = new ArrayList<>();
        //根据传递的参数设置根节点
        if(menu != null && menu.getId()!= null){
            //父节点为传递的id为根节点
            for (Menu nav : allMenu) {
                if(nav.getParentId().equals(menu.getId())){
                    rootMenu.add(nav);
                }
            }
        }else {
            //父节点是0的，为根节点
            for (Menu nav : allMenu) {
                if(nav.getParentId().equals(0)){
                    rootMenu.add(nav);
                }
            }
        }

        // 根据Menu类的order排序
        Collections.sort(rootMenu);

        //为根节点设置子菜单，getChild是递归调用
        for (Menu nv : rootMenu) {
            //获取根节点下的所有子节点，使用getChild方法
            List<Menu> childList = getChild(nv.getId(),allMenu);
            //给根节点设置子节点
            nv.setChildren(childList);
        }
        return rootMenu;
    }
    private List<Menu> getChild(Integer id, List<Menu> allMenu) {
        //子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu nav : allMenu) {
            //遍历所有节点，将所有菜单的父id与传过来的根节点的id比较
            //相等说明：为该根节点的子节点
            if(nav.getParentId().equals(id)){
                childList.add(nav);
            }
        }
        //递归设置子节点
        for (Menu nav : childList) {
            nav.setChildren(getChild(nav.getId(),allMenu));
        }
        //排序
        Collections.sort(childList);
        //如果节点下没有子节点，返回一个空List（递归退出）
        if(childList.size() == 0){
            return new ArrayList<Menu>();
        }
        return childList;
    }

}
