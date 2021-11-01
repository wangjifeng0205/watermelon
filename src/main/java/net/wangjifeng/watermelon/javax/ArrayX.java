package net.wangjifeng.watermelon.javax;

import net.wangjifeng.watermelon.base.Castor;
import net.wangjifeng.watermelon.base.SimpleCastor;
import net.wangjifeng.watermelon.base.WatermelonException;
import net.wangjifeng.watermelon.util.Nils;

import java.lang.reflect.Array;
import java.util.Iterator;

/**
 * @author: wjf
 * @date: 2021/9/26 10:52
 *
 * 数组的通用迭代器，Iterator<T>的泛型必须为一个数组。包括基本数据类型数组。
 */
public class ArrayX<E> implements Iterator<E> {

    /**
     * 任何类型的数组，包括基本数据类型数组
     */
    private final Object array;

    /**
     * 当前元素的index
     */
    private int currentIndex = 0;

    /**
     * 类型转换器
     */
    private final Castor<Object, E> castor = new SimpleCastor<>();

    /**
     * 私有的构造方法，需要传入一个数组。
     *
     * @param array {@link E}
     */
    private ArrayX(Object array) {
        Nils.requireNonNil(array);
        Nils.assertion(array.getClass().isArray(), "not an array");
        this.array = array;
    }

    @Override
    public boolean hasNext() {
        return this.currentIndex < Array.getLength(this.array);
    }

    @Override
    public E next() {
        return castor.cast(Array.get(this.array, this.currentIndex++));
    }

    @Override
    public void remove() {
        WatermelonException.throwEx("cannot remove items from an array");
    }

    /**
     * 创建一个Array的扩展。
     *
     * @param array 数组
     * @param <T> 数组元素的泛型
     * @return ArrayX<T>
     */
    public static <T> ArrayX<T> newArrayX(Object array) {
        return new ArrayX<>(array);
    }
}
