package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.TableRowReferences;
import org.licho.brain.utils.simu.ITrace;

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
