package com.devils.hr.service;

import com.devils.hr.pojo.roles.Student;
import com.devils.hr.querys.ListQueryResult;
import com.devils.hr.querys.SingleQueryResult;
import com.devils.hr.request.StudentReqMod;
import com.devils.hr.service.base.BaseService;

/**
 * Created by AndyL on 2017/4/2.
 */
public interface StudentService extends BaseService<Student> {

    /**
     * 通过手机号或学号查找学生
     * @param  number   学号
     * @param  phone    手机号
     * */
    SingleQueryResult<Student> findByNumberOrPhone(long number, String phone);

    /**
     * 根据身份证号查找学生
     * @param IDNumber  身份证号
     * */
    SingleQueryResult<Student> findByIDNumber(String IDNumber);

    /**
     * 按学号顺序分页获取学生信息
     * */
    ListQueryResult<Student> findByPageInNumber(long startNumber, int count, int skip);

    /**
     * 生成学号
     * */
    long generateNumber();

    /**
     * 创建新学生
     * @see com.devils.hr.controllers.apis.StudentApiCtrl#createStudent(StudentReqMod)
     * @see com.devils.hr.request.StudentReqMod
     * */
    SingleQueryResult<Student> saveByReqMod(StudentReqMod studentReqMod);

}
