package com.wuzz.demo.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wuzz.demo.dao.UserMapper;
import com.wuzz.demo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/5/7 10:04
 * @since 1.0
 **/
@Service
public class UserService /*implements IService<User>*/ {

    @Autowired
    private UserMapper userMapper;

//    public IPage<User> selectUserPage(Page<User> page, Integer state) {
//        // 不进行 count sql 优化，解决 MP 无法自动优化 SQL 问题，这时候你需要自己查询 count 部分
//        // page.setOptimizeCountSql(false);
//        // 当 total 为小于 0 或者设置 setSearchCount(false) 分页插件不会进行 count 查询
//        // 要点!! 分页返回的对象与传入的对象是同一个
//        return userMapper.selectPageVo(page, state);
//    }

    public boolean test() {

        IPage<User> page = userMapper.selectPageVo(new Page<>(0, 2));
        System.out.println(JSON.toJSONString(page));
        //查询
        User user = userMapper.selectById(1);
        System.out.println(user.toString());

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().like(User::getName, "J");
        List<User> users = userMapper.selectList(wrapper);
        System.out.println(users.toString());

        Page page2 = new Page<>(1, 2);
        IPage<User> userPage = userMapper.selectPage(page2,wrapper);
        System.out.println(JSON.toJSONString(userPage));

        List<Map<String, Object>> maps = userMapper.selectMaps(wrapper);
        System.out.println(JSON.toJSONString(maps));
//
//        User wuzz = new User("wuzz", 18, "774283325@qq.com");
//        int i = userMapper.insert(wuzz);
//        System.out.println(wuzz.getId());
        return false;
    }


}
