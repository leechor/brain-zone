package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;

/**
 *
 */
public class AbsBaseRunSpaceTrace extends AbsTrace_2<AbsBaseRunSpace, AbsBaseRunSpaceExpressionConverter> {
    public AbsBaseRunSpaceTrace(ElementReferenceStateGridItemPropertyObject elementReferenceStateGridItemProperties,
                                AbsBaseRunSpace absBaseRunSpace) {
        super(elementReferenceStateGridItemProperties, absBaseRunSpace);
    }

    @Override
    void InitializeStateImpl(Enum23 enum23) {

    }

    @Override
    protected void CopyFromCore(AbsBaseTrace absBaseTrace) {

    }

}
