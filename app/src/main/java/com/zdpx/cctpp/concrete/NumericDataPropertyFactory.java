package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.NumericDataType;
import com.zdpx.cctpp.utils.simu.NameDefinitionFunctionOperator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class NumericDataPropertyFactory implements NameDefinitionFunctionOperator {
    @Override
    public List<NameDefinitionFunction> getNameDefinitionFunctions() {
        List<NameDefinitionFunction> nameDefinitionFunctions = new ArrayList<>();
        NameDefinitionFunction nameDefinitionFunction = new NameDefinitionFunction();
        nameDefinitionFunction.name = "RealProperty";
        nameDefinitionFunction.func = t -> new NumericDataPropertyDefinition(t, NumericDataType.Real);
        nameDefinitionFunctions.add(nameDefinitionFunction);

        NameDefinitionFunction definitionFunction = new NameDefinitionFunction();
        definitionFunction.name = "IntegerProperty";
        definitionFunction.func = t -> new NumericDataPropertyDefinition(t, NumericDataType.Integer);

        nameDefinitionFunctions.add(definitionFunction);
        return nameDefinitionFunctions;

    }
}
