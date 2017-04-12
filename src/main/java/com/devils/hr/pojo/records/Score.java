package com.devils.hr.pojo.records;

import com.devils.hr.pojo.roles.Student;
import com.devils.hr.pojo.roles.Subject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by AndyL on 2017/4/5.
 */
@Document(collection = "score")
public class Score {

    @Id
    private String  id;

    @DBRef
    private Subject subject;

    @DBRef
    private Student student;

    private int     score;

    private long    updateTime;

    private long    createTime;

}
