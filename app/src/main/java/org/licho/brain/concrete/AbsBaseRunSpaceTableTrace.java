package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class AbsBaseRunSpaceTableTrace extends AbsBaseTableTrace<org.licho.brain.concrete.property.AbsBaseRunSpace, AbsBaseRunSpaceExpressionConverter> {
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
