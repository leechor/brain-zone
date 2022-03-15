package org.licho.brain.concrete.property;

import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.ExitPointPropertyDefinition;

/**
 *
 */
public class MissingBranchedStepDefinition extends AbsBaseStepDefinition<MissingBranchedStepDefinition> {
    public MissingBranchedStepDefinition() {
        super("MissingBranched");
        ExitPointPropertyDefinition item = new ExitPointPropertyDefinition("Exit2");
        super.getPropertyDefinitions().add(item);
        this.level = 3;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new MissingBranchedStepProperty(name);
    }
}
