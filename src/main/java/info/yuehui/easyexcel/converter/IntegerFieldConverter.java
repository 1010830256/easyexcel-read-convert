package info.yuehui.easyexcel.converter;


import info.yuehui.easyexcel.annotation.ExcelFieldConverter;
import info.yuehui.easyexcel.exception.ConvertException;

/**
 * Integer类型的字段转换器
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/15 15:39
 */
public class IntegerFieldConverter implements ExcelFieldConverter<Integer> {

    @Override
    public Integer convert(String value, Converter converter) throws ConvertException {
        if (value == null) {
            return null;
        }
        try {
            String trim = value.trim();
            return Integer.valueOf(trim);
        } catch (NumberFormatException e) {
            throw new ConvertException(converter.errorMsg(), e);
        }
    }

}
