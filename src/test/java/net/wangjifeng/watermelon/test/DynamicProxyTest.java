package net.wangjifeng.watermelon.test;

import net.wangjifeng.watermelon.Consumer;
import net.wangjifeng.watermelon.base.DynamicProxy;
import org.junit.Test;

/**
 * @author: wjf
 * @date: 2021/10/9 10:47
 */
public class DynamicProxyTest {

    @Test
    public void test() {
        User user = new User();
        user.setUsername("wjf");
        DynamicProxy<TestInterface> proxy = new DynamicProxy<TestInterface>(user) {
            @Override
            public <T> Consumer<T> before() {
                return null;
            }

            @Override
            public <T> Consumer<T> after() {
                return null;
            }

            @Override
            public Consumer<Throwable> throwing() {
                return null;
            }

            @Override
            public <T> Consumer<T> ultimate() {
                return null;
            }
        };
        System.out.println(proxy.getProxy().getUsername());
    }

}
