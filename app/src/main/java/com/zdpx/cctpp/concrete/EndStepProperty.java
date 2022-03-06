package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum42;
import com.zdpx.cctpp.exceptions.InvalidOperationException;

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
			String param = String.format(EngineResources.Trace_EndStep_ProcessEnded,
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
    public String GetGridObjectClassName() {
        return null;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties param0, GridObjectDefinition param1) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
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
