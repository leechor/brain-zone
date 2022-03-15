package org.licho.brain.concrete;

import org.licho.brain.annotations.ElementFunctionReferenceReturnType;
import org.licho.brain.annotations.UnitClass;
import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.annotation.ElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.entity.EntityRunSpaceTravelerEdge;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.enu.EntityLocationType;
import org.licho.brain.concrete.enu.TravelerEdgeType;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.element.Network;
import org.licho.brain.enu.EntityInterfaceProcess;
import org.licho.brain.enu.IconIndex;
import org.licho.brain.enu.NetworkTurnaroundMethod;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.utils.simu.IEntityProcess;
import org.licho.brain.utils.simu.IVisitRequest;
import org.licho.brain.simioEnums.QueueRanking;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 */
public class EntityDefinition extends AgentDefinition {
    public static final String description = "No Network (Free Space)";
    public static final Guid guid = new Guid("{44DE6FB2-8CD3-479f-919E-C304FB8860DD}");
    public static String name = "Entity";
    public static final EntityDefinition EntityFacility = new EntityDefinition(null);

    static {// EntityDefinition
        InternalReference.put(EntityDefinition.name, EntityDefinition.EntityFacility);
    }

    protected EntityDefinition(String name, ActiveModel activeModel, Guid guid) {
        super(name, activeModel, guid);
        super.Description(EngineResources.Entity_Description);
    }

    private EntityDefinition(ActiveModel activeModel) {
        this(EntityDefinition.name, activeModel, EntityDefinition.guid);
    }

    public EntityDefinition(String name, ActiveModel activeModel,
                            IntelligentObjectDefinition baseClassDefinition) {
        super(name, activeModel, baseClassDefinition);
    }

    @Override
    public ObjectClass ObjectClass() {
        return ObjectClass.Entity;
    }

    public static String getDefaultInstanceName() {
        return EntityDefinition.name + "." + EngineResources.DefaultEntityInstanceName;
    }

