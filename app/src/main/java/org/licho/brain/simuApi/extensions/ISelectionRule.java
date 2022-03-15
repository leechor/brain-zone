package org.licho.brain.simuApi.extensions;

import org.licho.brain.simuApi.IExecutionContext;

/**
 *
 */
public interface ISelectionRule {
    IExecutionContext Select(Iterable<IExecutionContext> candidates);

}
