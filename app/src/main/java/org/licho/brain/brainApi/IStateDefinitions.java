package org.licho.brain.brainApi;

import org.licho.brain.brainApi.enu.SimioUnitType;

/**
 *
 */
public interface IStateDefinitions extends INamedSimioCollection<IStateDefinition> {

    IStateDefinition addState(String name);

    IStateDefinition addRealState(String name);

    IStateDefinition addRealState(String name, SimioUnitType unitType);

    IStateDefinition addStringState(String name);

    IStateDefinition addDateTimeState(String name);

    IStateDefinition addListState(String name);

}
