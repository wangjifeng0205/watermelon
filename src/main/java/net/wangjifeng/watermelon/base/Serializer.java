package net.wangjifeng.watermelon.base;

import net.wangjifeng.watermelon.Fun;

/**
 * @author: wjf
 * @date: 2021/9/23 11:04
 *
 * 序列化器。
 */
@FunctionalInterface
public interface Serializer<T> extends Fun<T, byte[]> {
}
