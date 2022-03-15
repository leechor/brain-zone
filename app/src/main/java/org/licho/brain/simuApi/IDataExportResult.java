package org.licho.brain.simuApi;

/**
 *
 */
public interface IDataExportResult {
    boolean isComplete();

    boolean isCancelled();

    String getMessage();
}
