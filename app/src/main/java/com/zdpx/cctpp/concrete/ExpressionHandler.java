package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;

import java.util.function.BiFunction;

/**
 *
 */
@FunctionalInterface
public interface ExpressionHandler extends BiFunction<AbsBaseRunSpace, IRunSpace, ExpressionValue> {
}
