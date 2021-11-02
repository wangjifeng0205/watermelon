package net.wangjifeng.watermelon.test;

import net.wangjifeng.watermelon.javax.ArrayX;
import org.junit.Test;

/**
 * @author: wjf
 * @date: 2021/11/2 16:58
 */
public class ArrayXTest {

    @Test
    public void newArrayTest() {
        Integer[] array = ArrayX.newArray(int.class, 5);
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        for (Integer integer : array) {
            System.out.println(integer);
        }
    }

}
