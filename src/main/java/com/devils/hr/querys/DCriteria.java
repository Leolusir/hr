package com.devils.hr.querys;

import org.springframework.data.mongodb.core.query.Criteria;

/**
 * Created by AndyL on 2017/4/11.
 */
public class DCriteria extends Criteria implements Cloneable {

    private static DCriteria criteria = new DCriteria();

    private DCriteria() {}

    public static Criteria getOneCriteria(){
        try {
            return (Criteria) criteria.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return new Criteria();
    }
}
