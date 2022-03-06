package com.zdpx.cctpp.concrete.property;

import com.zdpx.cctpp.concrete.AbsBaseStepDefinition;
import com.zdpx.cctpp.concrete.AbsPropertyObject;
import com.zdpx.cctpp.concrete.ExitPointPropertyDefinition;

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
