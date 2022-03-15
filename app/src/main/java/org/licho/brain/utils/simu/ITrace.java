package org.licho.brain.utils.simu;

import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.IRunSpace;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.Warning;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum42;

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
