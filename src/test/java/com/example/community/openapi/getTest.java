package com.example.community.openapi;
import com.example.community.util.SignatureUtil;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * @author: lichengcan
 * @date: 2023-07-20 15:50
 * @description
 **/
public class getTest {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException {
        //方法类型
        String httpRequestMethod = "GET";
        //接口路径
        String canonicalUri = "/collect/v1/monitoringSoftware/readMonitorSoftWare";
        //POST接口请求参数
        String payload="";
        //GET接口请求参数
        String canonicalQueryString="";
        final String authorization = getAuthorization(httpRequestMethod, canonicalUri, payload,canonicalQueryString);
        System.out.println("authorization = " + authorization);
    }

    /**
     *
     * @param httpRequestMethod 方法类型
     * @param canonicalUri 接口路径
     * @param payload POST接口请求参数 默认空串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeyException
     */
    public static String getAuthorization(String httpRequestMethod, String canonicalUri,String payload,String canonicalQueryString) throws NoSuchAlgorithmException, InvalidKeyException {

        //定义签名算法
        String algorithm = "TC3-HMAC-SHA256";
        String secretId = "402883028953bcfc018962634d160000";
        String secretKey = "bt04w30vbnrvdopykclvl2ijq7j3ps2vb";
        String credential = "402883028953bcfc018962634d160000";
        String SignedHeaders = "content-type;X-SAAS-HOST";


        String canonicalHeaders = "content-type:" + "application/json; charset=utf-8" +
                "\n" + "X-SAAS-HOST:" + "https://test-open.tanbse.tencent.com" + ":" + "443" + "\n";
        String signedHeaders = "content-type;X-SAAS-HOST";
        String signature = SignatureUtil.sign(httpRequestMethod, canonicalUri,
                canonicalQueryString, canonicalHeaders, signedHeaders,
                payload, "TC3-HMAC-SHA256", "1689841328909",
                credential, secretKey);
        String authorization = SignatureUtil.authorization(algorithm, credential, SignedHeaders, signature);
        return authorization;
    }
}
