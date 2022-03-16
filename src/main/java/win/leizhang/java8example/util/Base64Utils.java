package win.leizhang.java8example.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.util.Base64;

/**
 * Created by zealous on 2019-03-14.
 */
public class Base64Utils {
    // java8的B64初始化
    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    /**
     * 字节数组转Base64编码
     *
     * @param bytes
     * @return
     */
    public static String byte2Base64(byte[] bytes) {
        return ENCODER.encodeToString(bytes);
    }

    /**
     * Base64编码转字节数组
     *
     * @param base64Key
     * @return
     */
    public static byte[] base642Byte(String base64Key) {
        return DECODER.decode(base64Key);
    }

    @Deprecated
    public static String b2b64(byte[] bytes) {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(bytes);
    }

    @Deprecated
    public static byte[] b642b(String str) throws IOException {
        BASE64Decoder decoder = new BASE64Decoder();
        return decoder.decodeBuffer(str);
    }

}
