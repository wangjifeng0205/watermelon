package net.wangjifeng.watermelon.javax;

import net.wangjifeng.watermelon.util.Lists;
import net.wangjifeng.watermelon.util.Nils;

import java.io.Serializable;
import java.util.List;
import java.util.function.Predicate;

/**
 * @author: wjf
 * @date: 2021/10/26 10:21
 *
 * 对{@link java.util.function.Predicate}的扩展。
 */
public class PredicateX {

    /**
     * &&
     *
     * @param components 断言
     * @param <T> {@link T}
     * @return AndPredicate
     */
    public static <T> Predicate<T> and(Predicate<? super T>... components) {
        return new AndPredicate<T>(Lists.newList(components));
    }

    /**
     * ||
     *
     * @param components 断言
     * @param <T> {@link T}
     * @return OrPredicate
     */
    public static <T> Predicate<T> or(Predicate<? super T>... components) {
        return new OrPredicate<T>(Lists.newList(components));
    }

    /**
     * !
     *
     * @param predicate 断言
     * @param <T> {@link T}
     * @return NotPredicate
     */
    public static <T> Predicate<T> not(Predicate<T> predicate) {
        return new NotPredicate<T>(predicate);
    }

    /**
     * &&
     *
     * @param <T>
     */
    private static class AndPredicate<T> implements Predicate<T>, Serializable {

        private final List<? extends Predicate<? super T>> components;

        private AndPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }

        @Override
        public boolean test(T t) {
            for (int i = 0; i < components.size(); i++) {
                if (!components.get(i).test(t)) {
                    return false;
                }
            }
            return true;
        }

        @Override
        public int hashCode() {
            return components.hashCode() + 0x12472c2c;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof AndPredicate) {
                AndPredicate<?> that = (AndPredicate<?>) obj;
                return components.equals(that.components);
            }
            return false;
        }

        @Override
        public String toString() {
            return "PredicateX.and(" + Lists.joining(this.components, ",") + ")";
        }
        private static final long serialVersionUID = 0;
    }

    /**
     * ||
     *
     * @param <T>
     */
    private static class OrPredicate<T> implements Predicate<T>, Serializable {

        private final List<? extends Predicate<? super T>> components;

        private OrPredicate(List<? extends Predicate<? super T>> components) {
            this.components = components;
        }

        @Override
        public boolean test(T t) {
            for (int i = 0; i < components.size(); i++) {
                if (components.get(i).test(t)) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public int hashCode() {
            return components.hashCode() + 0x053c91cf;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof OrPredicate) {
                OrPredicate<?> that = (OrPredicate<?>) obj;
                return components.equals(that.components);
            }
            return false;
        }

        @Override
        public String toString() {
            return "Predicates.or(" + Lists.joining(this.components, ",") + ")";
        }
        private static final long serialVersionUID = 0;
    }

    /**
     * !
     *
     * @param <T>
     */
    private static class NotPredicate<T> implements Predicate<T>, Serializable {

        final Predicate<T> predicate;

        NotPredicate(Predicate<T> predicate) {
            this.predicate = Nils.requireNonNil(predicate);
        }
        @Override
        public boolean test(T t) {
            return !predicate.test(t);
        }

        @Override
        public int hashCode() {
            return ~predicate.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NotPredicate) {
                NotPredicate<?> that = (NotPredicate<?>) obj;
                return predicate.equals(that.predicate);
            }
            return false;
        }

        @Override
        public String toString() {
            return "Predicates.not(" + predicate.toString() + ")";
        }
        private static final long serialVersionUID = 0;
    }

}
