package org.licho.brain.api;

/**
 *
 */
public interface IDataExportResult {
    boolean isComplete();

    boolean isCancelled();

    String getMessage();
}
