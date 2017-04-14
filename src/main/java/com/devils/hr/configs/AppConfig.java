package com.devils.hr.configs;

/**
 * Created by AndyL on 2017/4/6.
 */
public class AppConfig {

    public static final int defaultDataQueryCount = 20;

    /**
     * 学生账号初始密码
     * 000000
     * 对明文进行 MD5 加密
     * */
    public static final String INIT_PASSWORD_MD5 = "670b14728ad9902aecba32e22fa4f6bd";

    public static final boolean OPEN_AUTH = true;
}
