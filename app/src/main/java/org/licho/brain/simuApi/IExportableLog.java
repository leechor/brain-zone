package org.licho.brain.simuApi;

/**
 *
 */
public interface IExportableLog {
    IDataExportBindings getDataExportBindings();

    IDataExportResult exportForInteractive();

    IDataExportResult exportForPlan();
}
