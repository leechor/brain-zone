package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum42;

import java.text.MessageFormat;

/**
 *
 */
public class EndStepProperty extends AbsStepProperty<EndStepDefinition> {
    public EndStepProperty(String name) {
        super(EndStepDefinition.class, name);
    }

    @Override
    public void SetBreakpoint() {

    }

    @Override
    public BreakpointWrapper ClearBreakpoint() {
        return null;
    }

    @Override
    public void RestoreBreakpoint(BreakpointWrapper param0) {

    }

    @Override
    public void OnBreakpointChanged() {

    }

    @Override
    public Breakpoint Breakpoint() {
        return null;
    }

    @Override
    public String BreakpointLocation() {
        return null;
    }

    @Override
    public ActiveModel Model() {
        return null;
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {
		if (tokenRunSpace.TraceFlag())
		{
			String param = MessageFormat.format(EngineResources.Trace_EndStep_ProcessEnded,
                    tokenRunSpace.getProcessPropertyElementRunSpace().Name());
			tokenRunSpace.traceMethod(tokenRunSpace, Enum42.Zero, param);
		}
		tokenRunSpace.getProcessPropertyElementRunSpace().init(tokenRunSpace);
    }

    @Override
    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return false;
    }

    @Override
    public String getObjectClassName() {
        return null;
    }

    @Override
    public String getObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        return new String[0];
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }
}
