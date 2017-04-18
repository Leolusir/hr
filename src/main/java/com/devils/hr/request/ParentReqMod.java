package com.devils.hr.request;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

/**
 * Created by AndyL on 2017/4/18.
 */
@Validated
public class ParentReqMod {

    @NotEmpty
    private String name;

    @NotEmpty
    private String phone;

    public ParentReqMod() {}

    public ParentReqMod(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "ParentReqMod{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
