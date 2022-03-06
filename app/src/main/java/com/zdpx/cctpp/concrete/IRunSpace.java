package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.TableRowReferences;
import com.zdpx.cctpp.utils.simu.ITrace;

/**
 *
 */
public interface IRunSpace extends ITrace {
	IntelligentObjectRunSpace ContextObjectRunSpace();

	IntelligentObjectRunSpace AssociatedObjectRunSpace();

	IntelligentObjectRunSpace ParentObjectRunSpace();

	AbsIntelligentPropertyObject MyElementInstance();

	TableRowReferences GetTableRowReferences();
}
