package info.yuehui.easyexcel.util;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 反射工具类
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/21 00:40
 */
public class ReflectUtils {

    /**
     * 获取字段, 父类和子类有相同字段以子类为准
     *
     * @param clazz                class类型
     * @param withSuperClassFields 是否返回父类字段
     * @return 字段
     */
    public static Collection<Field> getFields(Class<?> clazz, boolean withSuperClassFields) {
        Map<String, Field> map = new HashMap<>();
        Class<?> cur = clazz;

        do {
            Field[] curFields = cur.getDeclaredFields();
            for (Field curField : curFields) {
                map.putIfAbsent(curField.getName(), curField);
            }
            // 使用父类继续找字段
            cur = cur.getSuperclass();

            // 如果当前类是Object，则不需要再找字段了
        } while (withSuperClassFields && cur != null && !Object.class.equals(cur));

        return map.values();
    }

}
