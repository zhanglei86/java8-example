package win.leizhang.java8example.test.security;

import org.junit.Test;

import javax.crypto.Cipher;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA加解密测试
 * Created by zhanglei863 on 2022/3/15.
 */
public class RSATest {
    // 加密算法
    private static final String RSA = "RSA";
    // java8的B64初始化
    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    // 公钥、私钥
    private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCVrdD6oZG42nLjINHwk9SBDUi2swKcFker5/4dmCpcpwQkekpeFc7zSDV4xT2A5petYlqxs/WjGuF9b7RRsAKEp4nXPUcAs0832iF5VvbjNwPL8KStDvz1/K8DBy4CvIcHxAkTrCoGVk0H8yy1SSmGAmg+jHdyUIYGOz+ca1cKRQIDAQAB";
    private static final String PRIVATE_KEY = "MIICeAIBADANBgkqhkiG9w0BAQEFAASCAmIwggJeAgEAAoGBAJWt0PqhkbjacuMg0fCT1IENSLazApwWR6vn/h2YKlynBCR6Sl4VzvNINXjFPYDml61iWrGz9aMa4X1vtFGwAoSnidc9RwCzTzfaIXlW9uM3A8vwpK0O/PX8rwMHLgK8hwfECROsKgZWTQfzLLVJKYYCaD6Md3JQhgY7P5xrVwpFAgMBAAECgYAgnJFmuiMs2nOjpvf26c6oG4PXQqNz7KrK+q30Nr4sF7ugUwC3BRtlCDuWR6tB1XwAq+hExYfkdKvij58DGU1C0kWuOuthCdm97HuIW4vumCsR5tFAvubBh67Thl6fOURY310Ee9uIPCMfTF53xEOhDQaGmI63LR+FKzis3rQNFQJBANRBI0bS3EhFg0VH/Z9RV8cnkykLm45VFyK/R+O4ND5ijnrF3kZNEg+CHMfGu5Bv/M1DuMm3Pj7K12sMZwzDRS8CQQC0hxpSwZgEOOo8uSAVcWgCMrUPwc9BFymWST5xW0ejGcSb0y009D3XwWikSz3j6EB10W5YQBd1WNKBI3KfxDLLAkEAmyPZQwUQmYqlflg2WuK7xAd7Gr8vPgOX23/wbdPP6+Fhvv8tjjj7AHbbznHF2rbgErBzOo1DY+YjOk1gbQW1ZQJBAJ5LoR9TtfJt3ablRWbIDEVxJSbt8MfIgaTQELrhKU2w+J8Sax910wxvAUye1WQUcQFeZP1pJai1+89Uv9ur9EUCQQCG6izNtV0mnIB8pFHKJcM+Bgb6eyO7t/OtIb0l2ivDK2rNK0fkbNZJW1c6c3d7QVBJ6EqqhVEcMXFQ75hLF0c4";

    @Test
    public void test1() throws Exception {
        // 原文
        String message = "timeStamp=20220315112233";
        // 公钥加密：调用方
        String messageEn = encrypt(message, PUBLIC_KEY);
        // 私钥解密：接口服务提供方
        String messageDe = decrypt(messageEn, PRIVATE_KEY);
        System.out.printf("RSA加密解密测试\n原文==>%s\n密文==>%s\n还原后的字符串==>%s\n", message, messageEn, messageDe);
    }

    @Test
    public void test2() throws NoSuchAlgorithmException {
        genKeyPair();
    }

    /**
     * RSA公钥加密
     *
     * @param str       原文
     * @param publicKey 公钥
     * @return 密文
     * @throws Exception 加密过程中的异常信息
     */
    public static String encrypt(String str, String publicKey) throws Exception {
        //base64编码的公钥
        byte[] decoded = DECODER.decode(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance(RSA).generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        return ENCODER.encodeToString(cipher.doFinal(str.getBytes(StandardCharsets.UTF_8)));
    }

    /**
     * RSA私钥解密
     *
     * @param str        加密字符串
     * @param privateKey 私钥
     * @return 铭文
     * @throws Exception 解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception {
        //64位解码加密后的字符串
        byte[] inputByte = DECODER.decode(str.getBytes(StandardCharsets.UTF_8));
        //base64编码的私钥
        byte[] decoded = DECODER.decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance(RSA).generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance(RSA);
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        return new String(cipher.doFinal(inputByte));
    }

    /**
     * 随机生成密钥对
     *
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(RSA);
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024, new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = ENCODER.encodeToString(publicKey.getEncoded());
        // 得到私钥字符串
        String privateKeyString = ENCODER.encodeToString(privateKey.getEncoded());

        // 输出
        System.out.printf("密钥对:\n公钥==>%s\n私钥==>%s\n", publicKeyString, privateKeyString);
    }

}
