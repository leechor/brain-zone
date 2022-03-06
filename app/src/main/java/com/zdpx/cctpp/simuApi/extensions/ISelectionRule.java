package com.zdpx.cctpp.simuApi.extensions;

import com.zdpx.cctpp.simuApi.IExecutionContext;

/**
 *
 */
public interface ISelectionRule {
    IExecutionContext Select(Iterable<IExecutionContext> candidates);

}
