package net.wangjifeng.watermelon.base;

/**
 * @author: wjf
 * @date: 2021/9/18 14:45
 *
 * 转换器，watermelon核心接口。
 *
 * @param <T> {@link T}
 * @param <R> {@link R}
 */
@FunctionalInterface
public interface Castor<T, R> {

    /**
     * 将{@link T}转化为{@link R}。
     *
     * @param t {@link T}
     * @return {@link R}
     */
    R cast(T t);

    /**
     * 默认的转换器。
     *
     * @return {@link Castor}
     */
    @SuppressWarnings("unchecked")
    default Castor<T, R> defaultCast() {
        return t -> (R) t;
    }

}
