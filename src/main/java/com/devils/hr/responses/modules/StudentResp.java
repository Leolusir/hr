package com.devils.hr.responses.modules;

import com.devils.hr.pojo.modules.Image;
import com.devils.hr.pojo.roles.Student;

/**
 * Created by AndyL on 2017/4/6.
 */
public class StudentResp {

    private String id;

    private String phone;

    private String password;

    private Image  photo;

    private String name;

    private String gender;

    private String birthday;    //yyyy-mm-dd

    private long   number;      //学号

    private String IDNumber;    //身份证号

    private String address;     //联络地址

    public StudentResp(Student student) {
        this.id = student.getId();
        this.phone = student.getPhone();
        this.password = student.getPassword();
        this.photo = student.getPhoto();
        this.name = student.getName();
        this.gender = student.getGender();
        this.birthday = student.getBirthday();
        this.number = student.getNumber();
        this.IDNumber = student.getIDNumber();
        this.address = student.getAddress();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "StudentResp{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", photo=" + photo +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday='" + birthday + '\'' +
                ", number=" + number +
                ", IDNumber='" + IDNumber + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
