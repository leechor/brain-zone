package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.simuApi.extensions.IStepDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class StepDefinition extends AbsStepDefinition {
    private IStepDefinition iStepDefinition;

    private Guid guid;
    public static IStepDefinitions StepDefinions;
    private static Map<Guid, StepDefinition> stepDefinitions;

    public StepDefinition(IStepDefinition stepDefinition) {
        super(stepDefinition.Name());

        this.iStepDefinition = stepDefinition;
        this.guid = this.iStepDefinition.UniqueID();
        if (this.iStepDefinition.NumberOfExits() > 1) {
            super.getPropertyDefinitions().add(new ExitPointPropertyDefinition("AlternateExit"));
            this.level = 3;
        }
        this.iStepDefinition.DefineSchema(super.getPropertyDefinitions());
        super.Description(stepDefinition.Description());
    }

    IStepDefinition getStepDefinition() {
        return this.iStepDefinition;
    }

    public Guid getGuid() {
        return this.guid;
    }

    public AbsStepDefinition DefaultDefinition() {
        return this.DefaultDefinition(null);
    }

    @Override
    public AbsStepDefinition DefaultDefinition(Class<?> t) {
        if (StepDefinition.stepDefinitions == null) {
            StepDefinition.stepDefinitions = new HashMap<>();
        }
        StepDefinition stepDefinition = StepDefinition.stepDefinitions.get(this.guid);
        if (stepDefinition == null) {
            if (this.getStepDefinition() != null) {
                stepDefinition = new StepDefinition(this.getStepDefinition());
            } else {
                stepDefinition = this;
            }
            StepDefinition.stepDefinitions.put(guid, stepDefinition);
        }
        return stepDefinition;

    }

    public static void clear() {
        StepDefinition.stepDefinitions = null;
    }


    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new BaseStepProperty(this, name);
    }


}
