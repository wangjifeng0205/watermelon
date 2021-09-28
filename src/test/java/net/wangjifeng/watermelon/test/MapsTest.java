package net.wangjifeng.watermelon.test;

import net.wangjifeng.watermelon.util.Lambdas;
import net.wangjifeng.watermelon.util.Maps;
import org.junit.Test;

import java.util.Map;

/**
 * @author: wjf
 * @date: 2021/9/28 14:55
 */
public class MapsTest {

    @Test
    public void transformMap() {
        Map<String, Integer> map = Maps.newMap("1", 1, "2", 2, "3", 3);
        Map<Integer, Integer> integerMap = Maps.transformMap(map, Integer::valueOf, Lambdas.changeless());
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
        integerMap.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

    @Test
    public void newMap() {
        Map<String, Integer> map = Maps.newMap("1", 1, "2", 2, "3", 3);
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(v);
        });
    }

    @Test
    public void mapToObj() {
        Map<String, Object> userMap = Maps.newMap("username", "wjf", "age", 18, "sex", "男");
        User user = Maps.mapToObj(userMap, User.class);
        System.out.println(user);
    }

}
