package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public interface IGridObject {
    String GetGridObjectClassName();

    String GetGridObjectDescription();

    String GetGridObjectInstanceName();

    GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                               GridObjectDefinition gridObjectDefinition);

    IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1);

    String[] DisplayedValuesNeeded(int param0);
}

