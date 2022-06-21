package info.yuehui.easyexcel.converter;


import info.yuehui.easyexcel.annotation.ExcelFieldConverter;
import info.yuehui.easyexcel.exception.ConvertException;
import info.yuehui.easyexcel.util.DateUtils;

import java.time.LocalDate;

/**
 * LocalDate类型的字段转换器
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/21 01:21
 */
public class LocalDateFieldConverter implements ExcelFieldConverter<LocalDate> {

    @Override
    public LocalDate convert(String value, Converter converter) throws ConvertException {
        if (value == null) {
            return null;
        }
        String trim = value.trim();
        LocalDate localDate = DateUtils.tryFormat(trim);
        if (localDate == null) {
            throw new ConvertException(converter.errorMsg());
        }
        return localDate;
    }
}
