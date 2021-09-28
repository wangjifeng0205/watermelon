package net.wangjifeng.watermelon.test;

import net.wangjifeng.watermelon.base.Ref;
import org.junit.Test;

/**
 * @author: wjf
 * @date: 2021/9/28 15:43
 */
public class RefTest {

    @Test
    public void ref() {
        User user = new Ref<User>() {}.newT();
        System.out.println(user);
    }

}
