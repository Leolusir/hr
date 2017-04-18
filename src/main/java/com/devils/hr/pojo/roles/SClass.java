package com.devils.hr.pojo.roles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/4.
 * 班级
 */
@Document(collection = "sclass")
public class SClass {

    @Id private String id;

    private int    status;

    private int    grade;   //年级

    private int    order;   //班次

    private long   session; //届

    private String name;

    private String desc;

    private long   updateTime;

    private long   createTime;

    public SClass() {}

    public SClass(String id, int status, int grade, int order, long session, String name, String desc, long updateTime, long createTime) {
        this.id = id;
        this.status = status;
        this.grade = grade;
        this.order = order;
        this.session = session;
        this.name = name;
        this.desc = desc;
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

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public long getSession() {
        return session;
    }

    public void setSession(long session) {
        this.session = session;
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
        return "SClass{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", grade=" + grade +
                ", order=" + order +
                ", session=" + session +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
