package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;

/**
 *
 */
@FunctionalInterface
public interface ArgsDoubleExpressionHandler {
    Double apply(AbsBaseRunSpace elementRunSpace, IRunSpace tokenRunspace, ExpressionValue[] args);
}
