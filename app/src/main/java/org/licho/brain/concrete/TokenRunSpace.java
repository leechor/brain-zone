package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.TableRowReferences;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.StatisticsDataSourceInfo;
import org.licho.brain.utils.simu.IAssociatedObjectRunSpace;

import java.util.EnumSet;

/**
 *
 */
public class TokenRunSpace extends AbsBaseRunSpace implements IAssociatedObjectRunSpace {
    public int beginStepIndex = -1;
    private double timeNow;
    private IntelligentObjectRunSpace associatedObjectRunSpace;
    private IntelligentObjectRunSpace contextObjectRunSpace;
    private int index = -1;
    private ProcessPropertyElementRunSpace processPropertyElementRunSpace;
    private AbsBaseStepProperty stepProperty;
    private EnumSet<Enum70> enum70;

    public TokenRunSpace(IntelligentObjectRunSpace parent, MayApplication application,
                         Token intelligentObject) {
        super(parent, application, intelligentObject);
        // TODO: 2021/12/16 
    }

    public double getTimeNow() {
        return this.timeNow;
    }

    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        if (this.associatedObjectRunSpace == null || this.associatedObjectRunSpace.IsAgentPopulationMemberOnFreeList()) {
            return null;
        }
        return this.associatedObjectRunSpace;
    }

    public void AssociatedObjectRunSpace(IntelligentObjectRunSpace value) {
        if (this.associatedObjectRunSpace != null) {
            this.associatedObjectRunSpace.updateAssociateObject(this);
        }
        this.associatedObjectRunSpace = value;
        if (this.associatedObjectRunSpace != null) {
            this.associatedObjectRunSpace.setAssociatedObject(this);
        }
    }

    @Override
    public StatisticsDataSourceInfo GetStatisticsDataSourceInfo(String param0) {
        return null;
    }

    @Override
    public void runtimeErrorHandler(IRunSpace param0, AbsPropertyObject param1, IntelligentObjectProperty param2,
                                    String param3) {

    }

    @Override
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2,
                                    IntelligentObjectProperty param3, String param4) {

    }

    public int getIndex() {
        return this.index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public ProcessPropertyElementRunSpace getProcessPropertyElementRunSpace() {
        return this.processPropertyElementRunSpace;
    }

    public AbsBaseStepProperty getStepProperty() {
        return this.stepProperty;
    }

    public void setBaseStepProperty(AbsBaseStepProperty absBaseStepProperty) {
        this.stepProperty = absBaseStepProperty;
    }

    public void init(ProcessPropertyElementRunSpace processPropertyElementRunSpace,
                     IntelligentObjectRunSpace associatedObjectRunSpace,
                     IntelligentObjectRunSpace contextObjectRunSpace, TableRowReferences tableRowReferences) {
        this.processPropertyElementRunSpace = processPropertyElementRunSpace;
        this.AssociatedObjectRunSpace(associatedObjectRunSpace);
        this.contextObjectRunSpace = contextObjectRunSpace;
        this.timeNow = this.ParentObjectRunSpace.getSomeRun().TimeNow();
        for (AbsBaseTrace absBaseTrace : this.absBaseTraces) {
            absBaseTrace.InitializeState();
        }
        this.tableRowReferences.clear();
        this.tableRowReferences.method_1(tableRowReferences);
        this.ReturnTable().DoubleValue(this.processPropertyElementRunSpace.ReturnTable().DoubleValue());
    }

    public DoubleTable ReturnTable() {
        return (DoubleTable) this.absBaseTraces[0];
    }

    public void addEnum70One(boolean add) {
        this.modifyEnum70(TokenRunSpace.Enum70.One, add);

    }

    private void modifyEnum70(Enum70 enum70, boolean add) {
        if (add) {
            this.enum70.add(enum70);
            return;
        }
        this.enum70.remove(enum70);
    }

    public boolean isEnum70One() {
        return this.containedEnum70(EnumSet.of(TokenRunSpace.Enum70.One));
    }

    private boolean containedEnum70(EnumSet<Enum70> enum70s) {
        return enum70s.containsAll(this.enum70);
    }

    public void method_17(TokenRunSpace tokenRunSpace) {
        if (this.AssociatedObjectRunSpace() == tokenRunSpace.AssociatedObjectRunSpace()) {
            this.addEnum70_16(tokenRunSpace.isEnum70_16());
        }
        this.addEnum70One(tokenRunSpace.isEnum70One());
        this.ReturnTable().method_3(tokenRunSpace.ReturnTable());
        if (this.myElementInstance.assignerDefinition == tokenRunSpace.myElementInstance.assignerDefinition) {
            for (int i = 1; i < this.absBaseTraces.length; i++) {
                this.absBaseTraces[i].method_3(tokenRunSpace.absBaseTraces[i]);
            }
        }
    }

    private void addEnum70_16(boolean add) {
        this.modifyEnum70(Enum70.Four, add);
    }

    public boolean isEnum70_16() {
        return this.containedEnum70(EnumSet.of(Enum70.Four));
    }

    public enum Enum70 {
        Zero, // 1
        One, // 2
        Two, // 4
        Three, // 8
        Four  // 16
    }
}
