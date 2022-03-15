package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;

import java.util.function.BiFunction;

/**
 *
 */
@FunctionalInterface
public interface ExpressionHandler extends BiFunction<AbsBaseRunSpace, IRunSpace, ExpressionValue> {
}
