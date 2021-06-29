package cc.ccoder.tinylink.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * <p>
 * </p>
 *
 * @author chencong
 * @email cong.chen@payby.com | cong.ccoder@gmail.com
 * @date TinyLinkConstant.java v1.0 2021/6/28 22:26
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TinyLinkConstant {

    /**
     * 种子字符
     */
    public static final String[] STOCK_CHAR =
        {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v",
            "w", "x", "y", "z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G",
            "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * MD5加密盐值
     */
    public static final String SALT = "ccoder";

    public static final String HTTP_LINK = "http://";

    public static final String HTTPS_LINK = "https://";

}
