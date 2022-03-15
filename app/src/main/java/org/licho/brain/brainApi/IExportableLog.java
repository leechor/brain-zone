package org.licho.brain.brainApi;

/**
 *
 */
public interface IExportableLog {
    IDataExportBindings getDataExportBindings();

    IDataExportResult exportForInteractive();

    IDataExportResult exportForPlan();
}
