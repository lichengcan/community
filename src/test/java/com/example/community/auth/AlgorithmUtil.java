package com.example.community.auth;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @author: lichengcan
 * @date: 2023-07-20 15:01
 * @description
 **/
public class AlgorithmUtil {
    /**
     * SHA256
     * @param canonicalRequest 要计算SHA-256哈希值的输入数据
     * @return
     */
    public static byte[] Hash_SHA256(String canonicalRequest) {
        byte[] hashBytes = new byte[0];

        try {
            // 创建SHA-256 MessageDigest实例
            MessageDigest sha256Digest = MessageDigest.getInstance("SHA-256");

            // 将输入数据转换为字节数组
            byte[] inputData = canonicalRequest.getBytes();

            // 计算SHA-256哈希值
            hashBytes = sha256Digest.digest(inputData);

            // 将哈希值转换为十六进制字符串
//            for (byte b : hashBytes) {
//                String hex = Integer.toHexString(0xff & b);
//                if (hex.length() == 1) hashHex.append('0');
//                hashHex.append(hex);
//            }

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashBytes;
    }

    /**
     * 将byte[]转换为十六进制字符串
     * @param bytes
     * @return
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xFF & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    /**
     * 将字符转换为十六进制字符串
     *
     * @param HMAC_SHA256
     * @return
     */
    public static String StringHexEncode(String HMAC_SHA256) {
        // 使用StringBuilder来构建十六进制编码后的结果
        StringBuilder hexEncodedString = new StringBuilder();

        // 遍历输入字符串的每个字符
        for (char c : HMAC_SHA256.toCharArray()) {
            // 将字符转换为十六进制表示形式并添加到结果字符串中
            String hexValue = Integer.toHexString((int) c);
            hexEncodedString.append(hexValue);
        }
        return hexEncodedString.toString();
    }


    /**
     * HMAC_SHA256算法
     * @param secretKey 密钥
     * @param message 要计算HMAC-SHA256的消息
     */
    public static byte[] HMAC_SHA256(byte[] secretKey,String message) {
        byte[] hmacBytes = new byte[0];
        try {

            // 转换密钥为SecretKeySpec对象
            SecretKeySpec secretKeySpec = new SecretKeySpec(secretKey, "HmacSHA256");

            // 获取Mac实例并初始化密钥
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(secretKeySpec);

            // 计算HMAC-SHA256
            hmacBytes= mac.doFinal(message.getBytes());

            // 将结果转换为Base64编码（可选，根据需求决定是否编码）
//            String hmacBase64 = Base64.getEncoder().encodeToString(hmacBytes);

        } catch (NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return hmacBytes;
    }

}
