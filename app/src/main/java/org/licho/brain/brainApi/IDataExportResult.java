package org.licho.brain.brainApi;

/**
 *
 */
public interface IDataExportResult {
    boolean isComplete();

    boolean isCancelled();

    String getMessage();
}
