package info.yuehui.easyexcel.util;

import cn.hutool.core.map.MapUtil;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.groups.Default;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * hibernate校验工具类
 *
 * @author JiangDingCheng
 * @date 2021-12-03 14:15
 */
public class ValidatorUtils {

    private static final Validator VALIDATOR = Validation.buildDefaultValidatorFactory().getValidator();

    /**
     * 校验对象
     *
     * @param obj 需要校验的对象
     * @param <T> 泛型
     * @return 校验结果 key字段名 : value提示信息
     */
    public static <T> Map<String, StringBuffer> validate2Map(T obj) {
        Set<ConstraintViolation<T>> set = VALIDATOR.validate(obj, Default.class);
        return getMapMsg(set);
    }

    /**
     * 校验对象
     *
     * @param obj 需要校验的对象
     * @return 拼接的提示信息
     */
    public static <T> String validate2Str(T obj) {
        Set<ConstraintViolation<T>> set = VALIDATOR.validate(obj, Default.class);
        return getMsg(set);
    }

    /**
     * 校验对象
     *
     * @param obj    需要校验的对象
     * @param groups 需要校验的分组
     * @return 拼接的提示信息
     */
    public static <T> String validate2Str(T obj, Class<?>... groups) {
        Set<ConstraintViolation<T>> set = VALIDATOR.validate(obj, groups);
        return getMsg(set);
    }

    /**
     * 校验具体某个对象字段
     *
     * @param clazz    需要校验的对象Class对象
     * @param filedStr 需要校验的字段名
     * @param filed    需要校验的字段
     * @param <T>      泛型
     * @return 校验结果 key字段名 : value提示信息
     */
    public static <T> Map<String, StringBuffer> validateFiled2Map(Class<T> clazz, String filedStr, Object filed) {
        Set<ConstraintViolation<T>> set = VALIDATOR.validateValue(clazz, filedStr, filed);
        return getMapMsg(set);
    }

    /**
     * 校验具体某个对象字段
     *
     * @param clazz    需要校验的对象Class对象
     * @param filedStr 需要校验的字段名
     * @param filed    需要校验的字段
     * @return 拼接的提示信息
     */
    public static <T> String validateFiled2Str(Class<T> clazz, String filedStr, Object filed) {
        Set<ConstraintViolation<T>> set = VALIDATOR.validateValue(clazz, filedStr, filed);
        return getMsg(set);
    }

    /**
     * 校验结果 key字段名 : value提示信息
     */
    private static <T> Map<String, StringBuffer> getMapMsg(Set<ConstraintViolation<T>> set) {
        Map<String, StringBuffer> errMap = new HashMap<>();

        if (Objects.nonNull(set) && set.size() > 0) {
            errMap = new HashMap<>();
            String property;
            for (ConstraintViolation<T> cv : set) {
                property = cv.getPropertyPath().toString();
                if (errMap.get(property) != null) {
                    errMap.get(property).append(",").append(cv.getMessage());
                } else {
                    StringBuffer stringBuffer = new StringBuffer();
                    stringBuffer.append(cv.getMessage());
                    errMap.put(property, stringBuffer);
                }
            }
        }
        return errMap;
    }

    /**
     * 校验结果拼接的提示信息
     */
    private static <T> String getMsg(Set<ConstraintViolation<T>> set) {
        Map<String, StringBuffer> mapMsg = getMapMsg(set);
        if (MapUtil.isNotEmpty(mapMsg)) {
            mapMsg.forEach((k, v) -> v = v.insert(0, "[").append("]"));
        }
        return String.join("", mapMsg.values());
    }
}


