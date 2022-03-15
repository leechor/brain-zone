package org.licho.brain.brainApi.extensions;

import org.licho.brain.brainApi.IExecutionContext;

/**
 *
 */
public interface ISelectionRule {
    IExecutionContext Select(Iterable<IExecutionContext> candidates);

}
