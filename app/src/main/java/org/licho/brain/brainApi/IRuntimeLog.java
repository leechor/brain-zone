package org.licho.brain.brainApi;

/**
 *
 */
public interface IRuntimeLog<TLogRecord extends IRuntimeLogRecord> extends ISimioCollection<TLogRecord> {
    ILogExpressions getRuntimeLogExpressions();
}
