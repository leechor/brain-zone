package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsStepProperty;
import org.licho.brain.concrete.AgentElementRunSpace;
import org.licho.brain.concrete.ElementPropertyRow;
import org.licho.brain.concrete.ElementRunSpaceOperator;
import org.licho.brain.concrete.ExitPointPropertyRow;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.enu.Enum42;

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
