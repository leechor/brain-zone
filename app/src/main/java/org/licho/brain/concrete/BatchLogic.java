package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.brainEnums.ElementScope;

/**
 *
 */
public class BatchLogic extends AbsIntelligentPropertyObject{
    protected BatchLogic(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return null;
    }

    @Override
    protected AbstractGridObjectDefinition DefaultDefinition() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 1;
    }
}
