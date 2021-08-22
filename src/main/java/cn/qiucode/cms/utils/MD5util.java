package cn.qiucode.cms.utils;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @program: cms
 * @description:
 * @author: 上官江北
 * @create: 2021-07-25 22:55
 */
public class MD5util {
    /**
     * 对字符串进行Md5加密
     *
     * @param input 原文
     * @return md5后的密文
     */
    public static String md5(String input) {
        byte[] code = null;
        try {
            code = MessageDigest.getInstance("md5").digest(input.getBytes());
        } catch (NoSuchAlgorithmException e) {
            code = input.getBytes();
        }
        BigInteger bi = new BigInteger(code);
        return bi.abs().toString(32).toUpperCase();
    }
    /**
     * 对字符串进行Md5加密
     *
     * @param input 原文
     * @param salt 随机数
     * @return string
     */
    public static String generatePasswordMD5(String input, String salt) {
        if(StringUtils.isEmpty(salt)) {
            salt = "";
        }
        return md5(md5(salt + md5(input+salt)));
    }

    private static String algorithmName = "md5";
    private static int hashIterations = 2;
    private static String salt = "123456";

    public static String encrypt(String str,String salt, String algorithmName, int hashIterations) {
        return new SimpleHash(algorithmName,str, ByteSource.Util.bytes(salt),hashIterations).toString();
    }

    public static String encrypt(String str,String salt){
        return encrypt(str,salt,algorithmName,hashIterations);
    }

    public static String getRandomString( int length){
        //定义一个字符串（A-Z，a-z，0-9）即62位；
        String str= "zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890" ;
        //由Random生成随机数
        Random random= new Random();
        StringBuffer sb= new StringBuffer();
        //长度为几就循环几次
        for ( int i= 0 ; i<length; ++i){
            //产生0-61的数字
            int number=random.nextInt( 62 );
            //将产生的数字通过length次承载到sb中
            sb.append(str.charAt(number));
        }
        //将承载的字符转换成字符串
        return sb.toString();
    }

    public static String getRandomString2( int length){
        //产生随机数
        Random random= new Random();
        StringBuffer sb= new StringBuffer();
        //循环length次
        for ( int i= 0 ; i<length; i++){
            //产生0-2个随机数，既与a-z，A-Z，0-9三种可能
            int number=random.nextInt( 3 );
            long result= 0 ;
            switch (number){
                //如果number产生的是数字0；
                case 0 :
                    //产生A-Z的ASCII码
                    result=Math.round(Math.random()* 25 + 65 );
                    //将ASCII码转换成字符
                    sb.append(String.valueOf(( char )result));
                    break ;
                case 1 :
                    //产生a-z的ASCII码
                    result=Math.round(Math.random()* 25 + 97 );
                    sb.append(String.valueOf(( char )result));
                    break ;
                case 2 :
                    //产生0-9的数字
                    sb.append(String.valueOf
                            ( new Random().nextInt( 10 )));
                    break ;
            }
        }
        return sb.toString();
    }



    //控制台输出的字符串替换数据库中password
    public static void main(String [] args){
        //System.out.println(getRandomString( 7 ));
        String pwd=encrypt("123456","8NtuF8u",algorithmName,hashIterations);
        System.out.println(pwd);
    }
}
