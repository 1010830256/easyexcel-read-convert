package info.yuehui.easyexcel.annotation;

import info.yuehui.easyexcel.converter.Converter;
import info.yuehui.easyexcel.exception.ConvertException;

/**
 * 定义一个excel字段转换器接口
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/15 14:10
 */
public interface ExcelFieldConverter<T> {

    /**
     * String类型value转换成T
     *
     * @param value     value
     * @param converter 注解信息
     * @return t
     * @throws ConvertException 转换异常
     */
    T convert(String value, Converter converter) throws ConvertException;

}
