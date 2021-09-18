package net.wangjifeng.watermelon.util;

import net.wangjifeng.watermelon.Fun;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: wjf
 * @date: 2021/9/18 16:43
 *
 * 操作{@link java.util.List}的工具。
 */
public class Lists {

    private Lists() {}

    /**
     * 将源list转换为结果list。
     *
     * @param source 源list
     * @param fun S -> R
     * @param <S> 泛型Source
     * @param <R> 泛型Result
     * @return 结果list
     */
    public static <S, R> List<R> transformList(List<S> source, Fun<S, R> fun) {
        if (Nils.isNil(source)) {
            return Collections.emptyList();
        }
        Nils.requireNonNil(fun);
        return source.stream().map(fun::fun).collect(Collectors.toList());
    }

    /**
     * 创建list。
     *
     * @param ts args
     * @param <T> {@link T}
     * @return List<T>
     */
    public static <T> List<T> newList(T... ts) {
        if (Nils.isNil(ts)) {
            return Collections.emptyList();
        }
        return Arrays.stream(ts).collect(Collectors.toList());
    }

}
