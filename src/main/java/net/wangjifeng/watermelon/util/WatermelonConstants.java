package net.wangjifeng.watermelon.util;

/**
 * @author: wjf
 * @date: 2021/10/26 10:58
 *
 * 常量接口，一些常用的数据类型的常量类。
 */
public interface WatermelonConstants<T> {

    public static final String STR_CONSTANT = "";

    public static final int INT_CONSTANT = 1;

    public static final long LONG_CONSTANT = 1L;

    /**
     * 获取某个类型对应的常量。
     *
     * @return 常量
     */
    T constant();

    public enum WatermelonConstant implements WatermelonConstants<Object> {
        Str() {
            @Override
            public String constant() {
                return STR_CONSTANT;
            }
        },
        Int() {
            @Override
            public Integer constant() {
                return INT_CONSTANT;
            }
        },
        Long() {
            @Override
            public Long constant() {
                return LONG_CONSTANT;
            }
        }
        ;
        WatermelonConstant() {}
    }

}
