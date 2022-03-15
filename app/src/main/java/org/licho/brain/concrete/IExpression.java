package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.enu.UnitType;
import org.licho.brain.utils.simu.IReferencedObjects;

/**
 *
 */
public interface IExpression {
    boolean equal(StringPropertyDefinition stringPropertyDefinition);

    boolean equal(BaseStatePropertyObject baseStatePropertyObject);

    boolean equal(AbsIntelligentPropertyObject absIntelligentPropertyObject);

    boolean equal(AbsListProperty absListProperty);

    boolean equal(Table table);

    boolean equal(UserFunction userFunction);

    boolean equal(TokenDefinition tokenDefinition);

    boolean equal(IntelligentObjectDefinition intelligentObjectDefinition);

    boolean equal(ExpressionFunction expressionFunction);

    boolean equal(AbsInputParameter absInputParameter);

    void CollectReferencedObjects(IReferencedObjects referencedObjects);

    double getDoubleValue();

    String getStringValue();

    ExpressionValue GetExpressionValue(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                       AbsBaseRunSpace absBaseRunSpace, UnitType unitType, int i,
                                       UnitConversions unitConversions, boolean b, IReport report);

    PropertyDefinitionModel getPropertyDefinitionModel();

}
