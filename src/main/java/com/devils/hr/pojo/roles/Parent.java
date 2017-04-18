package com.devils.hr.pojo.roles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/14.
 * 学生家长
 */
@Document(collection = "parent")
public class Parent {

    public static final int STATUS_INACTIVE = 0;    //未激活 即未修改密码

    public static final int STATUS_ACTIVE   = 1;

    @Id
    private String id;

    private int    status;

    private String name;

    private String desc;

    private String phone;

    private String password;

    private long   updateTime;

    private long   createTime;

    public Parent() {}

    public Parent(String id, int status, String name, String desc, String phone, String password, long updateTime, long createTime) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.desc = desc;
        this.phone = phone;
        this.password = password;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return "Parent{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
