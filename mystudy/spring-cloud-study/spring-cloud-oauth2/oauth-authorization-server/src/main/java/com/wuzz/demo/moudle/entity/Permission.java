package com.wuzz.demo.moudle.entity;

/**
 * @description: 类功能描述
 * @author: wuzhenzhao
 * @time 2020/7/10 14:17
 * @since 1.0
 **/
public class Permission {

    private Integer permissionId;

    private String permissionCode;

    private String permissionDesc;

    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionCode() {
        return permissionCode;
    }

    public void setPermissionCode(String permissionCode) {
        this.permissionCode = permissionCode;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }
}
