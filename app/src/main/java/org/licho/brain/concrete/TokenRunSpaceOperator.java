package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;

/**
 *
 */
public class TokenRunSpaceOperator {
    public static AbsBaseRunSpace smethod_0(Table table, IRunSpace runSpace, AbsBaseRunSpace absBaseRunSpace) {
        if (table != null) {
            if (runSpace != null && runSpace.AssociatedObjectRunSpace() != null && runSpace.AssociatedObjectRunSpace().tableRowReferences.method_20(table)) {
                return runSpace.AssociatedObjectRunSpace();
            }
            if (absBaseRunSpace != null && absBaseRunSpace.tableRowReferences.method_20(table)) {
                return absBaseRunSpace;
            }
            if (runSpace instanceof TokenRunSpace && ((TokenRunSpace) runSpace).tableRowReferences.method_20(table)) {
                return (TokenRunSpace) runSpace;
            }
        }
        if (runSpace != null && runSpace.AssociatedObjectRunSpace() != null && runSpace.AssociatedObjectRunSpace()
                instanceof AgentElementRunSpace) {
            return runSpace.AssociatedObjectRunSpace();
        }
        if (runSpace instanceof TokenRunSpace) {
            TokenRunSpace tokenRunSpace = (TokenRunSpace) runSpace;
            return tokenRunSpace;
        }
        return absBaseRunSpace;
    }
}
