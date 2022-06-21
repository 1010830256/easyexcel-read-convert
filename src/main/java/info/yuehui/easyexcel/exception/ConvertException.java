package info.yuehui.easyexcel.exception;

/**
 * 使用excel字段转换器时的异常
 *
 * @author zhangxing
 * @version v1.0
 * @date 2022/6/15 14:11
 */
public class ConvertException extends Exception {

    public ConvertException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConvertException(String message) {
        super(message);
    }

}
