package com.wuzz.demo.mapstruct;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/11/10 14:09
 * @since 1.0
 **/
public class MapstructTest {

    public static void main(String[] args) {

        Role role = new Role(2L, "administrator", "超级管理员");
        User user = new User(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role);

        UserRoleDto userRoleDto = UserRoleMapper.INSTANCES.toUserRoleDto(user);
        System.out.println(userRoleDto);
    }
}
