package net.wangjifeng.watermelon;

/**
 * @author: wjf
 * @date: 2021/9/18 14:33
 *
 * 全局函数，watermelon核心接口，类似于{@link java.util.function.Function}。
 *
 * @param <INPUT> 输入
 * @param <OUTPUT> 输出
 */
@FunctionalInterface
public interface Fun<INPUT, OUTPUT> {

    /**
     * 通过输入获得输出。
     *
     * @param input {@link INPUT}
     * @return {@link OUTPUT}
     */
    OUTPUT fun(INPUT input);

}
