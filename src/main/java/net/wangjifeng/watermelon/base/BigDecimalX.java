package net.wangjifeng.watermelon.base;

import java.math.BigDecimal;

/**
 * @author: wjf
 * @date: 2021/9/18 17:08
 *
 * 作为{@link java.math.BigDecimal}的扩展。
 */
public class BigDecimalX extends BigDecimal {

    /**
     * 简单的复刻了父类的构造方法。
     *
     * @param val 值
     */
    public BigDecimalX(String val) {
        super(val);
    }

    /**
     * 扩展了静态的valueOf方法。
     *
     * @param value 值
     * @return BigDecimalX
     */
    public static BigDecimalX valueOf(String value) {
        return new BigDecimalX(value);
    }

}
