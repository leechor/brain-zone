package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.brainEnums.ElementScope;

/**
 *
 */
public class Token extends AbsIntelligentPropertyObject {
    protected Token(GridObjectDefinition definition, String name,
                    ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new TokenRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    @Override
    protected AbsDefinition DefaultDefinition() {
        return null;
    }

    @Override
	protected  int BindPropertyInstanceReferences(int index)
	{
		if (this.objectDefinition != null)
		{
			((TokenDefinition)this.objectDefinition).initProperties();
		}
		return super.BindPropertyInstanceReferences(index);
	}


    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }
}
