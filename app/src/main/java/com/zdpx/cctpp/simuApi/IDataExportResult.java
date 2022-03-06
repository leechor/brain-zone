package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IDataExportResult {
    boolean isComplete();

    boolean isCancelled();

    String getMessage();
}
