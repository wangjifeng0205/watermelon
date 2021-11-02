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
public abstract class DynamicProxy<I> {

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
     * 构造函数，需要提供被代理的对象，前置和后置都可以为空。
     *
     * @param target 被代理的对象
     */
    public DynamicProxy(I target) {
        this.target = Nils.requireNonNil(target);
        this.targetClass = classCastor.cast(this.target.getClass());
        this.proxy = newProxy();
    }

    /**
     * 前置通知。
     *
     * @param <T> {@link T}
     * @return {@link T}
     */
    public abstract <T> Consumer<T> before();

    /**
     * 后置通知。
     *
     * @param <T> {@link T}
     * @return {@link T}
     */
    public abstract <T> Consumer<T> after();

    /**
     * 异常通知。
     *
     * @return Throwable
     */
    public abstract Consumer<Throwable> throwing();

    /**
     * 最终通知。
     *
     * @param <T> {@link T}
     * @return {@link T}
     */
    public abstract <T> Consumer<T> ultimate();

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
        this.proxy = newProxy();
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
    private I newProxy() {
        return objCastor.cast(
                Proxy.newProxyInstance(
                        DynamicProxy.class.getClassLoader(),
                        this.targetClass.getInterfaces(),
                        (proxy, method, args) -> {
                            Object result = null;
                            try {
                                Consumer<I> before = before();
                                Lambdas.notNullExec(before, () -> before.consume(DynamicProxy.this.target));
                                result = method.invoke(DynamicProxy.this.target, args);
                                Consumer<I> after = after();
                                Lambdas.notNullExec(after, () -> after.consume(DynamicProxy.this.target));
                            } catch (Exception e) {
                                Consumer<Throwable> throwing = throwing();
                                Lambdas.notNullExec(throwing, () -> throwing.consume(e));
                            } finally {
                                Consumer<Object> ultimate = ultimate();
                                final Object duplicate = result;
                                Lambdas.notNullExec(ultimate, () -> ultimate.consume(duplicate));
                            }
                            return result;
                        }
                )
        );
    }

}
