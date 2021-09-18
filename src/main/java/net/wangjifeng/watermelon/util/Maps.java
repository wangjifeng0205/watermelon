package net.wangjifeng.watermelon.util;

import net.wangjifeng.watermelon.Fun;
import net.wangjifeng.watermelon.base.Castor;
import net.wangjifeng.watermelon.base.SimpleCastor;

import java.util.*;

/**
 * @author: wjf
 * @date: 2021/9/18 14:54
 * <p>
 * 操作{@link java.util.Map}的工具。
 */
public class Maps {

    private Maps() {
    }

    // ***** PUBLIC *****

    /**
     * 将源map转换为结果map。
     *
     * @param sourceMap 源map
     * @param keyFun    SK -> RK
     * @param valueFun  SV -> RV
     * @param <SK>      源key泛型
     * @param <SV>      源value泛型
     * @param <RK>      结果key泛型
     * @param <RV>      结果value泛型
     * @return 结果map
     */
    public static <SK, SV, RK, RV> Map<RK, RV> transformMap(Map<SK, SV> sourceMap, Fun<SK, RK> keyFun, Fun<SV, RV> valueFun) {
        if (Nils.isNil(sourceMap)) {
            return Collections.emptyMap();
        }
        Nils.requireNonNil(keyFun);
        Nils.requireNonNil(valueFun);

        Map<RK, RV> resultMap = new HashMap<>(sourceMap.size());
        sourceMap.forEach((sk, sv) -> resultMap.put(keyFun.fun(sk), valueFun.fun(sv)));
        return resultMap;
    }

    /**
     * 创建map。
     *
     * @param pairs 键值对
     * @param <K>   key泛型
     * @param <V>   value泛型
     * @return map
     */
    public static <K, V> Map<K, V> newMap(Object... pairs) {
        if (Nils.isNil(pairs)) {
            return new HashMap<>(0);
        }
        HashMap<K, V> result = new HashMap<>(pairs.length / 2);
        List<Pair<Object, Object>> kvPairs = assemblyPairs(pairs);
        Castor<Object, K> keyCastor = new SimpleCastor<>();
        Castor<Object, V> valueCastor = new SimpleCastor<>();
        kvPairs.forEach(pair -> result.put(keyCastor.cast(pair.getKey()), valueCastor.cast(pair.getValue())));
        return result;
    }

    /**
     * map转化为对象。
     *
     * @param properties 属性
     * @param clazz      字节码对象
     * @param <T>        {@link T}
     * @return {@link T}
     */
    public static <T> T mapToObj(Map<String, Object> properties, Class<T> clazz) {
        T bean = Beans.newBean(clazz);
        if (Nils.isNil(properties)) {
            return bean;
        }
        properties.forEach((k, v) -> Beans.setProperty(k, bean, v));
        return bean;
    }

    // ***** PRIVATE *****

    /**
     * 组装map。
     *
     * @param pairs 键值对
     * @param <K>   key泛型
     * @param <V>   value泛型
     * @return List<Lambdas.KVPair < K, V>>
     */
    private static <K, V> List<Pair<K, V>> assemblyPairs(Object... pairs) {
        Nils.assertion(pairs.length % 2 == 0, "the number of pairs.length is not even");
        List<Pair<K, V>> result = new ArrayList<>(pairs.length / 2);

        for (int i = 0; i < pairs.length; i++) {
            if (i % 2 == 0) {
                Pair<K, V> pair = new Pair<>();
                pair.setKey(pair.castKey(pairs[i]));
                result.add(pair);
            } else {
                Pair<K, V> pair = result.get(result.size() - 1);
                pair.setValue(pair.castValue(pairs[i]));
            }
        }
        return result;
    }

    // ***** CLASS *****

    /**
     * 内置的{@link Map}键值对。
     */
    static class Pair<K, V> {

        private K key;

        private V value;

        private final Castor<Object, K> keyCastor = new SimpleCastor<>();

        private final Castor<Object, V> valueCastor = new SimpleCastor<>();

        public Pair() {
        }

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public K castKey(Object obj) {
            return keyCastor.cast(obj);
        }

        public V castValue(Object obj) {
            return valueCastor.cast(obj);
        }
    }

}
