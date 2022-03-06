package com.zdpx.cctpp.concrete.property;

import com.zdpx.cctpp.concrete.ActiveModel;
import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.ExitPointPropertyRow;
import com.zdpx.cctpp.concrete.MaybeReadXmlStepProperty;
import com.zdpx.cctpp.concrete.Properties;

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
