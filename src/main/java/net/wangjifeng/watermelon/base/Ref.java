package net.wangjifeng.watermelon.base;

import net.wangjifeng.watermelon.util.Beans;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @author: wjf
 * @date: 2021/9/28 15:13
 *
 * 类型token。
 *
 * @param <T> {@link T}
 */
public class Ref<T> {

    /**
     * 泛型的实际类型class
     */
    private final Class<T> clazz;

    /**
     * protected的构造方法，需要子类实现
     */
    protected Ref() {
        Type superclass = this.getClass().getGenericSuperclass();
        if (superclass instanceof ParameterizedType) {
            SimpleCastor<Type, ParameterizedType> generalizedCastor = new SimpleCastor<>();
            SimpleCastor<Type, Class<T>> actualGeneralizedCastor = new SimpleCastor<>();
            this.clazz = actualGeneralizedCastor.cast(generalizedCastor.cast(superclass).getActualTypeArguments()[0]);
        } else {
            throw WatermelonException.newEx("The generic type of Ref<T> is not generalized");
        }
    }

    /**
     * 获取Class<T>。
     *
     * @return Class<T>
     */
    public Class<T> getClazz() {
        return this.clazz;
    }

    /**
     * 创建一个T对象。
     *
     * @return {@link T}
     */
    public T newT() {
        return Beans.newBean(this.clazz);
    }

}
