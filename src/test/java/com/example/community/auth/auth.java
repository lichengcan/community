package com.example.community.auth;

import cn.hutool.Hutool;

import java.util.Date;

/**
 * @author: lichengcan
 * @date: 2023-07-20 14:20
 * @description
 **/
public class auth {


    //TODO 1、待确定
    public static void main(String[] args) {
         Date date1 = new Date();
        System.out.println("date1 = " + date1);
    }
    String date = "";

    String SecretId = "402883028953bcfc018962634d160000";

    /**
     * 用户登录后可以获取到key
     */
    String SecretKey = "bt04w30vbnrvdopykclvl2ijq7j3ps2vb";


    byte[] SecretSigning = AlgorithmUtil.HMAC_SHA256(SecretKey.getBytes(), date);


    String Algorithm = "TC3-HMAC-SHA256";
    String RequestTimestamp = "1551113065";
    String Credential = SecretId;

    private String HTTPRequestMethod = "GET";
    private String CanonicalURI = "/ucenter/user";
    //TODO 2、POST请求是空串，GET请求是 ？ 后的内容，没有请求参数情况是 空串吗？
    private String CanonicalQueryString = "";
    //TODO 3、这里的host写什么
    private String CanonicalHeaders = "content-type;X-SAAS-HOST";
    private String SignedHeaders = "content-type;X-SAAS-HOST";
    //TODO 4、GET请求这里是空字符，其他请求使用请求正文
    String RequestPayload = "";
    private String HashedRequestPayload = AlgorithmUtil.bytesToHex(AlgorithmUtil.Hash_SHA256(RequestPayload)).toLowerCase();

    /**
     * 拼接规范请求
     */
    String CanonicalRequest =
            HTTPRequestMethod + '\n' +
                    CanonicalURI + '\n' +
                    CanonicalQueryString + '\n' +
                    CanonicalHeaders + '\n' +
                    SignedHeaders + '\n' +
                    HashedRequestPayload;
    String HashedCanonicalRequest = AlgorithmUtil.bytesToHex(AlgorithmUtil.Hash_SHA256(CanonicalRequest)).toLowerCase();


    /**
     * 拼接签名
     */
    String StringToSign =
            Algorithm + "\n" +
                    RequestTimestamp + "\n" +
                    Credential + "\n" +
                    HashedCanonicalRequest;

    /**
     * 生成签名
     */
    String Signature = AlgorithmUtil.bytesToHex(AlgorithmUtil.HMAC_SHA256(SecretSigning, StringToSign));


    //最终生成的Authorization
    String Authorization =
            Algorithm + " " +
                    "Credential=" + SecretId + ", " +
                    "SignedHeaders=" + SignedHeaders + ", " +
                    "Signature=" + Signature;


}
