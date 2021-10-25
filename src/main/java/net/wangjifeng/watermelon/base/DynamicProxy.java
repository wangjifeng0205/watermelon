package net.wangjifeng.watermelon.base;

import net.wangjifeng.watermelon.Consumer;
import net.wangjifeng.watermelon.util.Lambdas;
import net.wangjifeng.watermelon.util.Nils;

import java.lang.reflect.Proxy;

/**
 * @author: wjf
 * @date: 2021/10/9 9:43
 *
 * JDK动态代理增强。提供前置增强和后置增强。
 *
 * @param <I> 被代理的对象所实现的一个接口。
 */
public class DynamicProxy<I> {

    /**
     * 被代理的对象
     */
    private I target;

    /**
     * 被代理的对象的字节码
     */
    private Class<I> targetClass;

    /**
     * 代理对象
     */
    private I proxy;

    /**
     * 对象转换器
     */
    private final Castor<Object, I> objCastor = SimpleCastor.getSimpleCastor();

    /**
     * 字节码转换器
     */
    private final Castor<Class<?>, Class<I>> classCastor = SimpleCastor.getSimpleCastor();

    /**
     * 前置
     */
    private final Consumer<I> before;

    /**
     * 后置
     */
    private final Consumer<I> after;

    /**
     * 构造函数，需要提供被代理的对象，前置和后置都可以为空。
     *
     * @param target 被代理的对象
     * @param before 前置
     * @param after 后置
     */
    public DynamicProxy(I target, Consumer<I> before, Consumer<I> after) {
        this.target = Nils.requireNonNil(target);
        this.targetClass = classCastor.cast(this.target.getClass());
        this.before = before;
        this.after = after;
        this.proxy = newProxy(before, after);
    }

    /**
     * 获取代理对象。
     *
     * @return I
     */
    public I getProxy() {
        return this.proxy;
    }

    /**
     * 重新设置被代理的对象。
     *
     * @param target 被代理的对象
     * @return 代理对象
     */
    public I reset(I target) {
        this.target = target;
        this.targetClass = classCastor.cast(this.target.getClass());
        this.proxy = newProxy(this.before, this.after);
        return this.proxy;
    }

    /**
     * 生成代理对象。
     *
     * @param before 前置
     * @param after 后置
     * @return I
     */
    @SuppressWarnings("all")
    private I newProxy(Consumer<I> before, Consumer<I> after) {
        return objCastor.cast(
                Proxy.newProxyInstance(
                        DynamicProxy.class.getClassLoader(),
                        this.targetClass.getInterfaces(),
                        (proxy, method, args) -> {
                            Lambdas.notNullExec(before, () -> before.consume(DynamicProxy.this.target));
                            Object result = method.invoke(DynamicProxy.this.target, args);
                            Lambdas.notNullExec(after, () -> after.consume(DynamicProxy.this.target));
                            return result;
                        }
                )
        );
    }

}
