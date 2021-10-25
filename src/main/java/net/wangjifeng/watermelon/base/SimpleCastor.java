package net.wangjifeng.watermelon.base;

/**
 * @author: wjf
 * @date: 2021/9/18 15:10
 *
 * 简单的类型转换器。
 *
 * @param <T> {@link T}
 * @param <R> {@link R}
 */
public class SimpleCastor<T, R> implements Castor<T, R> {

    @Override
    public R cast(T t) {
        return defaultCast().cast(t);
    }

    /**
     * 获取简单的类型转换器。
     *
     * @param <T> {@link T}
     * @param <R> {@link R}
     * @return Castor<T, R>
     */
    public static <T, R> Castor<T, R> getSimpleCastor() {
        return new SimpleCastor<>();
    }

}
