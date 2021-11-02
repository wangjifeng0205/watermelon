package net.wangjifeng.watermelon.javax;

import net.wangjifeng.watermelon.base.WatermelonException;
import net.wangjifeng.watermelon.util.Lists;
import net.wangjifeng.watermelon.util.Nils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author: wjf
 * @date: 2021/11/1 17:44
 *
 * 一个多任务的执行器，可以将一个大任务拆分成多个小任务执行，最终合并得到一个完整的结果。
 */
@SuppressWarnings("all")
public class ListForkJoinTask<T, R> {

    /**
     * 每个子任务所处理的最大元素个数。
     */
    private int size;

    /**
     * 所要处理的集合。
     */
    private List<T> list;

    /**
     * 超时时间。
     */
    private int timeout = 3;

    /**
     * 时间单位。
     */
    private TimeUnit timeUnit = TimeUnit.SECONDS;

    public ListForkJoinTask(int size, List<T> list, int timeout, TimeUnit timeUnit) {
        Nils.requireNonNil(size);
        Nils.requireNonNil(list);
        Nils.requireNonNil(timeout);
        Nils.requireNonNil(timeUnit);
        this.size = size;
        this.list = list;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
    }

    public List<R> execute(Go<T, R> go) {
        Objects.requireNonNull(go);
        ForkJoinPool forkJoinPool = null;
        try {
            forkJoinPool = new ForkJoinPool();
            ForkJoinTask<List<R>> joinTask = forkJoinPool.submit(new ListForkJoinSubTask<>(this.size, this.list, go));

            boolean flag;
            do {
                flag = forkJoinPool.awaitTermination(this.timeout, this.timeUnit);
            } while (!flag);

            forkJoinPool.shutdown();
            return joinTask.get();
        } catch (InterruptedException | ExecutionException e) {
            throw WatermelonException.newEx(e.getMessage());
        } finally {
            if (forkJoinPool != null && !forkJoinPool.isShutdown()) {
                forkJoinPool.shutdown();
            }
        }
    }

    private static class ListForkJoinSubTask<T, R> extends RecursiveTask<List<R>> {

        /**
         * 任务所处理的最大元素个数。
         */
        private final int size;

        /**
         * 当前任务所要处理的集合。
         */
        private final List<T> currentList;

        /**
         * 对每个子集合的处理，由开发者自行实现。
         */
        private final Go<T, R> go;

        public ListForkJoinSubTask(int size, List<T> currentList, Go<T, R> go) {
            Objects.requireNonNull(currentList);
            Objects.requireNonNull(go);
            this.size = size;
            this.currentList = currentList;
            this.go = go;
        }

        @Override
        protected List<R> compute() {
            if (Nils.isNil(this.currentList)) {
                return Collections.emptyList();
            }
            int size = this.currentList.size();
            if (size <= this.size) {
                return go.go(this.currentList);
            }

            int index = size / 2;
            ListForkJoinSubTask<T, R> leftTask = new ListForkJoinSubTask<>(this.size, this.currentList.subList(0, index), this.go);
            ListForkJoinSubTask<T, R> rightTask = new ListForkJoinSubTask<>(this.size, this.currentList.subList(index, size), this.go);
            leftTask.fork();
            rightTask.fork();
            List<R> leftList = leftTask.join();
            List<R> rightList = rightTask.join();
            List<R> result = Lists.newList();

            if (Nils.isNotNil(leftList)) {
                result.addAll(leftList);
            }
            if (Nils.isNotNil(rightList)) {
                result.addAll(rightList);
            }
            return result;
        }

    }

    /**
     * 用于list执行器的转换，需要开发者自行实现。
     *
     * @param <T>
     * @param <R>
     */
    @FunctionalInterface
    public interface Go<T, R> {

        /**
         * 处理每个子任务的逻辑实现。
         *
         * @param list 每个子任务所要处理的集合
         * @return 对应的结果集合。
         */
        public List<R> go(List<T> list);

    }

}
