package com.devils.hr.pojo.records;

import com.devils.hr.pojo.roles.Parent;
import com.devils.hr.pojo.roles.Student;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/18.
 * 学生家长关联
 */
@Document(collection = "refstupar")
public class RefStuPar {

    @Id
    private String id;

    @DBRef
    private Student student;

    @DBRef
    private Parent  parent;

    private String  relative;

    private long updateTime;

    private long createTime;

    public RefStuPar() {}

    public RefStuPar(String id, Student student, Parent parent, String relative, long updateTime, long createTime) {
        this.id = id;
        this.student = student;
        this.parent = parent;
        this.relative = relative;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Parent getParent() {
        return parent;
    }

    public void setParent(Parent parent) {
        this.parent = parent;
    }

    public String getRelative() {
        return relative;
    }

    public void setRelative(String relative) {
        this.relative = relative;
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
        return "RefStuPar{" +
                "id='" + id + '\'' +
                ", student=" + student +
                ", parent=" + parent +
                ", relative='" + relative + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
