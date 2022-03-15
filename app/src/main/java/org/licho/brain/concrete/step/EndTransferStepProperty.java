package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsStepProperty;
import org.licho.brain.concrete.CalendarItem;
import org.licho.brain.concrete.ElementPropertyRow;
import org.licho.brain.concrete.ElementRunSpaceOperator;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.LinkRunSpace;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.concrete.StringHelper;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.enu.EntityLocationTransitionState;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum42;

import java.text.MessageFormat;
import java.util.Objects;

/**
 *
 */
public class EndTransferStepProperty extends AbsStepProperty<EndTransferStepDefinition> {
    private EnumPropertyRow entityType;
    private ElementPropertyRow entityObject;

    public EndTransferStepProperty(Class<EndTransferStepDefinition> cl, String name) {
        super(cl, name);
    }

    @Override
    protected void initProperties() {
        this.entityType = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "EntityType"));
        this.entityObject = (ElementPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "EntityObject"));
        super.initProperties();
    }

    public EnumPropertyRow getEntityType() {
        return this.entityType;
    }

    public ElementPropertyRow getEntityObject() {
        return this.entityObject;
    }

    @Override
    public IntelligentObjectProperty GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 78 && StringHelper.equalsLocal(name, "ObjectType")) {
            return this.getEntityType();
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }


    @Override
    public void execute(TokenRunSpace tokenRunSpace) {
        EntityRunSpace elementRunSpace = ElementRunSpaceOperator.getElementRunSpace(EntityRunSpace.class, tokenRunSpace,
                this.getEntityType(), this.getEntityObject());

        if (elementRunSpace != null) {
            if (elementRunSpace.getEntityLocationTransitionState() != EntityLocationTransitionState.None) {
                if (elementRunSpace.getEntityLocationTransitionState() == EntityLocationTransitionState.FlowingThroughRegulator && elementRunSpace.currentLink() != null && !elementRunSpace.haveRegulatorOperator()) {
                    this.method_44(tokenRunSpace, elementRunSpace);
                } else if (tokenRunSpace.TraceFlag()) {
                    String message = MessageFormat.format(Messages.Trace_EndTransferStep_IgnoringEndTransfer,
                            elementRunSpace.Name(),
                            elementRunSpace.getEntityLocationTransitionState().toString());
                    tokenRunSpace.traceMethod(tokenRunSpace, Enum42.Zero, message);
                }
            } else {
                switch (elementRunSpace.getEntityLocationType()) {
                    case Station: {
                        this.translateStationTo(tokenRunSpace, elementRunSpace);
                        break;
                    }

                }
            }
        }
    }

    private void translateStationTo(TokenRunSpace tokenRunSpace, EntityRunSpace entityRunSpace) {
        if (entityRunSpace.CurrentStation().getEntityRunSpace() == entityRunSpace) {
            if (tokenRunSpace.TraceFlag()) {
                String actionMessage = MessageFormat.format(Messages.Trace_EndTransferStep_EndingTransferIntoStation,
                        entityRunSpace.Name(),
                        entityRunSpace.CurrentStation().ParentObjectRunSpace.Name() + "." + entityRunSpace.CurrentStation().Name());
                tokenRunSpace.traceMethod(tokenRunSpace, Enum42.Zero, actionMessage);
            }
            entityRunSpace.CurrentStation().method_22();
            if (entityRunSpace.getMayApplication().ActiveModel() != null && entityRunSpace.getMayApplication().ActiveModel().isOneStepping()) {
                entityRunSpace.getMayApplication().ActiveModel().method_66(MessageFormat.format(Messages.Trace_CoarseStepping_EntityTransferredIntoStation, entityRunSpace.CurrentStation().myElementInstance.InstanceName()));
            }
            return;
        }
        this.transfer(tokenRunSpace, entityRunSpace);
    }

    private void method_44(TokenRunSpace tokenRunSpace, EntityRunSpace entityRunSpace) {
        LinkRunSpace linkRunSpace = null;
        for (EntityRunSpace.OccupiedLink occupiedLink : entityRunSpace.occupiedLinks) {
            if (occupiedLink.LinkRunSpace.getEntityRunSpace() == entityRunSpace && tokenRunSpace.ParentObjectRunSpace == occupiedLink.LinkRunSpace) {
                linkRunSpace = occupiedLink.LinkRunSpace;
                break;
            }
        }

        if (linkRunSpace == null) {
            for (EntityRunSpace.OccupiedLink occupiedLink : entityRunSpace.occupiedLinks) {
                if (occupiedLink.LinkRunSpace.getEntityRunSpace() == entityRunSpace) {
                    linkRunSpace = occupiedLink.LinkRunSpace;
                    break;
                }
            }
        }

        if (linkRunSpace != null) {
            if (tokenRunSpace.TraceFlag()) {
                String endingTransferOntoLink =
                        MessageFormat.format(Messages.Trace_EndTransferStep_EndingTransferOntoLink,
                                entityRunSpace.Name(), linkRunSpace.Name());
                tokenRunSpace.traceMethod(tokenRunSpace, Enum42.Zero, endingTransferOntoLink);
            }
            linkRunSpace.method_88();
            if (entityRunSpace.getMayApplication().ActiveModel() != null && entityRunSpace.getMayApplication().ActiveModel().isOneStepping()) {
                entityRunSpace.getMayApplication().ActiveModel().method_66(MessageFormat.format(Messages.Trace_CoarseStepping_EntityTransferredOntoLink, linkRunSpace.Name()));
            }
            return;
        }
        this.transfer(tokenRunSpace, entityRunSpace);

    }

    private void transfer(TokenRunSpace tokenRunSpace, EntityRunSpace entityRunSpace) {
        if (tokenRunSpace.TraceFlag()) {
            String firingTransferEvents = MessageFormat.format(Messages.Trace_EndTransferStep_FiringTransferEvents,
                    entityRunSpace.Name());
            tokenRunSpace.traceMethod(tokenRunSpace, Enum42.Zero, firingTransferEvents);
        }
        entityRunSpace.getTransferringEvent().method_9(entityRunSpace);
        if (entityRunSpace.getMayApplication().ActiveModel() != null && entityRunSpace.getMayApplication().ActiveModel().isOneStepping()) {
            this.method_45(entityRunSpace.getMayApplication(),
                    MessageFormat.format(Messages.Trace_CoarseStepping_EntityTransferredTo,
                    entityRunSpace.getName()));
        }
        entityRunSpace.getTransferredEvent().method_9(entityRunSpace);
    }

    private void method_45(MayApplication application, String param1) {
        CalendarItem calendarItem = application.getRunOperatorWrapper().createCalendarItem();
        calendarItem.delegate4_0 = (CalendarItem t) ->
        {
            application.ActiveModel().method_66(param1);
        };
//		calendarItem.CalendarEventHandlerWrapper = this; todo
        application.getSomeRun().method_47(calendarItem);
    }
}
