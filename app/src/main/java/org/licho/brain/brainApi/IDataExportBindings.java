package org.licho.brain.brainApi;

/**
 *
 */
public interface IDataExportBindings extends INamedSimioCollection<IDataExportBinding> {
    IDataExportBinding getActiveExportBinding();

    void setActiveExportBinding(IDataExportBinding dataExportBinding);
}
