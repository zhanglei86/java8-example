/*
package win.leizhang.java8example.typical;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

*/
/**
 * 生成二维码
 * 参考：[Java骚操作--生成二维码](https://www.cnblogs.com/lsy131479/p/8808172.html) 2018-05-14 20:40 房上的猫
 * Created by zealous on 2019-05-18.
 *//*

public class QrCodeTest {

    @Test
    public void test1() throws Exception {
        // 二维码内容
        String content = "hello,word";
        String path = "/Volumes/IntelSSD/tmpp/1.jpg";
        orCode(content, path);
    }

    private static void orCode(String content, String path) throws IOException, WriterException {
        // 图片的宽度和高度
        int width = 300;
        int height = 300;
        // 图片的格式
        String format = "png";

        // 定义二维码的参数
        Map<EncodeHintType, Object> hints = new HashMap();
        // 定义字符集编码格式
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        // 纠错的等级 L > M > Q > H 纠错的能力越高可存储的越少，一般使用M
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        // 设置图片边距
        hints.put(EncodeHintType.MARGIN, 2);

        // 最终生成 参数列表 （1.内容 2.格式 3.宽度 4.高度 5.二维码参数）
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, width, height, hints);
        // 写入到本地
        Path file = new File(path).toPath();
        MatrixToImageWriter.writeToPath(bitMatrix, format, file);
    }

}
*/
