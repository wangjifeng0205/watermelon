package net.wangjifeng.watermelon.javax;

import net.wangjifeng.watermelon.util.Nils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: wjf
 * @date: 2021/11/2 17:02
 *
 * 指针，代表一个对象的地址。
 */
public class Pointer {

    /**
     * 当前对象。
     */
    private final Object object;

    /**
     * 对象所对应的地址hash。
     */
    private final int hash;

    /**
     * null-pointer。
     */
    private static final Pointer NULL_POINTER = new NullPointer();

    /**
     * 基本数据类型
     */
    private static final Map<String, Class<?>> PRIMITIVE = new HashMap<>();

    /**
     * 基本数据类型所对应的包装类型
     */
    private static final Map<String, Class<?>> WRAPPER = new HashMap<>();

    static {
        PRIMITIVE.put("byte", byte.class);
        PRIMITIVE.put("short", short.class);
        PRIMITIVE.put("int", int.class);
        PRIMITIVE.put("long", long.class);
        PRIMITIVE.put("float", float.class);
        PRIMITIVE.put("double", double.class);
        PRIMITIVE.put("char", char.class);
        PRIMITIVE.put("boolean", boolean.class);
        PRIMITIVE.put("void", void.class);

        WRAPPER.put("BYTE", Byte.class);
        WRAPPER.put("SHORT", Short.class);
        WRAPPER.put("INT", Integer.class);
        WRAPPER.put("LONG", Long.class);
        WRAPPER.put("FLOAT", Float.class);
        WRAPPER.put("DOUBLE", Double.class);
        WRAPPER.put("CHAR", Character.class);
        WRAPPER.put("BOOLEAN", Boolean.class);
        WRAPPER.put("VOID", Void.class);
    }

    /**
     * 根据key获取对应的基本类型。
     *
     * @param key key
     * @return 基本类型
     */
    public static Class<?> getPrimitive(String key) {
        return PRIMITIVE.get(key.toLowerCase());
    }

    /**
     * 根据key获取基本类型对应的包装类型。
     *
     * @param key key
     * @return 包装类型
     */
    public static Class<?> getWrapper(String key) {
        return WRAPPER.get(key.toUpperCase());
    }

    private Pointer(Object object) {
        this.object = object;
        this.hash = this.object.hashCode();
    }

    /**
     * 获取一个对象的指针。
     *
     * @param object 对象
     * @return 指针
     */
    public static Pointer pointer(Object object) {
        if (Nils.isNull(object)) {
            return NULL_POINTER;
        }
        return new Pointer(object);
    }

    /**
     * 根据指针获取对象。
     *
     * @return Object
     */
    public Object ref() {
        return this.object;
    }

    /**
     * 获取对象的hash。
     *
     * @return hash
     */
    public int hash() {
        return this.hash;
    }

    private static class NullPointer extends Pointer {

        private NullPointer() {
            super(null);
        }

        @Override
        public String toString() {
            return "NullPointer";
        }
    }

}
