package org.licho.brain.api;

/**
 *
 */
public interface IExportableLog {
    IDataExportBindings getDataExportBindings();

    IDataExportResult exportForInteractive();

    IDataExportResult exportForPlan();
}
