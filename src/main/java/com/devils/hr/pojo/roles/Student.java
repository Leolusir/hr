package com.devils.hr.pojo.roles;

import com.devils.hr.pojo.modules.Image;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/2.
 * 学生
 */
@Document(collection = "student")
public class Student {

    /**
     * 状态
     * */
    public static final int STATUS_INACTIVE = 0;    //未激活 即未绑定手机号
    public static final int STATUS_ACTIVE = 1;      //已激活

    @Id private String id;

    private int    status;

    private String phone;

    private String password;

    private Image  photo;

    private String name;

    private String desc;

    private String gender;

    private String birthday;    //yyyy-mm-dd

    @Indexed
    private long   number;      //学号

    private String IDNumber;    //身份证号

    private String address;     //联络地址

    private String admissionTime;    //入学时间 yyyy-mm-dd

    private long   updateTime;

    private long   createTime;


    public Student() {}

    public Student(String id, int status, String phone, String password, Image photo, String name, String desc, String gender, String birthday, long number, String IDNumber, String address, String admissionTime, long updateTime, long createTime) {
        this.id = id;
        this.status = status;
        this.phone = phone;
        this.password = password;
        this.photo = photo;
        this.name = name;
        this.desc = desc;
        this.gender = gender;
        this.birthday = birthday;
        this.number = number;
        this.IDNumber = IDNumber;
        this.address = address;
        this.admissionTime = admissionTime;
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

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getIDNumber() {
        return IDNumber;
    }

    public void setIDNumber(String IDNumber) {
        this.IDNumber = IDNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAdmissionTime() {
        return admissionTime;
    }

    public void setAdmissionTime(String admissionTime) {
        this.admissionTime = admissionTime;
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
        return "Student{" +
                "id='" + id + '\'' +
                ", status=" + status +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", photo=" + photo +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", number=" + number +
                ", IDNumber='" + IDNumber + '\'' +
                ", address='" + address + '\'' +
                ", admissionTime='" + admissionTime + '\'' +
                ", updateTime=" + updateTime +
                ", createTime=" + createTime +
                '}';
    }
}
