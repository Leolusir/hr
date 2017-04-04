package com.devils.hr.service;

import com.devils.hr.pojo.roles.Student;
import com.devils.hr.service.base.BaseService;

/**
 * Created by AndyL on 2017/4/2.
 */
public interface StudentService extends BaseService<Student> {

    /**
     * 根据身份证号查找学生
     * @param IDNumber  身份证号
     * */
    Student findByIDNumber(String IDNumber);

    /**
     * 生成学号
     * */
    long generateNumber();

}
