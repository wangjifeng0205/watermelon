package net.wangjifeng.watermelon.util;

import net.wangjifeng.watermelon.Consumer;
import net.wangjifeng.watermelon.Fun;
import net.wangjifeng.watermelon.Producer;

/**
 * @author: wjf
 * @date: 2021/9/18 16:53
 *
 * 操作函数和lambda表达式的工具。
 */
public class Lambdas {

    private Lambdas() {}

    // ***** PUBLIC *****

    /**
     * 提供者提供元素，fun将其转化为另一种元素。
     *
     * @param producer 提供者
     * @param fun fun
     * @param <T> {@link T}
     * @param <R> {@link R}
     * @return {@link R}
     */
    public static <T, R> R producerFun(Producer<T> producer, Fun<T, R> fun) {
        Nils.requireNonNil(producer);
        Nils.requireNonNil(fun);
        return fun.fun(producer.produce());
    }

    /**
     * 提供者提供元素，fun将其转化为另一种元素。
     *
     * @param producer 提供者
     * @param fun fun
     * @param consumer 消费者
     * @param <T> {@link T}
     * @param <R> {@link R}
     * @return {@link R}
     */
    public static <T, R> R fun(Producer<T> producer, Fun<T, R> fun, Consumer<R> consumer) {
        Nils.requireNonNil(producer);
        Nils.requireNonNil(fun);
        Nils.requireNonNil(consumer);
        R r = fun.fun(producer.produce());
        consumer.consume(r);
        return r;
    }

    /**
     * 不变原样的转换。
     *
     * @param <R> {@link R}
     * @return Fun<R, R>
     */
    public static <R> Fun<R, R> changeless() {
        return r -> r;
    }

    // ***** PRIVATE *****

    // ***** CLASS *****

}
