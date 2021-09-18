package net.wangjifeng.watermelon;

/**
 * @author: wjf
 * @date: 2021/9/18 14:35
 *
 * 生产者，watermelon核心接口，类似于{@link java.util.function.Supplier}。
 *
 * @param <T> 生产者生产的元素
 */
@FunctionalInterface
public interface Producer<T> {

    /**
     * 生产元素{@link T}。
     *
     * @return 生产者生产的元素
     */
    T produce();

}
