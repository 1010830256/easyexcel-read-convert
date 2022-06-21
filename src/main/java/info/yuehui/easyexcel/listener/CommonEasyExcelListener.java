package info.yuehui.easyexcel.listener;

import cn.hutool.core.util.ReflectUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import info.yuehui.easyexcel.annotation.ExcelFieldConverter;
import info.yuehui.easyexcel.converter.Converter;
import info.yuehui.easyexcel.domain.ConvertWrap;
import info.yuehui.easyexcel.exception.ConvertException;
import info.yuehui.easyexcel.util.ReflectUtils;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 通用easyexcel读取listener
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/21 00:06
 */
public class CommonEasyExcelListener<T> extends AnalysisEventListener<Map<Integer, String>> {

    private final Consumer<ConvertWrap<T>> objConsumer;
    private final Class<T> clazz;
    private Consumer<AnalysisContext> doAfterAllAnalysed;

    public CommonEasyExcelListener(Class<T> clazz, Consumer<ConvertWrap<T>> objConsumer) {
        this.clazz = clazz;
        this.objConsumer = objConsumer;
    }

    public CommonEasyExcelListener(Consumer<ConvertWrap<T>> objConsumer, Class<T> clazz, Consumer<AnalysisContext> doAfterAllAnalysed) {
        this.objConsumer = objConsumer;
        this.clazz = clazz;
        this.doAfterAllAnalysed = doAfterAllAnalysed;
    }

    @Override
    public void invoke(Map<Integer, String> rowMap, AnalysisContext analysisContext) {
        if (objConsumer != null) {
            objConsumer.accept(map2Obj(clazz, rowMap));
        }
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
        if (doAfterAllAnalysed != null) {
            doAfterAllAnalysed.accept(analysisContext);
        }
    }

    /**
     * 将行对象转换成class对象
     *
     * @param clazz map要转什么对象的字节码文件
     * @param row   map结构的row
     */
    public ConvertWrap<T> map2Obj(Class<T> clazz, Map<Integer, String> row) {
        // 创建一个clazz实例[必须有无参构造器]
        T obj = ReflectUtil.newInstance(clazz);

        // 实例化转换包装对象
        ConvertWrap<T> convertWrap = new ConvertWrap<>();
        convertWrap.setObj(obj);

        // 获取到所有字段
        Collection<Field> allField = ReflectUtils.getFields(clazz, true);

        for (Field field : allField) {
            // 设置访问权限
            field.setAccessible(true);

            // 没有ExcelProperty注解的字段自动忽略
            ExcelProperty excelProperty = field.getAnnotation(ExcelProperty.class);
            if (excelProperty == null) {
                continue;
            }

            // 通过列所在索引，拿到单元格的值
            String value = row.get(excelProperty.index());
            Class<?> type = field.getType();

            // 字符串类型字段直接赋值
            if (String.class.equals(type)) {
                ReflectUtil.setFieldValue(obj, field, value);
            } else {
                // 非字符串字段, 指定转换器
                Converter converter = field.getDeclaredAnnotation(Converter.class);
                if (converter == null) {
                    throw new IllegalArgumentException(StrUtil.format("Class[{}]中字段[{}]没有配置excel解析转换器, 请添加[@{}:{}]尝试转换", clazz.getName(), field.getName(), Converter.class.getSimpleName(), Converter.class.getName()));
                } else {
                    ExcelFieldConverter<?> excelFieldConverter = ReflectUtil.newInstance(converter.value());
                    try {
                        Object convertValue = excelFieldConverter.convert(value, converter);
                        ReflectUtil.setFieldValue(obj, field, convertValue);
                    } catch (ConvertException e) {
                        // 添加一个转换异常
                        convertWrap.getExceptions().add(new ConvertWrap.Exception(e.getMessage(), type, value));
                    }
                }
            }
        }

        return convertWrap;
    }

}
