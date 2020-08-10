package com.wuzz.demo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 系统用户
 * </p>
 */
@Data
public class SysUser implements Serializable {

    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String password;

    private String username;

    private String mobile;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}
