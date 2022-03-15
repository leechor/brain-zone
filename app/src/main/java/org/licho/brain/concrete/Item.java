package org.licho.brain.concrete;

/**
 * @author licho
 * @since 2021/10/13
 */
public interface Item<T> {
    default long numberItems() {
        throw new IllegalStateException("numberItems");
    }

    default T firstItem() {
        throw new IllegalStateException("numberItems");
    }

    default T lastItem() {
        throw new IllegalStateException("numberItems");
    }

    default T itemAtIndex(long index) {
        throw new IllegalStateException("numberItems");
    }

    default long indexOfItem(T item) {
        throw new IllegalStateException("numberItems");
    }

    default boolean contains(T item) {
        throw new IllegalStateException("numberItems");
    }
}
