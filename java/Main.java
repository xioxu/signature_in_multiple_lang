package com.mydoor.rsa;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author xietansheng
 */
public class Main {
    /**
     * 缺省的1024位密钥对,可处理117个字节的数据
     */
    private static final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAu7jconatLNx3Fx1k3Z6V" +
            "JhO86fI1rz5GfWlXsKegXvYL8FRJcq7acVo0HUT2FP7No0HbATJRwpDg79gvAp9y" +
            "tvX7yIN4NnX2hKOv5b9qrftTsf5m0bnzP5BPZAIrkWLKNK3LAtssKs+txAEKNrZY" +
            "u/t+cNq94pzrbjLiGiXChpnuHLbwE90+cJO9NjPVks7iQ5dsZd9pEyFeqUnHxFgk" +
            "gXd5FkCfI7dY4/fjMCcZRajBogjkQEvP+I9ojLnbK84QG5M3wNdvCD1q9+sSFBB9" +
            "ZuXCi2ell5lLkwy1u5tHXlR7omR7tCd4Qa6oYrF79iFE8hJF8J4i0BixyqNHjlXi" +
            "BwIDAQAB";

    private static final String PRIVATE_KEY = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQC7uNyidq0s3HcX" +
            "HWTdnpUmE7zp8jWvPkZ9aVewp6Be9gvwVElyrtpxWjQdRPYU/s2jQdsBMlHCkODv" +
            "2C8Cn3K29fvIg3g2dfaEo6/lv2qt+1Ox/mbRufM/kE9kAiuRYso0rcsC2ywqz63E" +
            "AQo2tli7+35w2r3inOtuMuIaJcKGme4ctvAT3T5wk702M9WSzuJDl2xl32kTIV6p" +
            "ScfEWCSBd3kWQJ8jt1jj9+MwJxlFqMGiCORAS8/4j2iMudsrzhAbkzfA128IPWr3" +
            "6xIUEH1m5cKLZ6WXmUuTDLW7m0deVHuiZHu0J3hBrqhisXv2IUTyEkXwniLQGLHK" +
            "o0eOVeIHAgMBAAECggEAOVj3JjalHuBXDVuwdbe9jr1W6A1Tcy7OL//fhxsedgQq" +
            "j4fgTEW4fAp3gz2PA8j3n1k+A3aQ3aSO4xai/eUiEVzCR3ZKYjDVV5393W0FtRLj" +
            "davnU0jAU/nNFxtGngqVBVTWgQ/dxVJenlNHAIHiR8ND1Ewu2CziC29F/YDtlVqp" +
            "FgenqZO+1GXtqR+MF/NxIa7DUFiMLTjcjZH4l/8HG4G2pGxmruLbzKprW3FMBzS4" +
            "cQly7LTO1zi401m0JpmZlNwkZlsoWhMOwUc7bs8aaWmsust+SINpqXyLy8JcH3VI" +
            "mtgPQzxYaYEzVuloyvOUhLQRMWA/Goh8TuU5DaavgQKBgQDgbcvmPlhY3a+FMFX1" +
            "l0Zq29L14sAB5A+8NJpVDDjlNAZL76pE+FLCwUNILqnNFzkuvvcMEZsbJKXLlmSt" +
            "kzbyYVL6xohN9LUzp1I6zD7OimWawLG9grEhvuktp4tT62sqImv4jVDlPJzvRGz2" +
            "kMW/hCbY8MDOezCYRJOBeq2IRwKBgQDWIStiZ1hENJSvg0ZWpfvqVFiR4wlaMhYF" +
            "9JUusfAtttGL//IbeSdoH23ogctahYC5nuFbt0RfGzDFEqKrzvZFgC2HMozUAja+" +
            "ARpiqp00Qc66JBX74A0TdBVY/opfAwlVy2ctwlp/dVCSNQ5525pceGjwq4GEA/+a" +
            "e/QUI/N4QQKBgF3hsDTdiamK+rx1bs/wexWCZWpUc0uXMnnha1n+ZeKDc8raM9js" +
            "I/qyS8nrMz7G4zXJzqBu0Pp2xyA2BmGRvp9/1O1wlV4+6nn0GXLdM7zEqiIlT3Te" +
            "MejS6sodVyxAw7B5e78apmduhpuUfRxbjU1DI9kGRTFa2QYpUacNQYBLAoGBAKkL" +
            "Qo7K+1nNENyGjE73dqsFr25siAOWquBEuElG3E+alk8p3d5mhP+kYEY/wWRTJM5l" +
            "aY/YXLegZZ4PGjD8kFtJ16d19suge8sAX+4otzZ8BVHaDyjrNid3ayr4uBBN+16p" +
            "12i+mGcrHFJwZF87SN9bh60IGJU5+t3goU4NG7aBAoGAYyqYD9gx3g9QL4I03gbw" +
            "EYqXX1U+u+exN+PBSoIiIynapQ3yYPRyRHT7/vt/vUKsGYQONlwA8xrjqz4SStT8" +
            "lk/6o+zk7csZv4gt8dB20ZzMckCx5Ve9Hq7pMsav4Usbl9RCGgFHAZyGM4pLx4UB" +
            "K/VjsfzGkRZErocpNwb8NCc=";

