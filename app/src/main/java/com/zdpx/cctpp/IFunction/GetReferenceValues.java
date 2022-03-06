package com.zdpx.cctpp.IFunction;

import com.zdpx.cctpp.concrete.ReferenceValue;

/**
 *
 */
@FunctionalInterface
public interface GetReferenceValues {
    ReferenceValue[] apply();
}
