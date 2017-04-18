package com.devils.hr.request;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by AndyL on 2017/4/18.
 * 用于接受创捷学生的请求参数
 * @see com.devils.hr.controllers.apis.StudentApiCtrl#createStudent(StudentReqMod)
 */
@Validated
public class StudentReqMod {

    @NotEmpty
    private String           ManagerId;

    @NotEmpty
    private String           name;

    private String           desc;

    @Size(min = 1)
    private List<ParentReqMod> parentsInfo;

    public StudentReqMod() {}

    public StudentReqMod(String managerId, String name, String desc, List<ParentReqMod> parentsInfo) {
        ManagerId = managerId;
        this.name = name;
        this.desc = desc;
        this.parentsInfo = parentsInfo;
    }

    public String getManagerId() {
        return ManagerId;
    }

    public void setManagerId(String managerId) {
        ManagerId = managerId;
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

    public List<ParentReqMod> getParentsInfo() {
        return parentsInfo;
    }

    public void setParentsInfo(List<ParentReqMod> parentsInfo) {
        this.parentsInfo = parentsInfo;
    }

    @Override
    public String toString() {
        return "StudentReqMod{" +
                "ManagerId='" + ManagerId + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", parentsInfo=" + parentsInfo +
                '}';
    }
}
