package cc.ccoder.tinylink.ext.strategy;

import java.nio.charset.StandardCharsets;

import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import cc.ccoder.tinylink.common.TinyLinkConstant;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date DigestAlgorithmStrategy.java v1.0 2021/6/28 23:09
 */
@Component
public class DigestAlgorithmStrategy implements TinyLinkStrategy {
    @Override
    public String getServiceCode() {
        return AlgorithmStrategy.DIGEST.getCode();
    }

    @Override
    public String algorithmGenerate(String originLink) {
        String encryptResult =
            DigestUtils.md5DigestAsHex((originLink + TinyLinkConstant.SALT).getBytes(StandardCharsets.UTF_8));
        String[] result = new String[4];
        for (int i = 0; i < 4; i++) {
            // 将长网址md5生成32位签名串,分为4段, 每段8个字节；
            String temp = encryptResult.substring(i * 8, i * 8 + 8);
            // 对这四段循环处理, 取8个字节, 将他看成16进制串与0x3fffffff(30位1)与操作, 并且这里用long 避免Integer超过31位
            long hexLong = 0x3FFFFFF & Long.parseLong(temp, 16);
            String outChars = "";
            // 这30位分成6段, 每5位的数字作为字母表的索引取得特定字符, 依次进行获得6位字符串；
            for (int j = 0; j < 6; j++) {
                // 把得到的值与 0x0000003D 进行位与运算，取得字符数组 chars 索引
                long index = 0x0000003D & hexLong;
                // 把取得的字符相加
                outChars += TinyLinkConstant.STOCK_CHAR[(int)index];
                // 每次循环按位右移 5 位
                hexLong = hexLong >> 5;
            }
            result[i] = outChars;
        }
        // 总的md5串可以获得4个6位串；取里面的任意一个就可作为这个长url的短url地址;
        int index = (int)(Math.random() * 4);
        return result[index];
    }
}
