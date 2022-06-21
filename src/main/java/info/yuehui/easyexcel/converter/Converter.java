package info.yuehui.easyexcel.converter;


import info.yuehui.easyexcel.annotation.ExcelFieldConverter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 用于标记需要转换类型的字段
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/15 14:16
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Converter {

    /**
     * 需要使用的转换器
     *
     * @return 转换器
     */
    Class<? extends ExcelFieldConverter<?>> value();

    /**
     * 转换异常的错误信息
     *
     * @return 错误信息
     */
    String errorMsg();

}
