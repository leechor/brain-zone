package org.licho.brain.concrete;

import org.licho.brain.concrete.property.ExperimentConstraintsDefinition;

/**
 *
 */
public class Scenario extends AbsPropertyObject {
    private Object synchronizationContext;
    private int defaultReplicationsRequired;

    public Scenario(ExperimentConstraintsDefinition definition, String name) {
        super(definition, name);
        this.defaultReplicationsRequired = definition.DefaultReplicationsRequired();
        this.synchronizationContext = null;
    }
}
