package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;

/**
 *
 */
@FunctionalInterface
public interface ArgsExpressionHandler {
    ExpressionValue apply(AbsBaseRunSpace elementRunSpace, IRunSpace tokenRunspace, ExpressionValue[] args);
}
