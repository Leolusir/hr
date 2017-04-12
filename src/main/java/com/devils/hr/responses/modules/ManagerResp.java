package com.devils.hr.responses.modules;

import com.devils.hr.pojo.modules.Image;
import com.devils.hr.pojo.roles.Manager;

import java.io.Serializable;

/**
 * Created by AndyL on 2017/4/6.
 */
public class ManagerResp implements Serializable {

    private String id;

    private String username;

    private String phone;

    private String email;

    private String name;

    private String desc;

    private Image photo;

    private String role;

    private String lastLoginIP;

    private String lastLoginCity;

    private long   lastLoginTime;

    public ManagerResp(Manager manager) {
        this.id = manager.getId();
        this.username = manager.getUsername();
        this.phone = manager.getPhone();
        this.email = manager.getEmail();
        this.name = manager.getName();
        this.desc = manager.getDesc();
        this.photo = manager.getPhoto();
        this.role = manager.getRole();
        this.lastLoginIP = manager.getLastLoginIP();
        this.lastLoginCity = manager.getLastLoginCity();
        this.lastLoginTime = manager.getLastLoginTime();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    @Override
    public String toString() {
        return "ManagerResp{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", photo=" + photo +
                ", role='" + role + '\'' +
                ", lastLoginIP='" + lastLoginIP + '\'' +
                ", lastLoginCity='" + lastLoginCity + '\'' +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
