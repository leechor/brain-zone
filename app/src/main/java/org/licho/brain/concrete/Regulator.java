package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.simioEnums.ElementScope;

/**
 *
 */
public class Regulator extends AbsIntelligentPropertyObject {

    protected Regulator(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return null;
    }

    @Override
    protected AbsDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }
}
