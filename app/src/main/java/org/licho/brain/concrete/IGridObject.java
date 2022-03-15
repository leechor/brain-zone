package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public interface IGridObject {
    String GetGridObjectClassName();

    // Token: 0x06000A11 RID: 2577
    String GetGridObjectDescription();

    // Token: 0x06000A12 RID: 2578
    String GetGridObjectInstanceName();

    // Token: 0x06000A13 RID: 2579
    GridItemProperties GetGridPropertyItemList(GridItemProperties param0, GridObjectDefinition param1);

    // Token: 0x06000A14 RID: 2580
    IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1);

    // Token: 0x06000A15 RID: 2581
    String[] DisplayedValuesNeeded(int param0);
}

