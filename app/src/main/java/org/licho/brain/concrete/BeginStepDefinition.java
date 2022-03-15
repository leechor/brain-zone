package org.licho.brain.concrete;

import org.licho.brain.concrete.property.AbsBaseStepProperty;

/**
 *
 */
public class BeginStepDefinition extends AbsBaseStepDefinition<BeginStepDefinition> {
    public BeginStepDefinition() {
        super("Begin");
    }

    // Token: 0x06001456 RID: 5206 RVA: 0x0000EA88 File Offset: 0x0000CC88
    public AbsPropertyObject CreateInstance(String name) {
        return new BeginStepProperty(name);
    }

    // Token: 0x06001457 RID: 5207 RVA: 0x00107BD0 File Offset: 0x00105DD0
    public AbsBaseStepProperty createAbsBaseStepProperty(String name, ProcessProperty process) {
        BeginStepProperty beginStepProperty = (BeginStepProperty) this.CreateInstance(name);
        beginStepProperty.ProcessProperty = process;
        return beginStepProperty;
    }
}
