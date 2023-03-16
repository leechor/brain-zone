package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.brainEnums.ElementScope;

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
    protected AbstractGridObjectDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public int IconIndex() {
        return -1;
    }

}
