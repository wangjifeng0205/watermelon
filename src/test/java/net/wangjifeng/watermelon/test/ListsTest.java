package net.wangjifeng.watermelon.test;

import net.wangjifeng.watermelon.util.Lambdas;
import net.wangjifeng.watermelon.util.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: wjf
 * @date: 2021/9/28 14:07
 */
public class ListsTest {

    @Test
    public void transformList() {
        List<Integer> source = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            source.add(i);
        }
        List<Integer> result = Lists.transformList(source, Lambdas.changeless());
        result.forEach(System.out::println);
    }

    @Test
    public void newList() {
        List<Integer> result = Lists.newList(1, 2, 3, 4, 5);
        result.forEach(System.out::println);
    }

}
