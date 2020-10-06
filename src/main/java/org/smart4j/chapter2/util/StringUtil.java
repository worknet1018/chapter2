package org.smart4j.chapter2.util;

import org.apache.commons.lang3.StringUtils;

/**
 * 字符串工具类
 */
public final class StringUtil {
    /**
     * 判断字符串是否非空
     * @param strValue
     * @return
     */
    public static boolean isNotEmpty(String strValue) {
        return !isEmpty(strValue);
    }

    /**
     * 判断字符串是否为空
     * @param strValue
     * @return
     */
    private static boolean isEmpty(String strValue) {
        if (strValue != null) {
            strValue =strValue.trim();
        }
        return StringUtils.isEmpty(strValue);
    }
}
