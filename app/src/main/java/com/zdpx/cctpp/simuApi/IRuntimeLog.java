package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IRuntimeLog<TLogRecord extends IRuntimeLogRecord> extends ISimioCollection<TLogRecord> {
    ILogExpressions getRuntimeLogExpressions();
}
