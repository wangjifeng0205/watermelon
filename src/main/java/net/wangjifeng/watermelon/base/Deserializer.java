package net.wangjifeng.watermelon.base;

import net.wangjifeng.watermelon.Fun;

/**
 * @author: wjf
 * @date: 2021/9/23 11:06
 *
 * 反序列化器。
 */
@FunctionalInterface
public interface Deserializer<T> extends Fun<byte[], T> {
}