    @Override
    protected void DefineSchema() {
        super.DefineSchema();

        ElementPropertyDefinition initialNetwork = new ElementPropertyDefinition("InitialNetwork", Network.class);
        initialNetwork.DisplayName(EngineResources.Entity_InitialNetwork_DisplayName);
        initialNetwork.Description(EngineResources.Entity_InitialNetwork_Description);
        initialNetwork.NullNullString(EntityDefinition.description);
        initialNetwork.DefaultString(NetworkDefinition.name);
        initialNetwork.CategoryName(EngineResources.CategoryName_TravelLogic);
        initialNetwork.RequiredValue(false);
        initialNetwork.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition networkTurnaroundMethod = new EnumPropertyDefinition("NetworkTurnaroundMethod",
                NetworkTurnaroundMethod.class);
        networkTurnaroundMethod.DisplayName(EngineResources.Entity_NetworkTurnaroundMethod_DisplayName);
        networkTurnaroundMethod.Description(EngineResources.Entity_NetworkTurnaroundMethod_Description);
        networkTurnaroundMethod.DefaultString("Exit & Re-enter");
        networkTurnaroundMethod.visibles = new boolean[]{
                false,
                true,
                true,
                true
        };
        networkTurnaroundMethod.stringValues = new String[]{
                "Default",
                "Exit & Re-enter",
                "Rotate In Place",
                "Reverse"
        };
        networkTurnaroundMethod.CategoryName(EngineResources.CategoryName_TravelLogic);
        networkTurnaroundMethod.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition initialPriority = new ExpressionPropertyDefinition("InitialPriority");
        initialPriority.DisplayName(EngineResources.Entity_InitialPriority_DisplayName);
        initialPriority.Description(EngineResources.Entity_InitialPriority_Description);
        initialPriority.CategoryName(EngineResources.CategoryName_RoutingLogic);
        initialPriority.DefaultString("1.0");
        initialPriority.ComplexityLevel(ProductComplexityLevel.Basic);

        SequencePropertyDefinition initialSequence = new SequencePropertyDefinition("InitialSequence");
        initialSequence.DisplayName(EngineResources.Entity_InitialSequence_DisplayName);
        initialSequence.Description(EngineResources.Entity_InitialSequence_Description);
        initialSequence.DefaultString("");
        initialSequence.CategoryName(EngineResources.CategoryName_RoutingLogic);
        initialSequence.RequiredValue(false);
        initialSequence.ComplexityLevel(ProductComplexityLevel.Basic);

        BooleanPropertyDefinition canTransferInAndOutOfObjects = new BooleanPropertyDefinition(
                "CanTransferInAndOutOfObjects");
        canTransferInAndOutOfObjects.DisplayName(EngineResources.Entity_CanTransferInAndOutOfObjects_DisplayName);
        canTransferInAndOutOfObjects.Description(EngineResources.Entity_CanTransferInAndOutOfObjects_Description);
        canTransferInAndOutOfObjects.DefaultString("True");
        canTransferInAndOutOfObjects.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        canTransferInAndOutOfObjects.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition dueDateExpression = new ExpressionPropertyDefinition("DueDateExpression");
        dueDateExpression.DisplayName(EngineResources.Entity_DueDateExpression_DisplayName);
        dueDateExpression.Description(EngineResources.Entity_DueDateExpression_Description);
        dueDateExpression.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        dueDateExpression.DefaultString("Infinity");
        dueDateExpression.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
        dueDateExpression.DefaultUnitSubType(0);
        dueDateExpression.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition ganttVisibilityExpression = new ExpressionPropertyDefinition(
                "GanttVisibilityExpression");
        ganttVisibilityExpression.DisplayName(EngineResources.Entity_GanttVisibilityExpression_DisplayName);
        ganttVisibilityExpression.Description(EngineResources.Entity_GanttVisibilityExpression_Description);
        ganttVisibilityExpression.DefaultString("True");
        ganttVisibilityExpression.CanBeDeleted(false);
        ganttVisibilityExpression.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        ganttVisibilityExpression.ComplexityLevel(ProductComplexityLevel.Advanced);

        super.getPropertyDefinitions().Add(initialNetwork);
        super.getPropertyDefinitions().Add(networkTurnaroundMethod);
        super.getPropertyDefinitions().Add(initialPriority);
        super.getPropertyDefinitions().Add(initialSequence);
        super.getPropertyDefinitions().Add(canTransferInAndOutOfObjects);
        super.getPropertyDefinitions().Add(dueDateExpression);
        super.getPropertyDefinitions().Add(ganttVisibilityExpression);

        BaseStatePropertyObject priority = new BaseStatePropertyObject("Priority", false, false);
        priority.value = 0.0;
        priority.Description(EngineResources.Entity_Priority_Description);

        CostStatePropertyObject cellRange = new CostStatePropertyObject("CellRange", true, true);
        cellRange.Description(EngineResources.Entity_CellRange_Description);

        QueueStateObject<IVisitRequest> visitRequestQueue = new QueueStateObject<>("VisitRequestQueue");
        visitRequestQueue.Description(EngineResources.Entity_VisitRequestQueue_Description);
        visitRequestQueue.queueRanking = QueueRanking.FirstInFirstOut;
        EntityObjectQueueState batchMembers = new EntityObjectQueueState("BatchMembers");

        batchMembers.Description(EngineResources.Entity_BatchMembers_Description);
        batchMembers.queueRanking = QueueRanking.FirstInFirstOut;

        VolumeUnitPropertyObject volume = new VolumeUnitPropertyObject("Volume", false, false);
        volume.value = 1.0;
        volume.Description(EngineResources.Entity_Volume_Description);

        WeightUnitPropertyObject weight = new WeightUnitPropertyObject("Weight", false, false);
        weight.value = 1.0;
        weight.Description(EngineResources.Entity_Weight_Description);

        super.getStateDefinitions().addStateProperty(priority);
        super.getStateDefinitions().addStateProperty(cellRange);
        super.getStateDefinitions().addStateProperty(visitRequestQueue);
        super.getStateDefinitions().addStateProperty(batchMembers);
        super.getStateDefinitions().addStateProperty(volume);
        super.getStateDefinitions().addStateProperty(weight);

        EventDefinition engaged = new EventDefinition("Engaged", false);
        engaged.Description(EngineResources.Entity_Engaged_Description);

        EventDefinition transferring = new EventDefinition("Transferring", false);
        transferring.Description(EngineResources.Entity_Transferring_Description);

        EventDefinition transferred = new EventDefinition("Transferred", false);
        transferred.Description(EngineResources.Entity_Transferred_Description);

        super.getEventDefinitions().InsertEventDefinition(engaged);
        super.getEventDefinitions().InsertEventDefinition(transferring);
        super.getEventDefinitions().InsertEventDefinition(transferred);

        super.createProcessProperties(ObjectClass.Entity);
    }

