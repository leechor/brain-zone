package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public class AbsBaseRunSpaceTableTrace extends AbsBaseTableTrace<AbsBaseRunSpace, AbsBaseRunSpaceExpressionConverter> {
	public AbsBaseRunSpaceTableTrace(ElementReferenceStateGridItemPropertyObject baseSomeIGridItemProperties, AbsBaseRunSpace absBaseRunSpace)
	{
		 super(baseSomeIGridItemProperties, absBaseRunSpace);
	}

	@Override
	public Object TableValue(IUnit unit) {
		return null;
	}

	@Override
	void InitializeStateImpl(Enum23 enum23) {

	}

	@Override
	protected void CopyFromCore(AbsBaseTrace absBaseTrace) {

	}
}
