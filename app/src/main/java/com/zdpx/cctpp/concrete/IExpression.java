package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.utils.simu.IReferencedObjects;

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
