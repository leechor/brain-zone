package org.licho.brain.api;

/**
 *
 */
public interface IDataExportBindings extends INamedSimioCollection<IDataExportBinding> {
    IDataExportBinding getActiveExportBinding();

    void setActiveExportBinding(IDataExportBinding dataExportBinding);
}
