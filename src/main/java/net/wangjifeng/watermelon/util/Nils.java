package net.wangjifeng.watermelon.util;

import net.wangjifeng.watermelon.base.Castor;
import net.wangjifeng.watermelon.base.SimpleCastor;
import net.wangjifeng.watermelon.base.WatermelonException;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author: wjf
 * @date: 2021/9/18 14:56
 *
 * @apiNote Nil: 零值，主要用作字符串，java.util包下的容器类。
 *          常见的容易的nil如下:
 *          1. [Array]: length = 0
 *          2. [String]: string = ' ... '
 *          3. [Collection]: size = 0
 *          4. [Map]: size = 0
 *          5. [Iterable]: iterator().hasNext() = false
 *          6. [Iterator]: hasNext() = false
 *          7. [Optional]: isPresent() = false
 *          8. [<T>]: null == obj
 */
public class Nils {

    private Nils() {}

    // ***** PUBLIC *****

    /**
     * 验证一个对象是否不是零值。
     *
     * @param obj 对象
     * @return 是否不是零值
     */
    public static boolean isNotNil(Object obj) {
        if (null == obj) {
            return false;
        }
        if (obj instanceof CharSequence) {
            return isNotBlank(SimpleCastor.<Object, CharSequence>getSimpleCastor().cast(obj));
        } else if (obj instanceof Collection<?>) {
            return !SimpleCastor.<Object, Collection<?>>getSimpleCastor().cast(obj).isEmpty();
        } else if (obj instanceof Map<?, ?>) {
            return !SimpleCastor.<Object, Map<?, ?>>getSimpleCastor().cast(obj).isEmpty();
        } else if (obj instanceof Iterable<?>) {
            return SimpleCastor.<Object, Iterable<?>>getSimpleCastor().cast(obj).iterator().hasNext();
        } else if (obj instanceof Iterator<?>) {
            return SimpleCastor.<Object, Iterator<?>>getSimpleCastor().cast(obj).hasNext();
        } else if (obj instanceof Optional<?>) {
            return SimpleCastor.<Object, Optional<?>>getSimpleCastor().cast(obj).isPresent();
        } else if (obj.getClass().isArray()) {
            return Array.getLength(obj) != 0;
        }
        return true;
    }

    /**
     * 是否全都不是null。
     *
     * @param objs 对象
     * @return 是否全都不是null
     */
    public static boolean isAllNotNull(Object... objs) {
        if (isNull(objs)) {
            return false;
        }
        for (Object obj : objs) {
            if (isNull(obj)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 验证一个对象是否是零值。
     *
     * @param obj 对象
     * @return 是否是零值
     */
    public static boolean isNil(Object obj) {
        return !isNotNil(obj);
    }

    /**
     * 验证一个对象是否是null。
     *
     * @param obj 对象
     * @return 是否是null
     */
    public static boolean isNull(Object obj) {
        return null == obj;
    }

    /**
     * 验证一个对象是否不是null。
     *
     * @param obj 对象
     * @return 是否不是null
     */
    public static boolean isNotNull(Object obj) {
        return !isNull(obj);
    }

    /**
     * 验证一个对象必须不是零值，否则抛出异常{@link UnsupportedOperationException}。
     *
     * @param obj 对象
     * @param msg 错误提示
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T requireNonNil(T obj, String... msg) {
        if (!Nils.isNotNil(obj)) {
            WatermelonException.throwEx(Nils.isNotNil(msg) ? msg[0] : "obj must not nil");
        }
        return obj;
    }

    /**
     * 断言表达式expression。
     *
     * @param expression 表达式
     * @param message 断言不成立时的提示
     */
    public static void assertion(boolean expression, String... message) {
        if (!expression) {
            WatermelonException.throwEx(isNotNil(message) ? message[0] : "assertion is fail");
        }
    }

    /**
     * 验证一个对象是不是零值，如果是则取默认值。
     *
     * @param obj 对象
     * @param defaultT 默认值
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T isNilOrDefault(T obj, T defaultT) {
        return Nils.isNotNil(obj) ? obj : defaultT;
    }

    /**
     * 验证一个对象是不是null，如果是则取默认值。
     *
     * @param obj 对象
     * @param defaultT 默认值
     * @param <T> 泛型
     * @return 对象
     */
    public static <T> T isNullOrDefault(T obj, T defaultT) {
        return Nils.isNotNull(obj) ? obj : defaultT;
    }

    // ***** PRIVATE *****

    /**
     * 验证字符序列是否不是空。
     *
     * @param charSequence 字符序列
     * @return 是否不是空
     */
    private static boolean isNotBlank(CharSequence charSequence) {
        int strLen;
        if ((strLen = charSequence.length()) != 0) {
            for(int i = 0; i < strLen; ++i) {
                if (!Character.isWhitespace(charSequence.charAt(i))) {
                    return true;
                }
            }

        }
        return false;
    }

    // ***** CLASS *****

}
