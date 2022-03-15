package org.licho.brain.concrete.property;

import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.ExitPointPropertyRow;
import org.licho.brain.concrete.MaybeReadXmlStepProperty;
import org.licho.brain.concrete.Properties;

/**
 *
 */
public class MissingBranchedStepProperty extends MaybeReadXmlStepProperty<MissingBranchedStepDefinition> {
    public MissingBranchedStepProperty(String name) {
        super(MissingBranchedStepDefinition.class, name);
    }

    @Override
    protected void initProperties() {
        Properties properties = this.properties;
        this.SecondExitPointProperty = (ExitPointPropertyRow) properties.search(t -> "Exit2".equals(t.Name()));
        super.initProperties();
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace){
        this.PrimaryExitPointProperty.processExit(tokenRunSpace);
    }

    @Override
    public String BreakpointLocation() {
        return null;
    }

    @Override
    public ActiveModel Model() {
        return null;
    }
}
