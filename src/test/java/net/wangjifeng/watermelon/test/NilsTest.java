package net.wangjifeng.watermelon.test;

import net.wangjifeng.watermelon.util.Nils;
import org.junit.Test;

/**
 * @author: wjf
 * @date: 2021/9/28 13:51
 */
public class NilsTest {

    @Test
    public void isNotNil() {
        String str = "";
        System.out.println(Nils.isNotNil(str));
    }

    @Test
    public void isAllNotNull() {
        String str = "";
        System.out.println(Nils.isAllNotNull(str));
        String[] strArr = {"1", "2", null};
        System.out.println(Nils.isAllNotNull(strArr));
    }

    @Test
    public void isNil() {
        String str = "";
        System.out.println(Nils.isNil(str));
        System.out.println(Nils.isNotNil(str));
    }

    @Test
    public void isNull() {
        String str = "";
        System.out.println(Nils.isNull(str));
        System.out.println(Nils.isNotNull(str));
    }

    @Test
    public void requireNonNil() {
        String str = "das    ";
        String nonNil = Nils.requireNonNil(str);
        System.out.println(nonNil);
        String str1 = "";
        String nonNil1 = Nils.requireNonNil(str1);
        System.out.println(nonNil1);
    }

    @Test
    public void assertion() {
        String str = "das    ";
        Nils.assertion(Nils.isNotNil(str));
    }

    @Test
    public void isNilOrDefault() {
        String str = "   ";
        String nilOrDefault = Nils.isNilOrDefault(str, "aaa");
        System.out.println(nilOrDefault);
    }

    @Test
    public void isNullOrDefault() {
        String str = "   ";
        String nilOrDefault = Nils.isNullOrDefault(str, "aaa");
        System.out.println(nilOrDefault);
        String str1 = null;
        String nilOrDefault1 = Nils.isNullOrDefault(str1, "bbb");
        System.out.println(nilOrDefault1);
    }

}
