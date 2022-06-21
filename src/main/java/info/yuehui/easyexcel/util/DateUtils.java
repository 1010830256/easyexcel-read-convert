package info.yuehui.easyexcel.util;

import cn.hutool.core.date.LocalDateTimeUtil;

import java.time.LocalDate;

/**
 * 日期工具类
 *
 * @author zhangxing
 * @date 2021/5/11
 */
public class DateUtils {

    /**
     * 常用日期格式
     */
    public static final String[] COMMON_DATE_FORMAT = {
            "yyyy-M-d", "yyyy-MM-dd",
            "yyyy年M月d日", "yyyy年MM月dd日",
            "yyyy/M/d", "yyyy/MM/dd",
            "yyyy.M.d", "yyyy.MM.dd"
    };

    /**
     * 尝试格式化
     *
     * @param useDate    日期字符串
     * @param formatList 格式列表
     * @return 解析的日期
     */
    public static LocalDate tryFormat(String useDate, String[] formatList) {
        for (String format : formatList) {
            LocalDate localDate = null;
            try {
                localDate = LocalDateTimeUtil.parseDate(useDate, format);
            } catch (Exception ignored) {
            }
            if (localDate != null) {
                return localDate;
            }
        }
        return null;
    }

    /**
     * 尝试格式化
     *
     * @param useDate 日期字符串
     * @return 解析的日期
     */
    public static LocalDate tryFormat(String useDate) {
        return tryFormat(useDate, COMMON_DATE_FORMAT);
    }

}
