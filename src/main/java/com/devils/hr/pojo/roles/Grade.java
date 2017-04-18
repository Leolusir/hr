package com.devils.hr.pojo.roles;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/18.
 * 年级
 */
@Document(collection = "grade")
public class Grade {

    @Id
    private String id;

    private int    status;

    private int    sortNum;

    private String name;

    private String desc;

    private long   updateTime;

    private long   createTime;

    public Grade() {}

    public Grade(String id, int status, int sortNum, String name, String desc, long updateTime, long createTime) {
        this.id = id;
        this.status = status;
        this.sortNum = sortNum;
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

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
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
        return "Grade{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", sortNum=" + sortNum +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
