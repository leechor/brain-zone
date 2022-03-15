package org.licho.brain.IFunction;

import org.licho.brain.concrete.ReferenceValue;

/**
 *
 */
@FunctionalInterface
public interface GetReferenceValues {
    ReferenceValue[] apply();
}
