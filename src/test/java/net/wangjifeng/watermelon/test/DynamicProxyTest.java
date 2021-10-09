package net.wangjifeng.watermelon.test;

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
        DynamicProxy<TestInterface> proxy = new DynamicProxy<>(user, u -> System.out.println("before:" + u), u -> System.out.println("after:" + u));
        System.out.println(proxy.getProxy().getUsername());
    }

}
