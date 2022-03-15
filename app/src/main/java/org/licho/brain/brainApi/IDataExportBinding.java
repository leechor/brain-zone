package org.licho.brain.brainApi;

/**
 *
 */
public interface IDataExportBinding {
    String getName();

    IExportDataConnector getDataConnector();
}
