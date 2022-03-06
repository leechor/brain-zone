package com.zdpx.cctpp.concrete;

/**
 *
 */
public class MissingStepDefinition extends AbsBaseStepDefinition<MissingStepDefinition>{
    public MissingStepDefinition() {
        super("Missing");
    }

    @Override
    	public  AbsPropertyObject CreateInstance(String name)
	{
		return new MissingStepProperty(name);
	}
}
