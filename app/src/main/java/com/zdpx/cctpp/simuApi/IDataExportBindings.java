package com.zdpx.cctpp.simuApi;

/**
 *
 */
public interface IDataExportBindings extends INamedSimioCollection<IDataExportBinding> {
    IDataExportBinding getActiveExportBinding();

    void setActiveExportBinding(IDataExportBinding dataExportBinding);
}
