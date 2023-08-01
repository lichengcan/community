package com.example.community.util;

import cn.hutool.core.text.StrSplitter;
import org.springframework.http.HttpHeaders;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * 签名工具类
 * @author xiankun.geng
 */
public class SignatureUtil {

    private static final Charset UTF8 = StandardCharsets.UTF_8;

    private static final String NEWLINE_CHAR = "\n";

    private SignatureUtil() {
    }

    /**
     * 拼接 Authorization
     * @param algorithm 签名算法，目前固定为 TC3-HMAC-SHA256。
     * @param credential 密钥凭证（SecretId）
     * @param signedHeaders 参与签名的头部信息，说明此次请求有哪些头部参与了签名
     * @param signature 签名
     */
    public static String authorization(String algorithm, String credential, String signedHeaders, String signature) {
        return algorithm + " " + "Credential=" + credential + ", " + "SignedHeaders=" + signedHeaders + ", "
                + "Signature=" + signature;
    }

    /**
     * 计算签名
     * @param httpRequestMethod 请求方式
     * @param canonicalUri 请求路径
     * @param canonicalQueryString GET 请求参数
     * @param canonicalHeaders 参与签名的头部信息，至少包含 host 和 content-type 两个头部
     * @param signedHeaders 参与签名的头部信息，说明此次请求有哪些头部参与了签名
     * @param payload POST 请求参数
     * @param algorithm 签名算法，目前固定为 TC3-HMAC-SHA256。
     * @param timestamp 请求时间戳
     * @param credential 密钥凭证（SecretId）
     * @param secretKey 密钥
     */
    public static String sign(String httpRequestMethod,
                            String canonicalUri,
                            String canonicalQueryString,
                            String canonicalHeaders,
                            String signedHeaders,
                            String payload,
                            String algorithm,
                            String timestamp,
                            String credential,
                            String secretKey) throws NoSuchAlgorithmException, InvalidKeyException {
        // 拼接规范请求
        String hashedRequestPayload = sha256Hex(payload);
        String canonicalRequest = httpRequestMethod + NEWLINE_CHAR + canonicalUri + NEWLINE_CHAR + canonicalQueryString + NEWLINE_CHAR
                + canonicalHeaders + NEWLINE_CHAR + signedHeaders + NEWLINE_CHAR + hashedRequestPayload;
        System.out.println("规范请求：" + canonicalRequest);
        // 拼接签名字符串
        String hashedCanonicalRequest = sha256Hex(canonicalRequest);
        String stringToSign = algorithm + NEWLINE_CHAR + timestamp + NEWLINE_CHAR + credential + NEWLINE_CHAR + hashedCanonicalRequest;
        System.out.println("签名字符串：" + stringToSign);
        // 计算签名
        byte[] secretSigning = hmac256(secretKey.getBytes(UTF8), timestamp2date(timestamp));
        System.out.println("签名：" + secretSigning + "，secretKey：" + secretKey + "，timestamp：" + timestamp);
        return DatatypeConverter.printHexBinary(hmac256(secretSigning, stringToSign)).toLowerCase();
    }

    private static byte[] hmac256(byte[] key, String msg) throws NoSuchAlgorithmException, InvalidKeyException {
        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, mac.getAlgorithm());
        mac.init(secretKeySpec);
        return mac.doFinal(msg.getBytes(UTF8));
    }

    private static String sha256Hex(String s) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] d = md.digest(s.getBytes(UTF8));
        return DatatypeConverter.printHexBinary(d).toLowerCase();
    }

    private static String timestamp2date(String timestamp) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        return sdf.format(new Date(Long.parseLong(timestamp + "000")));
    }

    public static String canonicalHeaders(String signedHeaders, HttpHeaders headers) {
        StringBuilder canonicalHeaders = new StringBuilder();
        List<String> signedHeaderList = StrSplitter.split(signedHeaders, ";", 0, true, true);
        for (String signedHeader : signedHeaderList) {
            canonicalHeaders.append(signedHeader).append(":").append(headers.getFirst(signedHeader)).append("\n");
        }
        return canonicalHeaders.toString();
    }

}
