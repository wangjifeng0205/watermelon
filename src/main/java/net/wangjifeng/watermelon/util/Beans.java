package net.wangjifeng.watermelon.util;

import net.wangjifeng.watermelon.base.WatermelonException;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author: wjf
 * @date: 2021/9/18 15:55
 *
 * 操作JavaBean对象的工具。
 */
public class Beans {

    private Beans() {}

    // ***** PUBLIC *****

    /**
     * 创建一个对象。
     *
     * @param clazz 对象字节码
     * @param <T> {@link T}
     * @return {@link T}
     */
    public static <T> T newBean(Class<T> clazz) {
        Nils.requireNonNil(clazz);
        Nils.assertion(hasNoArgsConstructor(clazz), String.format("%s does not have a public parameterless constructor", clazz.getTypeName()));
        try {
            Constructor<T> constructor = clazz.getConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            WatermelonException.throwEx(e);
        }
        throw WatermelonException.newEx(String.format("%s is not a JavaBean", clazz.getTypeName()));
    }

    /**
     * 获取某个对象的某个属性值。
     *
     * @param propertyName 属性名
     * @param bean bean
     * @param <T> {@link T}
     * @return Object
     */
    public static <T> Object getProperty(String propertyName, T bean) {
        Nils.requireNonNil(propertyName);
        Nils.requireNonNil(bean);
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, bean.getClass());
            return descriptor.getReadMethod().invoke(bean);
        } catch (Exception e) {
            throw WatermelonException.newEx(e.getMessage());
        }
    }

    /**
     * 设置某个对象的某个属性值。
     *
     * @param propertyName 属性名
     * @param bean bean
     * @param <T> {@link T}
     * @return Object
     */
    public static <T> void setProperty(String propertyName, T bean, Object property) {
        if (Nils.isNil(property)) {
            return;
        }
        Nils.requireNonNil(propertyName);
        Nils.requireNonNil(bean);
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(propertyName, bean.getClass());
            descriptor.getWriteMethod().invoke(bean, property);
        } catch (Exception e) {
            throw WatermelonException.newEx(e.getMessage());
        }
    }

    /**
     * 是否有无参构造方法。
     *
     * @param clazz BeanClass
     * @return 是否有无参构造方法
     */
    public static boolean hasNoArgsConstructor(Class<?> clazz) {
        try {
            Constructor<?> constructor = clazz.getConstructor();
            if (Nils.isNil(constructor)) {
                return false;
            }
            if (!constructor.isAccessible()) {
                return false;
            }
        } catch (NoSuchMethodException e) {
            return false;
        }
        return true;
    }

    // ***** PRIVATE *****

    // ***** CLASS *****

}
