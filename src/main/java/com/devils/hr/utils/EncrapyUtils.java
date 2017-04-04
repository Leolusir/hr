package com.devils.hr.utils;

import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by AndyL on 2017/4/4.
 */
public class EncrapyUtils {

    /** MD5加密
     * @param str   待加密的字符串
     * @return      加密后的字符串
     * @throws      NoSuchAlgorithmException  没有这种产生消息摘要的算法
     * @throws      UnsupportedEncodingException
     */
    public String EncoderByMd5(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5=MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        return base64en.encode(md5.digest(str.getBytes("utf-8")));
    }

}
