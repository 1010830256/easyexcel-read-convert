package info.yuehui.easyexcel.converter;


import info.yuehui.easyexcel.annotation.ExcelFieldConverter;
import info.yuehui.easyexcel.exception.ConvertException;

import java.util.Arrays;
import java.util.List;

/**
 * Boolean类型的字段转换器
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/21 01:19
 */
public class BooleanFieldConverter implements ExcelFieldConverter<Boolean> {

    public static final List<String> TRUE_TEXT_ARR = Arrays.asList("是", "true", "T", "1", "√");
    public static final List<String> FALSE_TEXT_ARR = Arrays.asList("否", "false", "F", "0", "×");

    @Override
    public Boolean convert(String value, Converter converter) throws ConvertException {
        if (value == null) {
            return null;
        }
        String trim = value.trim();
        if (TRUE_TEXT_ARR.contains(trim)) {
            return true;
        }
        if (FALSE_TEXT_ARR.contains(trim)) {
            return false;
        }
        throw new ConvertException(converter.errorMsg());
    }
}
