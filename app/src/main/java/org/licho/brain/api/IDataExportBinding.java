package org.licho.brain.api;

/**
 *
 */
public interface IDataExportBinding {
    String getName();

    IExportDataConnector getDataConnector();
}
