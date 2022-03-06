package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IExportableLog {
    IDataExportBindings getDataExportBindings();

    IDataExportResult exportForInteractive();

    IDataExportResult exportForPlan();
}
