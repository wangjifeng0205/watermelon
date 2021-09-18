package net.wangjifeng.watermelon;

/**
 * @author: wjf
 * @date: 2021/9/18 14:41
 *
 * 消费者，watermelon核心接口，类似于{@link java.util.function.Consumer}。
 *
 * @param <T> 消费者消费的元素
 */
@FunctionalInterface
public interface Consumer<T> {

    /**
     * 消费元素{@link T}。
     *
     * @param t 消费者消费的元素
     */
    void consume(T t);

}
