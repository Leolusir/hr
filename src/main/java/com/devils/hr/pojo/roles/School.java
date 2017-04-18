package com.devils.hr.pojo.roles;

import com.devils.hr.pojo.modules.Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/2.
 * 机构信息
 */
@Document(collection = "school")
public class School {

    @Id private String id;

    private int    status;

    private String name;

    private String desc;

    private Image  logo;

    private Image  cover;

    private long   updateTime;

    private long   createTime;

    public School() {}

    public School(String id, int status, String name, String desc, Image logo, Image cover, long updateTime, long createTime) {
        this.id = id;
        this.status = status;
        this.name = name;
        this.desc = desc;
        this.logo = logo;
        this.cover = cover;
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

    public Image getLogo() {
        return logo;
    }

    public void setLogo(Image logo) {
        this.logo = logo;
    }

    public Image getCover() {
        return cover;
    }

    public void setCover(Image cover) {
        this.cover = cover;
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
        return "School{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", logo=" + logo +
                ", cover=" + cover +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
