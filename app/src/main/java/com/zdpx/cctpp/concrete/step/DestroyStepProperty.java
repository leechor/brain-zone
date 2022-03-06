package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.concrete.AbsStepProperty;
import com.zdpx.cctpp.concrete.AgentElementRunSpace;
import com.zdpx.cctpp.concrete.ElementPropertyRow;
import com.zdpx.cctpp.concrete.ElementRunSpaceOperator;
import com.zdpx.cctpp.concrete.ExitPointPropertyRow;
import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.entity.EnumPropertyRow;
import com.zdpx.cctpp.enu.Enum42;

import java.text.MessageFormat;
import java.util.Objects;

/**
 *
 */
public class DestroyStepProperty extends AbsStepProperty<DestroyStepDefinition> {
    private EnumPropertyRow destroyType;
    private ElementPropertyRow entityObject;

    public DestroyStepProperty(Class<DestroyStepDefinition> cl, String name) {
        super(cl, name);
    }

    @Override
    protected void initProperties() {
        this.SecondExitPointProperty = (ExitPointPropertyRow) this.properties.search(t -> Objects.equals(t.Name(),
                "Failed"));
        this.destroyType = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "DestroyType"));
        this.entityObject = (ElementPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "EntityObject"));
        super.initProperties();
    }

    public EnumPropertyRow getDestroyType() {
        return this.destroyType;
    }

    public ElementPropertyRow getEntityObject() {
        return this.entityObject;
    }


    @Override
    public void execute(TokenRunSpace tokenRunSpace) {

        AgentElementRunSpace agentElementRunSpace =
                ElementRunSpaceOperator.getElementRunSpace(AgentElementRunSpace.class, tokenRunSpace,
                        this.getDestroyType(), this.getEntityObject());
        if (agentElementRunSpace != null) {
            if (!agentElementRunSpace.IsDestroyable()) {
                if (tokenRunSpace.TraceFlag()) {
                    String actionMessage = MessageFormat.format(Messages.Trace_DestroyStep_FailedToDestroyObject,
                            agentElementRunSpace.Name());
                    tokenRunSpace.traceMethod(tokenRunSpace, Enum42.Zero, actionMessage);
                }
                this.SecondExitPointProperty.processExit(tokenRunSpace);
                return;
            }
            if (tokenRunSpace.TraceFlag()) {
                String actionMessage = MessageFormat.format(Messages.Trace_DestroyStep_DestroyingObject,
                        agentElementRunSpace.Name());
                tokenRunSpace.traceMethod(tokenRunSpace, Enum42.Zero, actionMessage);
            }
            agentElementRunSpace.getPopulation().method_39(agentElementRunSpace, tokenRunSpace);
        }
        if (tokenRunSpace.ParentObjectRunSpace != null) {
            this.PrimaryExitPointProperty.processExit(tokenRunSpace);
        }

    }
}
