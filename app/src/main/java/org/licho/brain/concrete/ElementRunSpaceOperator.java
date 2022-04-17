package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.fixed.FixedRunSpace;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;

/**
 *
 */
public class ElementRunSpaceOperator {

    public static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                   ElementRunSpaceOperator.ObjectType param1) {
        return ElementRunSpaceOperator.getElementRunSpace(t, runSpace, param1, null, null, null, false);
    }

    public static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                   EnumPropertyRow enumPropertyRow,
                                                                   IntelligentObjectProperty intelligentObjectProperty) {
        ElementRunSpaceOperator.ObjectType objectType =
                (ElementRunSpaceOperator.ObjectType) enumPropertyRow.getObjectValue(runSpace,
                        runSpace.ParentObjectRunSpace());
        return ElementRunSpaceOperator.getElementRunSpace(t, runSpace, objectType, enumPropertyRow,
                intelligentObjectProperty,
                runSpace.ParentObjectRunSpace(), false);
    }

    public static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                   EnumPropertyRow enumPropertyRow,
                                                                   IntelligentObjectProperty intelligentObjectProperty,
                                                                   boolean param3) {
        ElementRunSpaceOperator.ObjectType objectType =
                (ElementRunSpaceOperator.ObjectType) enumPropertyRow.getObjectValue(runSpace,
                        runSpace.ParentObjectRunSpace());
        return ElementRunSpaceOperator.getElementRunSpace(t, runSpace, objectType, enumPropertyRow,
                intelligentObjectProperty,
                runSpace.ParentObjectRunSpace(), param3);
    }

    public static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                   ElementRunSpaceOperator.ObjectType param1,
                                                                   EnumPropertyRow param2) {
        return ElementRunSpaceOperator.getElementRunSpace(t, runSpace, param1, param2, null, null, false);
    }


    public static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                   IntelligentObjectProperty intelligentObjectProperty) {
        return ElementRunSpaceOperator.getElementRunSpace(t, runSpace,
                ElementRunSpaceOperator.ObjectType.SpecificObjectOrElement, null, intelligentObjectProperty,
                runSpace.ParentObjectRunSpace(), false);
    }

    public static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                   IntelligentObjectProperty intelligentObjectProperty,
                                                                   boolean param2) {
        return ElementRunSpaceOperator.getElementRunSpace(t, runSpace,
                ElementRunSpaceOperator.ObjectType.SpecificObjectOrElement, null, intelligentObjectProperty,
                runSpace.ParentObjectRunSpace(), param2);
    }

    public static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                   IntelligentObjectProperty intelligentObjectProperty,
                                                                   IntelligentObjectRunSpace param2) {
        return ElementRunSpaceOperator.getElementRunSpace(t, runSpace,
                ElementRunSpaceOperator.ObjectType.SpecificObjectOrElement, null, intelligentObjectProperty, param2,
                false);
    }

    private static <T extends AbsBaseRunSpace> T getElementRunSpace(Class<T> t, IRunSpace runSpace,
                                                                    ElementRunSpaceOperator.ObjectType objectType,
                                                                    EnumPropertyRow enumPropertyRow,
                                                                    IntelligentObjectProperty objectProperty,
                                                                    IntelligentObjectRunSpace intelligentObjectRunSpace, boolean param5) {
        AbsBaseRunSpace absBaseRunSpace = null;
        switch (objectType) {
            case ParentObject:
                absBaseRunSpace = runSpace.ParentObjectRunSpace();
                break;
            case AssociatedObject:
                if (runSpace.AssociatedObjectRunSpace() == null) {
                    RuntimeErrorFullMessageDetails.reportError(runSpace, enumPropertyRow,
                            MessageFormat.format(EngineResources.Error_RuntimeReference_NullOrUndefinedReferenceException,
                                    objectType, (enumPropertyRow != null) ? enumPropertyRow.getDisplay() :
                                            "undefined"));
                }
                absBaseRunSpace = runSpace.AssociatedObjectRunSpace();
                break;
            case SpecificObject:
            case SpecificObjectOrElement:
                absBaseRunSpace =
                        objectProperty.getExpressionResult(runSpace, intelligentObjectRunSpace).getAbsBaseRunSpace();
                if (absBaseRunSpace == null) {
                    if (objectProperty.isInvalidObjectNameValue(runSpace, intelligentObjectRunSpace) == 1.0) {
                        RuntimeErrorFullMessageDetails.reportError(runSpace, objectProperty,
                                MessageFormat.format(EngineResources.Error_RuntimeReference_NullOrUndefinedReferenceException,
                                        objectProperty.getIntelligentObjectPropertyValue(runSpace,
                                                intelligentObjectRunSpace),
                                        objectProperty.getDisplay()));
                    }
                    try {
                        return t.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                }
                break;
            default:
                try {
                    return (t.getDeclaredConstructor().newInstance());
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                    e.printStackTrace();
                }
        }

        if (absBaseRunSpace == null || !absBaseRunSpace.getClass().isAssignableFrom(t)) {
            AbsDefinition absDefinition =
                    ElementRunSpaceOperator.getDefaultDefinitionByRunSpaceType(t);
            IntelligentObjectProperty intelligentObjectProperty =
                    (objectType == ElementRunSpaceOperator.ObjectType.SpecificObject || objectType == ElementRunSpaceOperator.ObjectType.SpecificObjectOrElement) ? objectProperty : enumPropertyRow;
            RuntimeErrorFullMessageDetails.reportError(runSpace, intelligentObjectProperty,
                    MessageFormat.format(EngineResources.Error_RuntimeReference_ElementReferenceTypeMismatchException,
                            absBaseRunSpace.HierarchicalDisplayName(), (intelligentObjectProperty != null) ?
                                    intelligentObjectProperty.getDisplay() : "undefined", (absDefinition != null) ?
                                    absDefinition.Name() : "undefined"));
        }
        T telementRunSpace = (T) absBaseRunSpace;
        if (telementRunSpace.IsAgentPopulationStaticParent() && !param5) {
            IntelligentObjectProperty intelligentObjectProperty =
                    (objectType == ElementRunSpaceOperator.ObjectType.SpecificObject || objectType == ElementRunSpaceOperator.ObjectType.SpecificObjectOrElement) ? objectProperty : enumPropertyRow;
            RuntimeErrorFullMessageDetails.reportError(runSpace, intelligentObjectProperty,
                    MessageFormat.format(EngineResources.Error_RuntimeReference_AgentPopulationStaticParentReferenceException,
                            telementRunSpace.HierarchicalDisplayName(), (intelligentObjectProperty != null) ?
                                    intelligentObjectProperty.getDisplay() : "undefined"));
        }
        return telementRunSpace;
    }

    private static AbsDefinition getDefaultDefinitionByRunSpaceType(Class<?> runSpace) {
        AbsDefinition absDefinition =
                AbsDefinition.getDefinitions().stream().filter(t -> t.RunSpaceType() == runSpace).findFirst().orElse(null);

        if (absDefinition == null) {
            if (runSpace == IntelligentObjectRunSpace.class) {
                absDefinition = IntelligentObjectDefinition.Instance;
            } else if (runSpace == FixedRunSpace.class) {
                absDefinition = FixedDefinition.FixedFacility;
            } else if (runSpace == LinkRunSpace.class) {
                absDefinition = LinkDefinition.LinkFacility;
            } else if (runSpace == NodeRunSpace.class) {
                absDefinition = NodeDefinition.NodeFacility;
            } else if (runSpace == AgentElementRunSpace.class) {
                absDefinition = AgentDefinition.AgentFacility;
            } else if (runSpace == EntityRunSpace.class) {
                absDefinition = EntityDefinition.EntityFacility;
            } else if (runSpace == TransporterRunSpace.class) {
                absDefinition = TransporterDefinition.transporterFacility;
            }
        }
        return absDefinition;
    }

    public enum ObjectType {
        ParentObject,
        AssociatedObject,
        SpecificObject,
        SpecificObjectOrElement
    }

    public enum StateValueDataType {
        Any,
        Number,
        UnitlessNumber,
        DateTime,
        String,
        ObjectOrElementReference
    }
}
