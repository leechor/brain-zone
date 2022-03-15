package org.licho.brain.simuApi;

/**
 *
 */
public interface IRuntimeLog<TLogRecord extends IRuntimeLogRecord> extends ISimioCollection<TLogRecord> {
    ILogExpressions getRuntimeLogExpressions();
}
