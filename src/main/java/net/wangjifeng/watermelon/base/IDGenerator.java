package net.wangjifeng.watermelon.base;

import net.wangjifeng.watermelon.Producer;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author: wjf
 * @date: 2021/9/23 10:48
 *
 * 一个简单的IdGenerator，从1开始，每次调用增加1。
 */
public class IDGenerator implements Producer<String> {

    /**
     * 主信号量
     */
    private final AtomicLong mostSigBits = new AtomicLong(0);

    /**
     * 子信号量
     */
    private final AtomicLong leastSigBits = new AtomicLong(0);

    @Override
    public String produce() {
        UUID uuid = generateUUID();
        return uuid.toString().replace("-", "");
    }

    /**
     * 生成字符串id。
     *
     * @return id
     */
    public String generateId() {
        return produce();
    }

    /**
     * 生成uuid。
     *
     * @return uuid
     */
    private UUID generateUUID() {
        long leastSigBits = this.leastSigBits.incrementAndGet();
        if (leastSigBits == 0) {
            this.mostSigBits.incrementAndGet();
        }
        return new UUID(this.mostSigBits.get(), leastSigBits);
    }

}
