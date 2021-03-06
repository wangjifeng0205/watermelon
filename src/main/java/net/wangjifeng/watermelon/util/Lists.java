package net.wangjifeng.watermelon.util;

import net.wangjifeng.watermelon.Fun;
import net.wangjifeng.watermelon.base.SimpleCastor;

import java.util.ArrayList;
import java.util.Arrays;
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

    // ***** PUBLIC *****

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
            return newArrayList();
        }
        Nils.requireNonNil(fun);
        return source.stream().map(fun::fun).collect(Collectors.toList());
    }

    /**
     * 在每个元素之间插入一个连接符。泛型会调用对应的toString方法。
     *
     * @param list list
     * @param join 连接符
     * @return 连接之后的字符串
     */
    public static String joining(List<?> list, String join) {
        if (Nils.isNil(list)) {
            return SimpleCastor.<Object, String>getSimpleCastor().cast(WatermelonConstants.WatermelonConstant.Str.constant());
        }
        return list.stream().map(Object::toString).collect(Collectors.joining(join));
    }

    /**
     * 创建list。
     *
     * @param ts args
     * @param <T> {@link T}
     * @return List<T>
     */
    @SafeVarargs
    public static <T> List<T> newList(T... ts) {
        if (Nils.isNil(ts)) {
            return newArrayList();
        }
        return Arrays.stream(ts).collect(Collectors.toList());
    }

    /**
     * 创建一个初始化容量的list。
     *
     * @param cap 容量
     * @param <T> {@link T}
     * @return List<T>
     */
    public static <T> List<T> newList(int cap) {
        return newArrayList(cap);
    }

    // ***** PRIVATE *****

    /**
     * 创建arrayList。
     *
     * @param cap 容量
     * @param <E> {@link E}
     * @return ArrayList<E>
     */
    private static <E> ArrayList<E> newArrayList(int... cap) {
        if (Nils.isNil(cap)) {
            return new ArrayList<>();
        }
        return new ArrayList<>(cap[0]);
    }

    // ***** CLASS *****

}
