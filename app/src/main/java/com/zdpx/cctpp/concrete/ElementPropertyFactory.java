package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.element.Network;
import com.zdpx.cctpp.utils.simu.NameDefinitionFunctionOperator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ElementPropertyFactory implements NameDefinitionFunctionOperator {


    @Override
    public List<NameDefinitionFunction> getNameDefinitionFunctions() {
        List<NameDefinitionFunction> nameDefinitionFunctions = new ArrayList<>();
        NameDefinitionFunction item = new NameDefinitionFunction();
        item.name = "ElementProperty";
        item.func = ElementPropertyDefinition::new;

        nameDefinitionFunctions.add(item);
        NameDefinitionFunction item2 = new NameDefinitionFunction();
        item2.name = "NetworkProperty";
        item2.func = t ->  new ElementPropertyDefinition(t, Network.class);
        nameDefinitionFunctions.add(item2);
        return nameDefinitionFunctions;
    }
}
