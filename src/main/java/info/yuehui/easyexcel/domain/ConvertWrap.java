package info.yuehui.easyexcel.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 转换包装类
 * 包括转换后的对象以及转换异常
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/20 23:18
 */
@Data
public class ConvertWrap<T> {

    /**
     * 解析出的对象
     */
    private T obj;

    /**
     * 解析异常
     */
    private List<Exception> exceptions = new ArrayList<>();

    @Data
    public static class Exception {
        private String msg;
        private Class<?> fieldType;
        private String value;

        public Exception() {
        }

        public Exception(String msg, Class<?> fieldType, String value) {
            this.msg = msg;
            this.fieldType = fieldType;
            this.value = value;
        }
    }

}
