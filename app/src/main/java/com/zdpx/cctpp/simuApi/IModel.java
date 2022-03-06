package com.zdpx.cctpp.simuApi;

import java.util.function.Consumer;

/**
 *
 */
public interface IModel {

    String getName();

    void setName(String name);

    IProperties Properties();

    IStateDefinition StateDefinition();

    IEventDefinition EventDefinition();

    IFacility getFacility();

    IElementObjects getElements();

    INamedList NamedLists();

    ITables Tables();

    IFunctionTables FunctionTables();

    IRateTables RatedTables();

//    IChangeoverMatrices getChangeoverMatrices();

    IDayPatterns DayPatterns();

    IWorkSchedules WorkSchedules();

    IExternalNodes ExternalNodes();

    IExperiments Experiments();

    IErrors Errors();

    IRunSetup RunSetup();

    IPlan Plan();

    IImportDataConnectors ImportDataConnectors();

    IExportDataConnectors ExportDataConnectors();

    void BulkUpdate(Consumer<IModel> action);

}
