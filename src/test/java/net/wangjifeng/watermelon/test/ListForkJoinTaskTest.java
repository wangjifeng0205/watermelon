package net.wangjifeng.watermelon.test;

import net.wangjifeng.watermelon.javax.ListForkJoinTask;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author: wjf
 * @date: 2021/11/3 10:42
 */
public class ListForkJoinTaskTest {

    @Test
    public void test() {
        long start = System.currentTimeMillis();
        List<User> source = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUsername("wjf");
            user.setAge(i);
            source.add(user);
        }

        ListForkJoinTask<User, User> forkJoinTask = new ListForkJoinTask<>(1000, source, 10, TimeUnit.SECONDS);
        List<User> users = forkJoinTask.execute(list -> {
            for (User user : list) {
                user.setSex(user.getAge() % 2 == 0 ? "女" : "男");
            }
            return list;
        });
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public void test01() {
        long start = System.currentTimeMillis();
        List<User> source = new ArrayList<>();

        for (int i = 0; i < 10000; i++) {
            User user = new User();
            user.setUsername("wjf");
            user.setAge(i);
            source.add(user);
        }

        for (User user : source) {
            user.setSex(user.getAge() % 2 == 0 ? "女" : "男");
        }
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000);
        for (User user : source) {
            System.out.println(user);
        }
    }

}