    /**
     * 算法
     */
    public static final String RSA_ALGORITHM_NO_PADDING = "RSA";
    /**
     * 签名算法
     */
    private static final String SIGNATURE_INSTANCE = "SHA256withRSA";

    /**
     * 字符集
     */
    private static final String CHARSET = "utf-8";

    public static void main(String[] args) throws Exception {

        // 原始数据
        String data = "{\"success\":true,\"message\":\"msg test\"}";
        String signature = sign(data, PRIVATE_KEY);

        System.out.println("数据:" + data);
        System.out.println("签名内容:" + signature);

        // 篡改原始
        String data_modify = "{\"success\":true,\"message\":\"nothing\"}";
        boolean verifyResult = verify(data_modify, signature, PUBLIC_KEY);
        System.out.println("验签结果_篡改后数据:" + verifyResult);

        verifyResult = verify(data, signature, PUBLIC_KEY);
        System.out.println("验签结果_原始数据:" + verifyResult);
    }

    /**
     * 公钥字符串转PublicKey实例
     *
     * @param publicKey 公钥字符串
     *
     * @return PublicKey实例
     * @throws Exception e
     */
    public static PublicKey getPublicKey(String publicKey) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM_NO_PADDING);
        return keyFactory.generatePublic(keySpec);
    }

    /**
     * 私钥字符串转PrivateKey实例
     *
     * @param privateKey 私钥字符串
     *
     * @return PrivateKey实例
     * @throws Exception e
     */
    public static PrivateKey getPrivateKey(String privateKey) throws Exception {
        byte[] privateKeyBytes = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(RSA_ALGORITHM_NO_PADDING);
        return keyFactory.generatePrivate(keySpec);
    }

    /**
     * 签名
     *
     * @param content    明文
     * @param privateKey 私钥
     *
     * @return 签名后的密文
     * @throws Exception e
     */
    public static byte[] sign(byte[] content, PrivateKey privateKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_INSTANCE);
        signature.initSign(privateKey);
        signature.update(content);
        return signature.sign();
    }

    /**
     * 签名
     *
     * @param content    明文
     * @param privateKey 密钥字符串
     *
     * @return 签名后的密文
     * @throws Exception e
     */
    public static String sign(String content, String privateKey) throws Exception {
        return new String(Base64.getEncoder().encode(sign(content.getBytes(CHARSET), getPrivateKey(privateKey))),
                CHARSET);
    }

    /**
     * 验签
     *
     * @param content   明文
     * @param sign      签名
     * @param publicKey 公钥
     *
     * @return true or false
     * @throws Exception e
     */
    public static boolean verify(byte[] content, byte[] sign, PublicKey publicKey) throws Exception {
        Signature signature = Signature.getInstance(SIGNATURE_INSTANCE);
        signature.initVerify(publicKey);
        signature.update(content);
        return signature.verify(sign);
    }

    /**
     * 验签
     *
     * @param content   明文
     * @param sign      签名
     * @param publicKey 公钥字符串
     *
     * @return true or false
     * @throws Exception e
     */
    public static boolean verify(String content, String sign, String publicKey) throws Exception {
        return verify(content.getBytes(CHARSET), Base64.getDecoder().decode(sign), getPublicKey(publicKey));
    }
}
