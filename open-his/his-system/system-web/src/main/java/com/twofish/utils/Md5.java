package com.twofish.utils;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;

import java.util.Random;

/**
 * @author ww
 * @description
 * @create 2018-08-08 11:16
 **/
public class Md5 {

    public static String getPsw(String hashAlgorithmName, int hashIterations, String pass, String salt){
        Object result = new SimpleHash(hashAlgorithmName, pass, salt, hashIterations);
        return result.toString();
    }

    //生成随机的盐
    public static String getSalt(){
        return new Md5Hash(getStringRandom(8)).toString();
    }

    //生成随机字母
    public static String getStringRandom(int length) {
        String val = "";
        Random random = new Random();
        for(int i = 0; i < length; i++) {
            //输出是大写字母还是小写字母
            int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
            val += (char)(random.nextInt(26) + temp);
        }
        return val;
    }
}
