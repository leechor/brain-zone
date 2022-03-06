package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.TableRowReferences;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.element.Network;
import com.zdpx.cctpp.enu.Enum42;
import com.zdpx.cctpp.enu.ObjectCreationReason;
import com.zdpx.cctpp.enu.StatisticsDataSourceInfo;
import com.zdpx.cctpp.utils.simu.IAboutReport;
import com.zdpx.cctpp.utils.simu.IAssociatedObjectRunSpace;
import com.zdpx.cctpp.utils.simu.ICost;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 *
 */
public class IntelligentObjectRunSpace extends AbsBaseRunSpace implements IAssociatedObjectRunSpace, ICost {


    public AbsBaseRunSpace[] AbsBaseRunSpaces;
    public NodeRunSpace[] NodeRunSpaces;
    public NodeRunSpace[] NodeRunSpaces0;
    public IntelligentObjectRunSpace[] IntelligentObjectRunSpaces;
    public TablesStatesWrapper[] tablesStatesWrappers;
    public ProcessPropertyElementRunSpace[] ProcessPropertyElementRunSpaces;
    private int id;
    private int origin_id;
    private double timeCreated;
    private ResourceLogic resourceLogic;
    private final List<ResourceAllocate> resourceAllocates = new ArrayList<>(2);
    protected List<TokenRunSpace> tokenRunSpaces = new ArrayList<>(4);

    private ProcessPropertyElementRunSpace OnRunInitializedProcessProperty;
    private ProcessPropertyElementRunSpace OnCreatedProcessProperty;
    private ProcessPropertyElementRunSpace OnDestroyingProcessProperty;
    private ProcessPropertyElementRunSpace OnCapacityAllocatedProcessProperty;
    private ProcessPropertyElementRunSpace OnCapacityReleasedProcessProperty;
    private ProcessPropertyElementRunSpace OnEvaluatingSeizeRequestProcessProperty;
    private ProcessPropertyElementRunSpace OnCapacityChangedProcessProperty;
    private ProcessPropertyElementRunSpace OnRunEndingProcessProperty;
    private ProcessPropertyElementRunSpace OnRunWarmUpEndingProcessProperty;
    private IntelligentObjectRunSpace.Enum10 enum10;

    public IntelligentObjectRunSpace(IntelligentObjectRunSpace parent,
                                     MayApplication application, IntelligentObject intelligentObject) {
        super(parent, application, intelligentObject);
    }


    public double GetNetworkDistanceToNode(NodeRunSpace nodeRunSpace) {
        if (nodeRunSpace == null) {
            return Double.NaN;
        }
        return this.getDistanceTo(nodeRunSpace);
    }

    public double getDistanceTo(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (intelligentObjectRunSpace == null) {
            return Double.NaN;
        }
        if (this.ObjectRunSpaceUsedAsMyLogicalLocation().CurrentFacilityRunSpace() != intelligentObjectRunSpace.ObjectRunSpaceUsedAsMyLogicalLocation().CurrentFacilityRunSpace()) {
            return Double.NaN;
        }
        return this.getDistanceTo(intelligentObjectRunSpace.Location());
    }

    private double getDistanceTo(Location location) {
        return (this.Location().sub(location)).distance();
    }

    public Location Location() {
        if (super.parentEmpty()) {
            return Location.EmptyLocation;
        }
        if (this.ObjectRunSpaceUsedAsMyLogicalLocation() == this) {
            return ((IntelligentObject) this.myElementInstance).location;
        }
        return this.ObjectRunSpaceUsedAsMyLogicalLocation().Location();
    }


    public IntelligentObjectRunSpace CurrentFacilityRunSpace() {
        return this.ParentObjectRunSpace();
    }

    public boolean SupportsFacilityView() {
        return false;
    }

    private IntelligentObjectRunSpace ObjectRunSpaceUsedAsMyLogicalLocation() {
        return this;
    }

    @Override
    public IntelligentObjectRunSpace ContextObjectRunSpace() {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return null;
    }

    @Override
    public IntelligentObjectRunSpace ParentObjectRunSpace() {
        return null;
    }

    @Override
    public AbsIntelligentPropertyObject MyElementInstance() {
        return null;
    }

    @Override
    public TableRowReferences GetTableRowReferences() {
        return null;
    }

    @Override
    public boolean TraceFlag() {
        return false;
    }

    @Override
    public void runtimeErrorHandler(IRunSpace param0, AbsPropertyObject param1, IntelligentObjectProperty param2,
                                    String param3) {

    }

