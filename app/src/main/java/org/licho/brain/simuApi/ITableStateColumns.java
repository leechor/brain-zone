package org.licho.brain.simuApi;

/**
 *
 */
public interface ITableStateColumns extends INamedSimioCollection<ITableStateColumn>,
        IMutableSimioCollection<ITableStateColumn> {
    IRealTableStateColumn addRealColumn(String name);

    IBooleanTableStateColumn addBooleanColumn(String name);

    IDateTimeTableStateColumn addDateTimeColumn(String name);

    IStringTableStateColumn addStringColumn(String name);

    IListTableStateColumn addListColumn(String name);
}
