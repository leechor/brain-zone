package org.licho.brain.concrete.fixed;

import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.Breakpoint;
import org.licho.brain.concrete.BreakpointWrapper;
import org.licho.brain.concrete.CommonItems;
import org.licho.brain.concrete.Direction;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.GridObjectDefinition;
import org.licho.brain.concrete.IntelligentObject;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.ItemEditPolicy;
import org.licho.brain.concrete.Location;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainApi.IFixedObject;
import org.licho.brain.utils.simu.IBreakpoint;

import java.util.Map;

/**
 * typically used to represent an entire system being modeled(e.g. the plant), or component objects within a system that
 * have a fixed location(e.g. machine, equipment, work cells).
 */
public class Fixed extends IntelligentObject implements IBreakpoint {
    private EnumPropertyRow transferInConstraintsType;
    private ExpressionPropertyRow transferInCondition;
    private EnumPropertyRow transferOutConstraintsType;
    private ExpressionPropertyRow transferOutCondition;
    private FixedObject fixedObject;
    private Fixed fixed;
    private Breakpoint breakpoint;

    public Fixed(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }


    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.transferInConstraintsType = (EnumPropertyRow) this.properties.values.get(index++);
        this.transferInCondition = (ExpressionPropertyRow) this.properties.values.get(index++);
        this.transferOutConstraintsType = (EnumPropertyRow) this.properties.values.get(index++);
        this.transferOutCondition = (ExpressionPropertyRow) this.properties.values.get(index++);
        return index;
    }

    public EnumPropertyRow getTransferInConstraintsType() {
        return this.transferInConstraintsType;
    }

    public ExpressionPropertyRow getTransferInCondition() {
        return this.transferInCondition;
    }

    public EnumPropertyRow getTransferOutConstraintsType() {
        return this.transferOutConstraintsType;
    }

    public ExpressionPropertyRow getTransferOutCondition() {
        return this.transferOutCondition;
    }

    @Override
    public void InitializeFacilityLocation(double x, double y, double z) {
        Location location = new Location(x, y, z);
        Direction direction = location.sub(this.location);
        this.location = location;
        this.OnInitializeFacilityLocation(x, y, z);
        if (super.Parent() != null) {
            super.Parent().setLocationDirection(this, this.location, direction);
        }
        super.InitializeFacilityLocation(x, y, z);
    }

    protected void OnInitializeFacilityLocation(double x, double y, double z) {
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObject,
                                                        MayApplication application) {
        return new FixedRunSpace(sourceIntelligentObject, application, this);
    }

    public IFixedObject getFixedObject() {
        if (this.fixedObject == null) {
            this.fixedObject = new FixedObject(this);
        }
        return this.fixedObject;
    }

    public void SetBreakpoint() {

    }

    public BreakpointWrapper ClearBreakpoint() {
        return null;
    }

    public void RestoreBreakpoint(BreakpointWrapper param0) {

    }

    public void OnBreakpointChanged() {

    }

    public Breakpoint Breakpoint() {
        return this.breakpoint;
    }

    public String BreakpointLocation() {
        return this.InstanceName();
    }

    public ActiveModel Model() {
        if (super.parent != null) {
            return super.parent.ActiveModel();
        }
        return null;
    }

    public boolean method_94(TokenRunSpace tokenRunSpace,
                             IntelligentObjectRunSpace intelligentObject) {
        // TODO: 2022/1/13 
        return false;
    }

    private void method_95(Class181 param0) {

    }

    @Override
    protected void ReadAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                         Map<String, String> savedAttributes) {

    }

    @Override
    protected void WriteBodyToXml(XmlWriter xmlWriter, CommonItems commonItems) {
        super.WriteBodyToXml(xmlWriter, commonItems);
        if (this.breakpoint != null) {
            this.breakpoint.writeXmlBreakpoint(xmlWriter);
        }
    }

    @Override
    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return false;
    }

    @Override
    public void LoadOldDefaultValuesForLoadFrom(IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 32) {
            // TODO: 2021/11/15
        }
        super.LoadOldDefaultValuesForLoadFrom(intelligentObjectXml);
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    public EnumPropertyRow method_89() {
        return this.transferInConstraintsType;
    }

    public EnumPropertyRow method_91() {
        return this.transferOutConstraintsType;
    }

    private static class AssociatedObjectBreakpoint {
        public TokenRunSpace tokenRunSpace;

        public Breakpoint breakpoint;
    }

}
