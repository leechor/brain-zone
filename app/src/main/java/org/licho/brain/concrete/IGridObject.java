package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public interface IGridObject {
    /**
     * Gets the name of the object.
     *
     * @return The name of the object.
     */
    String getObjectClassName();

    /**
     * Gets the description of the object.
     * @return
     */
    String getObjectDescription();

    String GetGridObjectInstanceName();

    GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                           GridObjectDefinition gridObjectDefinition);

    IntelligentObjectProperty UpdatePropertyChange(int index, Object value);

    String[] DisplayedValuesNeeded(int index);
}

