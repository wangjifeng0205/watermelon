package net.wangjifeng.watermelon.base;

/**
 * @author: wjf
 * @date: 2021/9/18 15:59
 *
 * 自定义异常。
 */
public final class WatermelonException extends RuntimeException {

    private WatermelonException(String message) {
        super(message);
    }

    public static WatermelonException newEx(String message) {
        return new WatermelonException(message);
    }

    public static void throwEx(Exception e) {
        throwEx(e.getMessage());
    }

    public static void throwEx(String message) throws WatermelonException {
        throw WatermelonException.newEx(message);
    }

}
