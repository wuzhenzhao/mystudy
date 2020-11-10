package com.wuzz.demo.mapstruct;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/11/10 13:54
 * @since 1.0
 **/
public class Role {
    private Long id;
    private String roleName;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Role(Long id, String roleName, String description) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
    }

    public Role() {
    }
}


