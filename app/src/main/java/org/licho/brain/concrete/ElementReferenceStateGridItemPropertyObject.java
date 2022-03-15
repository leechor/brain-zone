package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class ElementReferenceStateGridItemPropertyObject extends BaseStateIGridItemPropertyObject {
    public ElementReferenceStateGridItemPropertyObject(ElementReferenceStatePropertyObject elementReferenceStateProperty,
                                                       StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        super(elementReferenceStateProperty, stateIGridItemPropertyObjectList);
    }

    @Override
    public AbsBaseTrace CreateStateRunSpace(AbsBaseRunSpace absBaseRunSpace) {
        if (super.haveState()) {
            return new AbsBaseRunSpaceTrace(this, absBaseRunSpace);
        }
        return new AbsBaseRunSpaceTableTrace(this, absBaseRunSpace);
    }
}
