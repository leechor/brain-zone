package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public class BeginStepProperty extends AbsStepProperty<BeginStepDefinition> {

    public BeginStepProperty(String name) {
        super(BeginStepDefinition.class, name);
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace)  {

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
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
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
}