    @Override
    public IntelligentObjectDefinition CreateNewBaseClassDefinition() {
        return EntityDefinition.create();
    }


    public static EntityDefinition create() {
        return new EntityDefinition(null);
    }

    protected boolean IsImplicitlySubclassedFrom(IntelligentObjectDefinition intelligentObjectDefinition) {
        return super.IsImplicitlySubclassedFrom(intelligentObjectDefinition) || intelligentObjectDefinition.getGuid() == AgentDefinition.guid;

    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Entity(this, name, ElementScope.Private);
    }

    public void GetInterfaceProcessInformation(Collection<IEntityProcess> infoList) {
        super.GetInterfaceProcessInformation(infoList);
        List<Integer> list = new ArrayList<>();
        list.add(1);
        if (!super.ResourceLogic()) {
            list.add(2);
            list.add(3);
        }
        super.injectEntityProcessToObjectClass(infoList, EntityInterfaceProcess.class, ObjectClass.Entity, list);
    }

    @Override
    public IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.Nine;
    }

    @Override
    public BaseStatePropertyObject StateForBinding(String name) {
        if (name.equalsIgnoreCase("BatchQueue")) {
            return super.getStateDefinitions().StateProperties.values.get(10);
        }
        return super.StateForBinding(name);
    }

    @Override
    void DoInterfaceProcessNameFixup(IntelligentObjectXml intelligentObjectXml, StringBuffer oldName,
                                     StringBuffer newName) {
        if (intelligentObjectXml.FileVersion() < 19) {
            if (newName.equals("Entity.OnEvaluatingVisitRequest")) {
                oldName.append("OnEvaluatingMoveRequest");
                newName.append("Entity.OnEvaluatingMoveRequest");
                return;
            }
            if (newName.equals("Entity.OnVisitRequestAccepted")) {
                oldName.append("OnMoveRequestAccepted");
                newName.append("Entity.OnMoveRequestAccepted");
                return;
            }
        }
        super.DoInterfaceProcessNameFixup(intelligentObjectXml, oldName, newName);
    }

    @Override
    public String GetPropertyValueFixup(IntelligentObjectXml intelligentObjectXml, String s) {
        if (intelligentObjectXml.FileVersion() < 19) {
            s = s.replaceAll("(^|[^\\w_])(?i:OnEvaluatingVisitRequest)([^\\w_]|$)", "$1OnEvaluatingMoveRequest$2");
            s = s.replaceAll("(^|[^\\w_])(?i:OnVisitRequestAccepted)([^\\w_]|$)", "$1OnMoveRequestAccepted$2");
        }
        return super.GetPropertyValueFixup(intelligentObjectXml, s);
    }

    @Override
    public StringPropertyDefinition GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 31 && name.equals("CanEnterObjects")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(
                    "CanTransferInAndOutOfObjects");
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }

    @BaseElementFunction("CurrentNode")
    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    public static ExpressionValue CurrentNode(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.CurrentNode());
    }

    @BaseElementFunction("PreviousNode")
    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    public static ExpressionValue PreviousNode(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.getPreviousNode());
    }

    @BaseElementFunction("DestinationNode")
    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    public static ExpressionValue DestinationNode(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.DestinationNode());
    }

    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    @BaseElementFunction("CurrentLink")
    public static ExpressionValue CurrentLink(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getLeadingEdge() != null) {
            return ExpressionValue.from(entityRunSpace.currentLink());
        }
        return null;
    }

    @BaseElementFunction("TrailingLink")
    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    public static ExpressionValue TrailingLink(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getTailingTravelEdge() != null) {
            return ExpressionValue.from(entityRunSpace.trailingLink());
        }
        return null;
    }

    @BaseElementFunction("CurrentConnectorLink")
    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    public static ExpressionValue CurrentConnectorLink(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.getCurrentConnectorLink());
    }

    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    @BaseElementFunction("LastLinkUsed")
    public static ExpressionValue LastLinkUsed(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.getLastLinkUsed());
    }

    @ElementFunctionReferenceReturnType(EntityDefinition.class)
    @BaseElementFunction("NextEntityAheadOnLink")
    public static ExpressionValue NextEntityAheadOnLink(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.getNextEntityAheadOnLink());
    }

    @ElementFunctionReferenceReturnType(EntityDefinition.class)
    @BaseElementFunction("NextEntityBehindOnLink")
    public static ExpressionValue NextEntityBehindOnLink(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.getNextEntityBehindOnLink());
    }

    @ElementFunctionReferenceReturnType(StationDefinition.class)
    @BaseElementFunction("CurrentStation")
    public static ExpressionValue CurrentStation(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.CurrentStation());
    }

    @BaseElementFunction("CurrentNetwork")
    @ElementFunctionReferenceReturnType(NetworkDefinition.class)
    public static ExpressionValue CurrentNetwork(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.CurrentNetwork());
    }

    @BaseElementFunction("IsParked")
    public static double IsParked(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.IsParked()) {
            return 1.0;
        }
        return 0.0;
    }

    @BaseElementFunction("IsBatchMember")
    public static double IsBatchMember(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() == EntityLocationType.BatchMembersQueue) {
            return 1.0;
        }
        return 0.0;
    }

    @ElementFunctionReferenceReturnType(EntityDefinition.class)
    @BaseElementFunction("BatchParent")
    public static ExpressionValue BatchParent(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() == EntityLocationType.BatchMembersQueue) {
            return ExpressionValue.from(entityRunSpace.CurrentParentObjectRunSpace);
        }
        return null;
    }

    @BaseElementFunction("IsWaitingRide")
    public static double IsWaitingRide(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.IsWaitingRide()) {
            return 1.0;
        }
        return 0.0;
    }

    @BaseElementFunction("ReservedTransporter")
    @ElementFunctionReferenceReturnType(TransporterDefinition.class)
    public static ExpressionValue ReservedTransporter(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return ExpressionValue.from(entityRunSpace.ReservedTransporter());
    }

    @BaseElementFunction("IsRiding")
    public static double IsRiding(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.IsRiding()) {
            return 1.0;
        }
        return 0.0;
    }

    @BaseElementFunction("CurrentTransporter")
    @ElementFunctionReferenceReturnType(TransporterDefinition.class)
    public static ExpressionValue CurrentTransporter(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.IsRiding()) {
            return ExpressionValue.from(entityRunSpace.CurrentStation().ParentObjectRunSpace);
        }
        return null;
    }

    @ElementFunctionReferenceReturnType(OperationDefinition.class)
    @BaseElementFunction("CurrentOperation")
    public static ExpressionValue CurrentOperation(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        ProductionLot productionLot = entityRunSpace.getProductionLot();
        if (productionLot != null) {
            return ExpressionValue.from(productionLot.CurrentOperation());
        }
        return null;
    }

    @BaseElementFunction("CurrentActivity")
    @ElementFunctionReferenceReturnType(ActivityDefinition.class)
    public static ExpressionValue CurrentActivity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        ProductionLot productionLot = entityRunSpace.getProductionLot();
        if (productionLot != null && productionLot.getCurrentActivitySequence() > 0) {
            return ExpressionValue.from(
                    productionLot.CurrentOperation().getActivityElementRunSpaces().
                            get(productionLot.getCurrentActivitySequence() - 1));
        }
        return null;
    }

    @BaseElementFunction("Sequence.CurrentJobStep")
    public static double Sequence_CurrentJobStep(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return (double) entityRunSpace.getSequenceCurrentJobStepIndex() + 1.0;
    }

    @BaseElementFunction("Sequence.NumberJobSteps")
    public static double Sequence_NumberJobSteps(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return (double) entityRunSpace.tableRowReferences.getCount(entityRunSpace.tableRowReferences.getTable(), null);

    }

    @BaseElementFunction("Sequence.NumberJobStepsRemaining")
    public static double Sequence_NumberJobStepsRemaining(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return (double) (entityRunSpace.tableRowReferences.getCount(entityRunSpace.tableRowReferences.getTable(),
                null) - (Math.max(entityRunSpace.getSequenceCurrentJobStepIndex(), 0)));

    }

    @BaseElementFunction("Sequence.ExpectedOperationTimeRemaining")
    public static double Sequence_ExpectedOperationTimeRemaining(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return entityRunSpace.getSequenceStep().getTimeRemaining();
    }

    @BaseElementFunction("Sequence.DestinationNodes")
    public static double Sequence_DestinationNodes(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.Sequence_NumberJobSteps(absBaseRunSpace, runSpace);
    }

    @BaseElementFunction("Sequence.DestinationNodes.NumberItems")
    public static double Sequence_DestinationNodes_NumberItems(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.Sequence_NumberJobSteps(absBaseRunSpace, runSpace);
    }

    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    @BaseElementFunction("Sequence.DestinationNodes.FirstItem")
    public static ExpressionValue Sequence_DestinationNodes_FirstItem(AbsBaseRunSpace absBaseRunSpace,
                                                                      IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.tableRowReferences.getTable() == null) {
            return null;
        }
        return ExpressionValue.from(entityRunSpace.getAssignedSequence(0));
    }

    @BaseElementFunction("Sequence.DestinationNodes.LastItem")
    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    public static ExpressionValue Sequence_DestinationNodes_LastItem(AbsBaseRunSpace absBaseRunSpace,
                                                                     IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.tableRowReferences.getTable() == null) {
            return null;
        }
        int num = entityRunSpace.tableRowReferences.getCount(entityRunSpace.tableRowReferences.getTable(), null);
        return ExpressionValue.from(entityRunSpace.getAssignedSequence(num - 1));
    }

    @BaseElementFunction(value = "Sequence.DestinationNodes.ItemAtIndex", Arguments = {"index"})
    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    public static ExpressionValue Sequence_DestinationNodes_ItemAtIndex(AbsBaseRunSpace absBaseRunSpace,
                                                                        IRunSpace runSpace, ExpressionValue[] values) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        int num;
        try {
            num = values[0].toInt();
        } catch (Exception ignored) {
            throw new NumberFormatException(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    entityRunSpace.Name(), "Sequence.DestinationNodes.ItemAtIndex"));
        }
        if (entityRunSpace.tableRowReferences.getTable() == null) {
            return null;
        }
        return ExpressionValue.from(entityRunSpace.getAssignedSequence(num - 1));
    }

    @BaseElementFunction(value = "Sequence.DestinationNodes.IndexOfItem", Arguments = {"node"})
    public static double Sequence_DestinationNodes_IndexOfItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                               ExpressionValue[] values) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        NodeRunSpace nodeRunSpace = null;
        try {
            nodeRunSpace = (NodeRunSpace) (values[0].getAbsBaseRunSpace());
        } catch (Exception ignored) {
        }
        if (nodeRunSpace == null) {
            throw new IllegalArgumentException(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    entityRunSpace.Name(), "Sequence.DestinationNodes.IndexOfItem"));
        }
        if (entityRunSpace.tableRowReferences.getTable() != null) {
            int num = entityRunSpace.tableRowReferences.getCount(entityRunSpace.tableRowReferences.getTable(), null);
            for (int i = 0; i < num; i++) {
                if (entityRunSpace.getAssignedSequence(i) == nodeRunSpace) {
                    return (double) (i + 1);
                }
            }
            return 0.0;
        }
        return 0.0;
    }

    @BaseElementFunction(value = "Sequence.DestinationNodes.Contains", Arguments = "node")
    public static double Sequence_DestinationNodes_Contains(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                            ExpressionValue[] values) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        NodeRunSpace nodeRunSpace = null;
        try {
            nodeRunSpace = (NodeRunSpace) (values[0].getAbsBaseRunSpace());
        } catch (Exception ignore) {
        }
        if (nodeRunSpace == null) {
            throw new IllegalArgumentException(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    entityRunSpace.Name(), "Sequence.DestinationNodes.Contains"));
        }
        if (entityRunSpace.tableRowReferences.getTable() != null) {
            int num = entityRunSpace.tableRowReferences.getCount(entityRunSpace.tableRowReferences.getTable(), null);
            for (int i = 0; i < num; i++) {
                if (entityRunSpace.getAssignedSequence(i) == nodeRunSpace) {
                    return 1.0;
                }
            }
            return 0.0;
        }
        return 0.0;
    }

    @BaseElementFunction("Sequence.DueDate")
    public static double Sequence_DueDate(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return entityRunSpace.getSequenceDueDate();
    }

    @BaseElementFunction("Sequence.ModifiedDueDate")
    public static double Sequence_ModifiedDueDate(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return Math.max(entityRunSpace.getSequenceDueDate(),
                entityRunSpace.getSomeRun().TimeNow() + entityRunSpace.getSequenceStep().getTimeRemaining());
    }

    @BaseElementFunction("Sequence.CriticalRatio")
    public static double Sequence_CriticalRatio(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return (entityRunSpace.getSequenceDueDate() - entityRunSpace.getSomeRun().TimeNow()) / Math.max(entityRunSpace.getSequenceStep().getTimeRemaining(), Double.MIN_VALUE);
    }

    @BaseElementFunction("Sequence.SlackTime")
    public static double Sequence_SlackTime(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return entityRunSpace.getSequenceDueDate() - entityRunSpace.getSomeRun().TimeNow() - entityRunSpace.getSequenceStep().getTimeRemaining();
    }

    @BaseElementFunction("Sequence.SlackTimePerOperation")
    public static double Sequence_SlackTimePerOperation(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        double num =
                entityRunSpace.getSequenceDueDate() - entityRunSpace.getSomeRun().TimeNow() - entityRunSpace.getSequenceStep().getTimeRemaining();
        int num2 =
                entityRunSpace.tableRowReferences.getCount(entityRunSpace.tableRowReferences.getTable(), null) - ((entityRunSpace.getSequenceCurrentJobStepIndex() <= 0) ?
                        0 : entityRunSpace.getSequenceCurrentJobStepIndex());
        return num / Math.max((double) num2, Double.MIN_VALUE);
    }

    @UnitClass(UnitType.Length)
    @BaseElementFunction(value = "NetworkDistanceTo.Node", Arguments = {"node"})
    public static double NetworkDistanceTo_Node(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                ExpressionValue[] values) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        NodeRunSpace nodeRunSpace = null;
        try {
            nodeRunSpace = (NodeRunSpace) (values[0].getAbsBaseRunSpace());
        } catch (Exception ignore) {
        }
        if (nodeRunSpace == null) {
            throw new IllegalArgumentException(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    entityRunSpace.Name(), "NetworkDistanceTo.Node"));
        }
        return entityRunSpace.GetNetworkDistanceToNode(nodeRunSpace);
    }

    @BaseElementFunction("NetworkDistanceTo.NextEntityAheadOnLink")
    @UnitClass(UnitType.Length)
    public static double NetworkDistanceTo_NextEntityAheadOnLink(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        double num = entityRunSpace.getNetworkDistanceToNextEntityAheadOnLink();
        if (entityRunSpace.getMayApplication().isChangeNaNToZeroInExpressions() && Double.isNaN(num)) {
            num = Double.MAX_VALUE;
        }
        return num;
    }

    @UnitClass(UnitType.Length)
    @BaseElementFunction("NetworkDistanceTo.NextEntityBehindOnLink")
    public static double NetworkDistanceTo_NextEntityBehindOnLink(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        double num = entityRunSpace.getNetworkDistanceToNextEntityAheadOnLink();
        if (entityRunSpace.getMayApplication().isChangeNaNToZeroInExpressions() && Double.isNaN(num)) {
            num = Double.MAX_VALUE;
        }
        return num;
    }

    @UnitClass(UnitType.TravelRate)
    @ElementFunction("SlipSpeed")
    public static double SlipSpeed(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() != EntityLocationType.Link) {
            return 0.0;
        }
        Queue<EntityRunSpaceTravelerEdge> aheadQueue = entityRunSpace.getLeadingEdge().getAheadQueue();
        if (aheadQueue != null) {
            EntityRunSpace space = aheadQueue.current().getEntityRunSpace();
            return Math.min(entityRunSpace.currentLink().Movement().RateValue(), space.Movement().RateValue());
        }
        return entityRunSpace.currentLink().Movement().RateValue();
    }

    @ElementFunction("FrontTraffic")
    public static double FrontTraffic(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() == EntityLocationType.Link && entityRunSpace.getLeadingEdge().getAheadQueue() != null) {
            return 1.0;
        }
        return 0.0;
    }

    @ElementFunction("FrontTraffic.Speed")
    @UnitClass(UnitType.TravelRate)
    public static double FrontTraffic_Speed(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() != EntityLocationType.Link) {
            return 0.0;
        }
        Queue<EntityRunSpaceTravelerEdge> aheadQueue = entityRunSpace.getLeadingEdge().getAheadQueue();
        if (aheadQueue != null) {
            return aheadQueue.current().getEntityRunSpace().Movement().RateValue();
        }
        return entityRunSpace.DoubleTable().DoubleValue();
    }

    @ElementFunction("FrontTraffic.Distance")
    @UnitClass(UnitType.Length)
    public static double FrontTraffic_Distance(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() != EntityLocationType.Link) {
            return 0.0;
        }
        Queue<EntityRunSpaceTravelerEdge> aheadQueue = entityRunSpace.getLeadingEdge().getAheadQueue();
        if (aheadQueue == null) {
            return entityRunSpace.currentLink().Length() - entityRunSpace.Movement().method_26();
        }
        if (aheadQueue.current().getTravelerEdgeType() == TravelerEdgeType.LeadingEdge) {
            return 0.0;
        }
        EntityRunSpace space = aheadQueue.current().getEntityRunSpace();
        return space.Movement().method_26() - space.Length() - entityRunSpace.Movement().method_26();
    }

    @ElementFunction("FrontTraffic.ID")
    public static double FrontTraffic_ID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() != EntityLocationType.Link) {
            return 0.0;
        }
        Queue<EntityRunSpaceTravelerEdge> aheadQueue = entityRunSpace.getLeadingEdge().getAheadQueue();
        if (aheadQueue != null) {
            return (double) aheadQueue.current().getEntityRunSpace().ID();
        }
        return 0.0;
    }

    @ElementFunction("NumberOccupiedLinks")
    public static double NumberOccupiedLinks(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return (double) entityRunSpace.occupiedLinks.size();
    }

    @ElementFunction("NumberOccupiedNodes")
    public static double NumberOccupiedNodes(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        return (double) entityRunSpace.occupiedNodes.size();
    }

    @ElementFunction("RideID")
    public static double RideID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.ReservedTransporter() != null) {
            return (double) entityRunSpace.ReservedTransporter().ID();
        }
        if (entityRunSpace.IsRiding()) {
            return (double) entityRunSpace.CurrentStation().ParentObjectRunSpace.ID();
        }
        return 0.0;
    }

    @ElementFunction("BatchParentID")
    public static double BatchParentID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.getEntityLocationType() == EntityLocationType.BatchMembersQueue) {
            return (double) entityRunSpace.CurrentParentObjectRunSpace.ID();
        }
        return 0.0;
    }

    @ElementFunction("DestinationNodeID")
    public static double DestinationNodeID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.DestinationNode() != null) {
            return (double) entityRunSpace.DestinationNode().ID();
        }
        return 0.0;
    }

    @ElementFunction("CurrentNodeID")
    public static double CurrentNodeID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.CurrentNode() != null) {
            return (double) entityRunSpace.CurrentNode().ID();
        }
        return 0.0;
    }

    @ElementFunction("CurrentLinkID")
    public static double CurrentLinkID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        if (entityRunSpace.currentLink() != null) {
            return (double) entityRunSpace.currentLink().ID();
        }
        return 0.0;
    }

    @ElementFunction("FrontTrafficSpeed")
    public static double FrontTrafficSpeed(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.FrontTraffic_Speed(absBaseRunSpace, runSpace);
    }

    @ElementFunction("FrontTrafficDistance")
    public static double FrontTrafficDistance(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.FrontTraffic_Distance(absBaseRunSpace, runSpace);
    }

    @ElementFunction("HasFrontTraffic")
    public static double HasFrontTraffic(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.FrontTraffic(absBaseRunSpace, runSpace);
    }

    @ElementFunction("Operation.Activity.BatchIndex")
    public static double Operation_Activity_BatchIndex(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.Operation_Activity_BatchNumber(absBaseRunSpace, runSpace);
    }

    @ElementFunction("Operation.Activity.BatchCount")
    public static double Operation_Activity_BatchCount(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.Operation_Activity_NumberBatchesRequired(absBaseRunSpace, runSpace);
    }

    @ElementFunction("Operation.Activity.BatchTime")
    @UnitClass(UnitType.Time)
    public static double Operation_Activity_BatchTime(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        ProductionLot productionLot = entityRunSpace.getProductionLot();
        if (productionLot == null || productionLot.getCurrentActivitySequence() == 0) {
            return 0.0;
        }
        OperationActivity operationActivity =
                productionLot.getOperationActivity(productionLot.getCurrentActivitySequence());
        return operationActivity.getBatchTime();
    }

    @ElementFunction("Operation.Activity.BatchSize")
    public static double Operation_Activity_BatchSize(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        ProductionLot productionLot = entityRunSpace.getProductionLot();
        if (productionLot == null || productionLot.getCurrentActivitySequence() == 0) {
            return 0.0;
        }
        OperationActivity operationActivity =
                productionLot.getOperationActivity(productionLot.getCurrentActivitySequence());
        return (double) operationActivity.getBatchSize();
    }

    @ElementFunction("Operation.Activity.BatchNumber")
    public static double Operation_Activity_BatchNumber(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        ProductionLot productionLot = entityRunSpace.getProductionLot();
        if (productionLot == null || productionLot.getCurrentActivitySequence() == 0) {
            return 0.0;
        }
        OperationActivity operationActivity =
                productionLot.getOperationActivity(productionLot.getCurrentActivitySequence());
        return (double) operationActivity.getBatchNumber();
    }

    @ElementFunction("Operation.Activity.NumberBatchesRequired")
    public static double Operation_Activity_NumberBatchesRequired(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        ProductionLot productionLot = entityRunSpace.getProductionLot();
        if (productionLot == null || productionLot.getCurrentActivitySequence() == 0) {
            return 0.0;
        }
        OperationActivity operationActivity =
                productionLot.getOperationActivity(productionLot.getCurrentActivitySequence());
        return (double) operationActivity.getNumberBatchesRequired();
    }

    @ElementFunction("Operation.Quantity")
    public static double Operation_Quantity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        EntityRunSpace entityRunSpace = (EntityRunSpace) absBaseRunSpace;
        ProductionLot productionLot = entityRunSpace.getProductionLot();
        if (productionLot == null) {
            return 0.0;
        }
        return (double) productionLot.OperationQuantity();
    }

    @ElementFunction("SequenceTableRow")
    public static double SequenceTableRow(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.Sequence_CurrentJobStep(absBaseRunSpace, runSpace);
    }

    @ElementFunction("SequenceTableRowCount")
    public static double SequenceTableRowCount(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return EntityDefinition.Sequence_NumberJobSteps(absBaseRunSpace, runSpace);
    }

}
