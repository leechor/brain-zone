package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.exception.SimioRuntimeException;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.UnitType;
import org.licho.brain.brainApi.enu.TimeUnit;

/**
 *
 */
public class RuntimeErrorFullMessageDetails {

    public static void reportError(String message) {
        reportError(null, null, null, message);
    }

    public static void reportError(IRunSpace runSpace, String message) {
        reportError(runSpace, null, null, message);
    }

    public static void reportError(IRunSpace runSpace,
                                   IntelligentObjectProperty objectProperty, String message) {
        reportError(runSpace, null, objectProperty, message);
    }

    public static void reportError(IRunSpace runSpace, AbsPropertyObject propertyObject,
                                   IntelligentObjectProperty objectProperty, String message) {
        if (runSpace == null) {
            throw new SimioRuntimeException(message);
        }
        runSpace.runtimeErrorHandler(runSpace, propertyObject, objectProperty, message);
    }

    public static boolean IsError(ActionRun actionRun, double unitCarry, IRunSpace runSpace,
                                  AbsPropertyObject propertyObject,
                                  IntelligentObjectProperty objectProperty, String param4,
                                  StringBuilder errorMessage) {
        if (actionRun.empty()) {
            return false;
        }
        try (var tmp = actionRun.disposable()) {
            errorMessage.append(RuntimeErrorFullMessageDetails.formatError(unitCarry, runSpace, propertyObject,
                    objectProperty, param4));
        }
        return true;
    }

    private static String formatError(double unitCarry, IRunSpace runSpace, AbsPropertyObject propertyObject,
                                      IntelligentObjectProperty objectProperty, String param4) {
        StringBuilder error = new StringBuilder();
        TokenRunSpace tokenRunSpace = (TokenRunSpace) runSpace;
        AbsBaseRunSpace absBaseRunSpace = (AbsBaseRunSpace) runSpace;
        if (tokenRunSpace != null && tokenRunSpace.getStepProperty() != null) {
            if (tokenRunSpace.AssociatedObjectRunSpace() != null) {
                error.append(tokenRunSpace.AssociatedObjectRunSpace() instanceof EntityRunSpace ?
                        String.format(EngineResources.RuntimeError_FullMessageDetails_Entity,
                                tokenRunSpace.AssociatedObjectRunSpace().HierarchicalDisplayName())
                        : String.format(EngineResources.RuntimeError_FullMessageDetails_Object,
                        tokenRunSpace.AssociatedObjectRunSpace().HierarchicalDisplayName()) + "\n\n");
            }
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Time_TokenRunSpaceContext,
                    tokenRunSpace.getMayApplication().getInitValue(unitCarry, UnitType.Time),
                    tokenRunSpace.getMayApplication().getUnitDescription(UnitType.Time)) + "\n\n");
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Object,
                    tokenRunSpace.ParentObjectRunSpace.HierarchicalDisplayName()) + "\n");
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Process,
                    tokenRunSpace.getProcessPropertyElementRunSpace().Name()) + "\n");
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Token,
                    tokenRunSpace.Name()) + "\n");
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Step,
                    tokenRunSpace.getStepProperty().getNameDisplay()) + "\n");
        } else if (absBaseRunSpace != null) {
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Time,
                    absBaseRunSpace.getMayApplication().getInitValue(unitCarry, UnitType.Time),
                    absBaseRunSpace.getMayApplication().getUnitDescription(UnitType.Time)) + "\n\n");
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_ObjectOrElement,
                    absBaseRunSpace.HierarchicalDisplayName()) + "\n");
        } else if (propertyObject != null) {
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Time, unitCarry,
                    TimeUnit.values()[0] + "\n\n"));
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Item,
                    propertyObject.objectDefinition.Name(), propertyObject.InstanceName() + "\n"));
        }
        if (objectProperty != null) {
            error.append(String.format(EngineResources.RuntimeError_FullMessageDetails_Property,
                    objectProperty.Name()) + "\n");
        }
        return error.append("\n").append(param4).toString();
    }
}
