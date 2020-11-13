package com.twofish.utils;

import org.apache.shiro.crypto.hash.DefaultHashService;
import org.apache.shiro.crypto.hash.HashRequest;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import java.util.Random;

/**
 * @author ww
 * @description
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

    //生成密码工具
    public static String getPswd(String hashAlgorithmName, int hashIterations, String pass, String salt) {
        DefaultHashService hashService = new DefaultHashService();
        HashRequest request = new HashRequest.Builder().setAlgorithmName(hashAlgorithmName)
                .setSource(ByteSource.Util.bytes(pass))
                .setSalt(ByteSource.Util.bytes(salt)).setIterations(hashIterations).build();
        return hashService.computeHash(request).toHex();
    }
    
}
