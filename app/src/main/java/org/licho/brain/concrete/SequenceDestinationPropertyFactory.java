package org.licho.brain.concrete;

import org.licho.brain.utils.simu.NameDefinitionFunctionOperator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class SequenceDestinationPropertyFactory implements NameDefinitionFunctionOperator {
    @Override
    public List<NameDefinitionFunction> getNameDefinitionFunctions() {
        List<NameDefinitionFunction> list = new ArrayList<>();
        NameDefinitionFunction item = new NameDefinitionFunction();
        item.name = "SequenceDestinationProperty";

        item.func = SequenceDestinationPropertyDefinition::new;
        list.add(item);
        NameDefinitionFunction item2 = new NameDefinitionFunction();
        item2.name = "TransferNodeProperty";

        item2.func = SequenceDestinationPropertyDefinition::new;
        list.add(item2);
        return list;
    }
}
