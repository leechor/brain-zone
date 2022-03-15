package org.licho.brain.concrete;

/**
 *
 */
public class MissingStepProperty extends MaybeReadXmlStepProperty<MissingStepDefinition> {
    public MissingStepProperty(String name) {
        super(MissingStepDefinition.class, name);
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {
        this.PrimaryExitPointProperty.processExit(tokenRunSpace);
    }

}
