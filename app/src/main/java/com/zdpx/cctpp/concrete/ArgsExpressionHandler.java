package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;

/**
 *
 */
@FunctionalInterface
public interface ArgsExpressionHandler {
    ExpressionValue apply(AbsBaseRunSpace elementRunSpace, IRunSpace tokenRunspace, ExpressionValue[] args);
}
