package com.devils.hr.responses.modules;

import com.devils.hr.pojo.roles.Grade;

/**
 * Created by AndyL on 2017/4/18.
 */
public class GradeResp {

    private String id;

    private String name;

    public GradeResp(Grade grade) {
        this.id = grade.getId();
        this.name = grade.getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
