package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.fake.TableRowReferences;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum42;
import org.licho.brain.utils.simu.ITrace;

/**
 *
 */
public class TraceRunSpace implements ITrace, IRunSpace{
    private IntelligentObjectRunSpace parentObjectRunSpace;

    private IntelligentObjectRunSpace associatedObjectRunSpace;

    public TraceRunSpace(IntelligentObjectRunSpace intelligentObjectRunSpace, EntityRunSpace entityRunSpace) {
        this.parentObjectRunSpace = parentObjectRunSpace;
        this.associatedObjectRunSpace = associatedObjectRunSpace;
    }

    public IntelligentObjectRunSpace ContextObjectRunSpace() {
        return this.parentObjectRunSpace;
    }

    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return this.associatedObjectRunSpace;
    }

    public IntelligentObjectRunSpace ParentObjectRunSpace() {
        return this.parentObjectRunSpace;
    }

    public AbsIntelligentPropertyObject MyElementInstance() {
        return null;
    }

    public TableRowReferences GetTableRowReferences() {
        return null;
    }

    public boolean TraceFlag() {
        return this.associatedObjectRunSpace.TraceFlag();
    }

    public void traceMethod(TokenRunSpace tokenRunSpace, Enum42 enum42, String param2) {
        this.traceMethod(this.associatedObjectRunSpace.getSomeRun().TimeNow(), tokenRunSpace, enum42, param2);
    }

    public void traceMethod(double param0, TokenRunSpace param1, Enum42 param2, String param3) {
        this.associatedObjectRunSpace.traceMethod(param0, param1, param2, param3);
    }

    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      TokenRunSpace tokenRunSpace, String param3, String param4, String param5) {
        this.runtimeWarningHandler(intelligentObjectRunSpace, warning,
                this.associatedObjectRunSpace.getSomeRun().TimeNow(), tokenRunSpace, param3, param4, param5);
    }

    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      double param2, TokenRunSpace tokenRunSpace, String param4, String param5,
                                      String param6) {
        this.associatedObjectRunSpace.runtimeWarningHandler(intelligentObjectRunSpace, warning, param2,
                tokenRunSpace, param4, param5, param6);
    }

    public void runtimeErrorHandler(IRunSpace runSpace, AbsPropertyObject absPropertyObject,
                                    IntelligentObjectProperty intelligentObjectProperty, String error) {
        this.runtimeErrorHandler(this.associatedObjectRunSpace.getSomeRun().TimeNow(), runSpace, absPropertyObject,
                intelligentObjectProperty, error);
    }

    public void runtimeErrorHandler(double timeNow, IRunSpace runSpace, AbsPropertyObject absPropertyObject,
                                    IntelligentObjectProperty intelligentObjectProperty, String error) {
        this.associatedObjectRunSpace.runtimeErrorHandler(timeNow, runSpace, absPropertyObject, intelligentObjectProperty, error);
    }
}
