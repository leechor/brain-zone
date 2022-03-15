package org.licho.brain.api.extensions;

import org.licho.brain.api.IExecutionContext;

/**
 *
 */
public interface ISelectionRule {
    IExecutionContext Select(Iterable<IExecutionContext> candidates);

}
