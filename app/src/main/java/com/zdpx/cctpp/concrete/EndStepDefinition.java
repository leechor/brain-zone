package com.zdpx.cctpp.concrete;

/**
 *
 */
public class EndStepDefinition extends AbsBaseStepDefinition<EndStepDefinition> {
    public EndStepDefinition() {
        super("End");
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new EndStepProperty(name);
    }

}
