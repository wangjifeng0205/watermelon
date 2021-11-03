package net.wangjifeng.watermelon.base;

/**
 * @author: wjf
 * @date: 2021/11/3 15:30
 *
 * 一个秒表计时器。
 */
public class StopWatch {

    /**
     * 毫秒值。
     */
    private long secondMillis;

    public StopWatch() {}

    /**
     * 开始
     */
    public void start() {
        this.secondMillis = System.currentTimeMillis();
    }

    /**
     * 停止
     *
     * @return 开始时间和结束时间的差值
     */
    public long stop() {
        return System.currentTimeMillis() - this.secondMillis;
    }

    /**
     * 重置。
     */
    public void reset() {
        this.secondMillis = 0;
    }

}
