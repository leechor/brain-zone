package org.licho.brain.api;

/**
 *
 */
public interface IRuntimeLog<TLogRecord extends IRuntimeLogRecord> extends ISimioCollection<TLogRecord> {
    ILogExpressions getRuntimeLogExpressions();
}
