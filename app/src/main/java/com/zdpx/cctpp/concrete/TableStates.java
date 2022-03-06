package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.simioEnums.ElementScope;

/**
 *
 */
public class TableStates extends AbsIntelligentPropertyObject {

    public TableStates(TableStatesDefinition definition, String name, ElementScope scope) {
       super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new TableStatesElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    @Override
    protected AbsDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public int IconIndex() {
        return -1;
    }

}
