package com.devils.hr.pojo.roles;

import com.devils.hr.pojo.modules.Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/4.
 * 后台管理员
 */
@Document(collection = "manager")
public class Manager {

    /**
     * 管理员权限
     * */
    public static final String ROLE_ROOT = "root";
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_READ = "read";
    public static final String ROLE_READ_WRITE = "readWrite";

    /**
     * 状态
     * */
    public static final int STATUS_INACTIVATED = 0;    //未激活 即未绑定手机号
    public static final int STATUS_ACTIVATED = 1;      //已激活


    @Id
    private String id;

    private int    status;

    private String username;

    private String password;

    private String phone;

    private String email;

    private String name;

    private String desc;

    private Image  photo;

    private String role;

    private String lastLoginIP;

    private String lastLoginCity;

    private long   lastLoginTime;

    private long   updateTime;

    private long   createTime;

    public Manager() {}

    public Manager(String id, int status, String username, String password, String phone, String email, String name, String desc, Image photo, String role, String lastLoginIP, String lastLoginCity, long lastLoginTime, long updateTime, long createTime) {
        this.id = id;
        this.status = status;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.name = name;
        this.desc = desc;
        this.photo = photo;
        this.role = role;
        this.lastLoginIP = lastLoginIP;
        this.lastLoginCity = lastLoginCity;
        this.lastLoginTime = lastLoginTime;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getLastLoginIP() {
        return lastLoginIP;
    }

    public void setLastLoginIP(String lastLoginIP) {
        this.lastLoginIP = lastLoginIP;
    }

    public String getLastLoginCity() {
        return lastLoginCity;
    }

    public void setLastLoginCity(String lastLoginCity) {
        this.lastLoginCity = lastLoginCity;
    }

    public long getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(long lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", photo=" + photo +
                ", role='" + role + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
