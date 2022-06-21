package info.yuehui.easyexcel.converter;


import info.yuehui.easyexcel.annotation.ExcelFieldConverter;
import info.yuehui.easyexcel.exception.ConvertException;

import java.math.BigDecimal;

/**
 * BigDecimal类型的字段转换器
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/15 14:11
 */
public class BigDecimalFieldConverter implements ExcelFieldConverter<BigDecimal> {

    @Override
    public BigDecimal convert(String value, Converter converter) throws ConvertException {
        if (value == null) {
            return null;
        }

        String trim = value.trim();

        BigDecimal bigDecimal;
        try {
            bigDecimal = new BigDecimal(trim);
        } catch (Exception e) {
            throw new ConvertException(converter.errorMsg(), e);
        }
        return bigDecimal;
    }

}
