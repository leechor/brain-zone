package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.AbsPropertyObject;
import com.zdpx.cctpp.concrete.IRunSpace;
import com.zdpx.cctpp.concrete.IntelligentObjectRunSpace;
import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.Warning;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum42;

/**
 *
 */
public interface ITrace {
	/**
	 * A flag to turn on/off trace information for this object. A value of 0 denotes off, all other values denote on.
	 * @return
	 */
	boolean TraceFlag();

	void traceMethod(TokenRunSpace tokenRunSpace, Enum42 enum42, String param2);
	void traceMethod(double param0, TokenRunSpace tokenRunSpace, Enum42 enum42, String param3);
	void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning, TokenRunSpace tokenRunSpace, String param3, String param4, String param5);
	void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning, double param2, TokenRunSpace tokenRunSpace, String param4, String param5, String param6);
	void runtimeErrorHandler(IRunSpace runSpace, AbsPropertyObject absPropertyObject, IntelligentObjectProperty intelligentObjectProperty, String error);
	void runtimeErrorHandler(double timeNow, IRunSpace runSpace, AbsPropertyObject absPropertyObject, IntelligentObjectProperty intelligentObjectProperty, String error);

}
