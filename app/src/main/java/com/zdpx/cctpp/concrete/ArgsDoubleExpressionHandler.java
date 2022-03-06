package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;

/**
 *
 */
@FunctionalInterface
public interface ArgsDoubleExpressionHandler {
    Double apply(AbsBaseRunSpace elementRunSpace, IRunSpace tokenRunspace, ExpressionValue[] args);
}