    @Override
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2,
                                    IntelligentObjectProperty param3, String param4) {

    }

    @Override
    public Cost getCost() {
        return null;
    }

    @Override
    public ICost getParentCostCenter() {
        return null;
    }

    @Override
    public void UpdateCostStateAndRate(double cost, double rate) {

    }

    @Override
    public double GetEffectiveCostRate() {
        return 0;
    }

    @Override
    public StatisticsDataSourceInfo GetStatisticsDataSourceInfo(String param0) {
        return null;
    }

    @Override
    public void Initialize(boolean initializeState, boolean initializeStatistics) {
        super.Initialize(initializeState, initializeStatistics);
        // TODO: 2022/1/13 
    }

    public AbsBaseRunSpace[] getAbsBaseStatisticsDataSources() {
        return AbsBaseRunSpaces;
    }

    public void setAbsBaseStatisticsDataSources(AbsBaseRunSpace[] absBaseRunSpaces) {
        this.AbsBaseRunSpaces = absBaseRunSpaces;
    }

    public boolean IsDestroyable() {
        return false;
    }

    public void injectRunSpace(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        IntelligentObjectDefinition intelligentObjectDefinition =
                (IntelligentObjectDefinition) (this.myElementInstance.objectDefinition);
        if (absIntelligentPropertyObject.Parent() == intelligentObjectDefinition) {
            absIntelligentPropertyObject.AddNewRunSpace(this);
            return;
        }
        for (IntelligentObjectRunSpace intelligentObjectRunSpace : this.getChildObjectRunSpaces()) {
            intelligentObjectRunSpace.injectRunSpace(absIntelligentPropertyObject);
        }
    }

    private IntelligentObjectRunSpace[] getChildObjectRunSpaces() {
        return null;
    }

    public SizeTableTrace getSizeTableTrace() {
        return (SizeTableTrace) this.absBaseTraces[1];
    }

    public double Length() {
        return this.GetSize(this.getSizeTableTrace(), SizeType.Length);
    }

    public void Length(double value) {
        this.getSizeTableTrace().setLength(value);
    }

    public double Width() {
        return this.GetSize(this.getSizeTableTrace(), SizeType.Width);
    }

    public void Width(double value) {
        this.getSizeTableTrace().setWidth(value);
    }


    public double Height() {
        return this.GetSize(this.getSizeTableTrace(), SizeType.Height);
    }

    public void Height(double value) {
        this.getSizeTableTrace().setHeight(value);
    }

    double GetSize(SizeTableTrace sizeTableTrace, IntelligentObjectRunSpace.SizeType sizeType) {
        switch (sizeType) {
            case Length:
                return sizeTableTrace.getLength();
            case Width:
                return sizeTableTrace.getWidth();
            case Height:
                return sizeTableTrace.getHeight();
            default:
                throw new IllegalArgumentException();
        }
    }

    public int ID() {
        return this.id;
    }

    public void ID(int value) {
        this.id = value;
        if (this.origin_id == 0) {
            this.origin_id = value;
        }
    }

    public double getTimeCreated() {
        return this.timeCreated;
    }

    public ResourceLogic getRunSpaceWrapper() {
        return this.resourceLogic;
    }

    public double getAllocatedTo(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.getAllocatedTo(intelligentObjectRunSpace.ID());
    }

    public double getAllocatedTo(int idOwner) {
        if (this.resourceAllocates != null) {
            double num = 0.0;
            for (ResourceAllocate resourceAllocate : this.resourceAllocates) {
                if (resourceAllocate.getOwner().ID() == idOwner) {
                    num += resourceAllocate.getResourceAllocated();
                }
            }
            return num;
        }
        return 0.0;
    }

    public List<ResourceAllocate> SeizedResourcesNumberItems() {
        return this.resourceAllocates;
    }

    public int SeizedResourcesIndexOfItem(IntelligentObjectRunSpace owner) {
        int result = -1;
        int num = 0;
        for (ResourceAllocate resourceAllocate : this.resourceAllocates) {
            if (resourceAllocate.getOwner() == owner) {
                result = num;
                break;
            }
            num++;
        }
        return result;
    }

    public IntelligentObjectRunSpace CurrentLocationParent() {
        return this.ParentObjectRunSpace;
    }

    public double DirectDistanceToLocation(Location location) {
        return (this.Location().sub(location)).distance();
    }

    public IntelligentObjectRunSpace CurrentParentObjectRunSpace() {
        return this.ParentObjectRunSpace;
    }

    @SuppressWarnings("unchecked")
    public BaseQueueGridItemTrace<Allocation> AllocationQueue() {
        return (BaseQueueGridItemTrace<Allocation>) this.absBaseTraces[2];
    }

    protected boolean method_27() {
        // TODO: 2021/12/1
        return false;
    }

    public void updateAssociateObject(TokenRunSpace associate) {
        if (associate.getIndex() == this.tokenRunSpaces.size() - 1) {
            this.tokenRunSpaces.remove(associate.getIndex());
        } else if (associate.getIndex() >= 0) {
            TokenRunSpace tokenRunSpace = this.tokenRunSpaces.get(this.tokenRunSpaces.size() - 1);
            tokenRunSpace.setIndex(associate.getIndex());
            this.tokenRunSpaces.remove(this.tokenRunSpaces.size() - 1);
            this.tokenRunSpaces.set(tokenRunSpace.getIndex(), tokenRunSpace);
        }
        associate.setIndex(-1);
    }

    public void setAssociatedObject(TokenRunSpace tokenRunSpace) {
        tokenRunSpace.setIndex(this.tokenRunSpaces.size());
        this.tokenRunSpaces.add(tokenRunSpace);
    }

    public void RemoveChildrenExistingRunSpace(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        IntelligentObjectDefinition intelligentObjectDefinition =
                (IntelligentObjectDefinition) this.myElementInstance.objectDefinition;
        if (absIntelligentPropertyObject.Parent() == intelligentObjectDefinition) {
            absIntelligentPropertyObject.RemoveExistingRunSpace(this);
            return;
        }
        for (IntelligentObjectRunSpace intelligentObjectRunSpace : this.getChildObjectRunSpaces()) {
            intelligentObjectRunSpace.RemoveChildrenExistingRunSpace(absIntelligentPropertyObject);
        }
    }

    public void initLinkRunSpace(Link link) {
        IntelligentObjectDefinition objectDefinition =
                (IntelligentObjectDefinition) this.myElementInstance.objectDefinition;
        if (link.Parent() == objectDefinition) {
            LinkRunSpace runSpace = (LinkRunSpace) link.GetRunSpaceRecursionOutOfParent(this);
            runSpace.Initialize(true, true);
        }

        for (IntelligentObjectRunSpace intelligentObjectRunSpace : this.getChildObjectRunSpaces()) {
            intelligentObjectRunSpace.initLinkRunSpace(link);
        }
    }

    public void method_66(Network network) {
        IntelligentObjectDefinition intelligentObjectDefinition =
                (IntelligentObjectDefinition) this.myElementInstance.objectDefinition;
        if (network.Parent() == intelligentObjectDefinition) {
            NetworkElementRunSpace space = (NetworkElementRunSpace) network.GetRunSpaceRecursionOutOfParent(this);
            space.method_18();
            return;
        }
        for (IntelligentObjectRunSpace objectRunSpace : this.getChildObjectRunSpaces()) {
            objectRunSpace.method_66(network);
        }

    }

    protected int method_60() {
        return ((IntelligentObjectDefinition) this.myElementInstance.objectDefinition).getAboutLocationNum();
    }

    @Override
    public void GetReportedStatistics(IAboutReport aboutReport) {
        // TODO: 2022/2/6 
    }

    public void method_45() {
        this.Initialize(true, true);
        this.method_42(this::method_34, true, true);

    }

    public void method_34(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (super.TraceFlag()) {
            String param = MessageFormat.format(EngineResources.Trace_DynamicObjectCreated, this.Name());
            super.traceMethod(null, Enum42.Zero, param);
        }
        ProcessPropertyElementRunSpace.executeToken(this.OnCreatedProcessProperty, intelligentObjectRunSpace);
    }

    public boolean method_42(Action<IntelligentObjectRunSpace> interfaceProcess, boolean param1, boolean param2) {
        if (this.IsAgentPopulationStaticParent()) {
            AgentElementRunSpace agentElementRunSpace = (AgentElementRunSpace) this;
            for (var elementRunSpace : agentElementRunSpace.getPopulation().getAgentElementRunSpaces()) {
                if ((!param2 || elementRunSpace.CreationReason() == ObjectCreationReason.RunInitialization) && !elementRunSpace.method_42(interfaceProcess, param1, param2)) {
                    return false;
                }
            }
            return true;
        }
        if (!super.getMayApplication().method_3()) {
            return false;
        }
        if (!param1 || this.IsAgentPopulationMember()) {
            interfaceProcess.apply(this);
        }
        if (this.NodeRunSpaces0 != null) {
            for (NodeRunSpace nodeRunSpace : this.NodeRunSpaces0) {
                if (!nodeRunSpace.method_42(interfaceProcess, param1, param2)) {
                    return false;
                }
            }
        }
        if (this.IntelligentObjectRunSpaces != null) {
            for (IntelligentObjectRunSpace intelligentObjectRunSpace : this.IntelligentObjectRunSpaces) {
                if (intelligentObjectRunSpace != null && !intelligentObjectRunSpace.method_42(interfaceProcess,
                        param1, param2)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isContainedByEnum10One() {
        return this.isContainedByEnum10(EnumSet.of(IntelligentObjectRunSpace.Enum10.One));
    }

    private boolean isContainedByEnum10(EnumSet<Enum10> enum10) {
        return enum10.contains(this.enum10);
    }

    public void executeToken(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        ProcessPropertyElementRunSpace.executeToken(this.OnRunEndingProcessProperty, null, intelligentObjectRunSpace);
    }

    public enum SizeType {
        Length,
        Width,
        Height
    }

    public enum Enum10 {
        Zero,
        One;
    }
}
