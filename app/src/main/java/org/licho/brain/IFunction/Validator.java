package org.licho.brain.IFunction;

/**
 *
 */
@FunctionalInterface
public interface Validator<T> {
    boolean apply(T proposedValue, StringBuffer failureReason);
}
