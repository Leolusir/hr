package com.devils.hr.pojo.records;

import com.devils.hr.pojo.roles.SClass;
import com.devils.hr.pojo.roles.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/5.
 */
@Document(collection = "exam")
public class Exam {

    /**
     * 考试类型
     * */
    public static final int TYPE_GRADE = 1; //年级考试

    @Id
    private String  id;

    private int     type;       //统考？班级自测？

    private String  name;

    private String  desc;

    private int     year;       //考试时间 年

    private int     month;      //考试时间 月

    private int     day;        //考试时间 日

    @DBRef
    private Subject subject;    //考试科目

    @DBRef
    private SClass  sClass;     //班级(可选)

    private long    updateTime;

    private long    createTime;

    public Exam() {}

    public Exam(String id, int type, String name, String desc, int year, int month, int day, Subject subject, SClass sClass, long updateTime, long createTime) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.desc = desc;
        this.year = year;
        this.month = month;
        this.day = day;
        this.subject = subject;
        this.sClass = sClass;
        this.updateTime = updateTime;
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public SClass getsClass() {
        return sClass;
    }

    public void setsClass(SClass sClass) {
        this.sClass = sClass;
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
        return "Exam{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", year=" + year +
                ", month=" + month +
                ", day=" + day +
                ", subject=" + subject +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
