package org.licho.brain.simuApi;

/**
 *
 */
public interface IDataExportBindings extends INamedSimioCollection<IDataExportBinding> {
    IDataExportBinding getActiveExportBinding();

    void setActiveExportBinding(IDataExportBinding dataExportBinding);
}
