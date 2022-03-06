package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.utils.simu.IUnit;

/**
 *
 */
public interface IExpressionConverter<T> {
	// Token: 0x06001AF5 RID: 6901
	T FromExpressionResult(T original, ExpressionValue expressionValue, int param2, AbsBaseTrace absBaseTrace);

	// Token: 0x06001AF6 RID: 6902
	ExpressionValue ToExpressionResult(T value, int param1, AbsBaseTrace absBaseTrace);

	// Token: 0x06001AF7 RID: 6903
	String FormattedValue(IUnit unit, T value, int param2, AbsBaseTrace absBaseTrace);

	// Token: 0x06001AF8 RID: 6904
	T GetInitialValue(AbsBaseTrace absBaseTrace, IntelligentObjectRunSpace intelligentObject);
}
