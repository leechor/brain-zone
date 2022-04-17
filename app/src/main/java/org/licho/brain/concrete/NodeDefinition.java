package org.licho.brain.concrete;

import org.licho.brain.annotations.ElementFunctionReferenceReturnType;
import org.licho.brain.api.NodeShapeType;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainEnums.NodeOutputFlowStrategy;
import org.licho.brain.brainEnums.NodeSplitAllocationRule;
import org.licho.brain.brainEnums.QueueRanking;
import org.licho.brain.brainEnums.SwitchNumericConditions;
import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.annotation.ElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.exception.Exception0;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.node.LinkSelectionPreference;
import org.licho.brain.concrete.node.Node;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.element.Station;
import org.licho.brain.enu.IconIndex;
import org.licho.brain.enu.LinkSelectionRule;
import org.licho.brain.enu.NodeInterfaceProcess;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.utils.simu.IEntityProcess;
import org.licho.brain.utils.simu.system.Color;

import java.text.MessageFormat;
import java.util.Collection;


/**
 *
 */
public class NodeDefinition extends FixedDefinition {

    public static final Guid guid = new Guid("{26D12E69-5EEB-4220-89B2-9E6FC7331093}");
    public static final String name = "Node";
    public static final NodeDefinition NodeFacility = new NodeDefinition((ActiveModel) null);
    private static Color defaultColor = Color.FromArgb(128, 0, 0);
    private static NodeShapeType defaultNodeShapeType = NodeShapeType.Diamond;
    private Color color = NodeDefinition.defaultColor;
    private NodeShapeType nodeShapeType = NodeDefinition.defaultNodeShapeType;

    public NodeDefinition(String name) {
        super(name);
    }

    static {
        InternalReference.put(NodeDefinition.name, NodeDefinition.NodeFacility);
    }


    protected NodeDefinition(String name, ActiveModel activeModel, Guid guid) {
        super(name, activeModel, guid);
        super.Description(EngineResources.Node_Description);
        Station station = super.addElement(StationDefinition.create("ParkingStation"), ElementScope.Public);
        station.Description(EngineResources.Node_ParkingStation_Description);
        station.setBooleanPropertyStringValue(true);
    }


    private NodeDefinition(ActiveModel activeModel) {
        this(NodeDefinition.name, activeModel, NodeDefinition.guid);
    }

    public NodeDefinition(String name, ActiveModel activeModel, IntelligentObjectDefinition parent) {
        super(name, activeModel, parent);
    }


    @Override
    public void DefineSchema() {
        super.DefineSchema();
        ExpressionPropertyDefinition initialTravelerCapacity = new ExpressionPropertyDefinition(
                "InitialTravelerCapacity");
        initialTravelerCapacity.DisplayName(EngineResources.Node_InitialTravelerCapacity_DisplayName);
        initialTravelerCapacity.Description(EngineResources.Node_InitialTravelerCapacity_Description);
        initialTravelerCapacity.CategoryName(EngineResources.CategoryName_CrossingLogic);
        initialTravelerCapacity.DefaultString("Infinity");
        initialTravelerCapacity.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition entryRankingRule = new EnumPropertyDefinition("EntryRankingRule", QueueRanking.class);
        entryRankingRule.DisplayName(EngineResources.Node_EntryRankingRule_DisplayName);
        entryRankingRule.Description(EngineResources.Node_EntryRankingRule_Description);
        entryRankingRule.DefaultString("First In) First Out");
        entryRankingRule.stringValues = new String[]
                {
                        "First In First Out",
                        "Last In First Out",
                        "Smallest Value First",
                        "Largest Value First"
                };
        entryRankingRule.CategoryName(EngineResources.CategoryName_CrossingLogic);
        entryRankingRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition entryRankingExpression = new ExpressionPropertyDefinition(
                "EntryRankingExpression");
        entryRankingExpression.DisplayName(EngineResources.Node_EntryRankingExpression_DisplayName);
        entryRankingExpression.Description(EngineResources.Node_EntryRankingExpression_Description);
        entryRankingExpression.DefaultString("Entity.Priority");
        entryRankingExpression.SwitchNumericProperty(entryRankingRule);
        entryRankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        entryRankingExpression.SwitchNumericValue(1.0);
        entryRankingExpression.ParentPropertyDefinition(entryRankingRule);
        entryRankingExpression.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition entryExemptionCondition = new ExpressionPropertyDefinition(
                "EntryExemptionCondition");
        entryExemptionCondition.DisplayName(EngineResources.Node_EntryExemptionCondition_DisplayName);
        entryExemptionCondition.Description(EngineResources.Node_EntryExemptionCondition_Description);
        entryExemptionCondition.DefaultString("");
        entryExemptionCondition.RequiredValue(false);
        entryExemptionCondition.CategoryName(EngineResources.CategoryName_CrossingLogic);
        entryExemptionCondition.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition outputFlowControlMode = new EnumPropertyDefinition("OutputFlowControlMode",
                NodeOutputFlowStrategy.class);
        outputFlowControlMode.DisplayName(EngineResources.Node_OutputFlowControlMode_DisplayName);
        outputFlowControlMode.Description(EngineResources.Node_OutputFlowControlMode_Description);
        outputFlowControlMode.DefaultString("Single Flow (No Splitting)");
        outputFlowControlMode.stringValues = new String[]{"Single Flow (No Splitting)", "Split " +
                "Flow"};
        outputFlowControlMode.CategoryName(EngineResources.CategoryName_OutputFlowControl);
        outputFlowControlMode.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition flowSplitAllocationRule = new EnumPropertyDefinition("FlowSplitAllocationRule",
                NodeSplitAllocationRule.class);
        flowSplitAllocationRule.DisplayName(EngineResources.Node_FlowSplitAllocationRule_DisplayName);
        flowSplitAllocationRule.Description(EngineResources.Node_FlowSplitAllocationRule_Description);
        flowSplitAllocationRule.DefaultString("Evenly If Possible");
        flowSplitAllocationRule.stringValues = new String[]{
                "Evenly If Possible",
                "Preferred Order By Link Weight",
                "Proportional Based On Link Weights"
        };
        flowSplitAllocationRule.CategoryName(EngineResources.CategoryName_OutputFlowControl);
        flowSplitAllocationRule.SwitchNumericProperty(outputFlowControlMode);
        flowSplitAllocationRule.SwitchNumericCondition(SwitchNumericConditions.Equal);
        flowSplitAllocationRule.SwitchNumericValue(1.0);
        flowSplitAllocationRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition outboundLinkPreference = new EnumPropertyDefinition("OutboundLinkPreference",
                LinkSelectionPreference.class);
        outboundLinkPreference.DisplayName(EngineResources.Node_OutboundLinkPreference_DisplayName);
        outboundLinkPreference.Description(EngineResources.Node_OutboundLinkPreference_Description);
        outboundLinkPreference.DefaultString(LinkSelectionPreference.Any.toString());
        outboundLinkPreference.visibles = new boolean[]{
                false,
                true,
                true,
                true
        };
        outboundLinkPreference.CategoryName(EngineResources.CategoryName_RoutingLogic);
        outboundLinkPreference.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition outboundLinkRule = new EnumPropertyDefinition("OutboundLinkRule",
                LinkSelectionRule.class);
        outboundLinkRule.DisplayName(EngineResources.Node_OutboundLinkRule_DisplayName);
        outboundLinkRule.Description(EngineResources.Node_OutboundLinkRule_Description);
        outboundLinkRule.DefaultString("Shortest Path");
        outboundLinkRule.stringValues = new String[]{
                "Shortest Path",
                "By Link Weight"
        };
        outboundLinkRule.CategoryName(EngineResources.CategoryName_RoutingLogic);
        outboundLinkRule.SwitchNumericProperty(outboundLinkPreference);
        outboundLinkRule.SwitchNumericCondition(SwitchNumericConditions.Equal);
        outboundLinkRule.setException(String.join(",", new String[]{
                LinkSelectionPreference.Default.name(),
                LinkSelectionPreference.Any.name(),
                LinkSelectionPreference.Available.name()
        }));
        outboundLinkRule.ComplexityLevel(ProductComplexityLevel.Basic);
        LinkPropertyDefinition outboundLinkName = new LinkPropertyDefinition("OutboundLinkName");
        outboundLinkName.DisplayName(EngineResources.Node_OutboundLinkName_DisplayName);
        outboundLinkName.Description(EngineResources.Node_OutboundLinkName_Description);
        outboundLinkName.DefaultString("");
        outboundLinkName.CategoryName(EngineResources.CategoryName_RoutingLogic);
        outboundLinkName.SwitchNumericProperty(outboundLinkPreference);
        outboundLinkName.SwitchNumericCondition(SwitchNumericConditions.Equal);
        outboundLinkName.SwitchNumericValue(3.0);
        outboundLinkName.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition sequenceExpectedOperationTime = new ExpressionPropertyDefinition(
                "SequenceExpectedOperationTime");
        sequenceExpectedOperationTime.DisplayName(EngineResources.Node_SequenceExpectedOperationTime_DisplayName);
        sequenceExpectedOperationTime.Description(EngineResources.Node_SequenceExpectedOperationTime_Description);
        sequenceExpectedOperationTime.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        sequenceExpectedOperationTime.DefaultString("0.0");
        sequenceExpectedOperationTime.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
        sequenceExpectedOperationTime.DefaultUnitSubType(0);
        sequenceExpectedOperationTime.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(initialTravelerCapacity);
        super.getPropertyDefinitions().add(entryRankingRule);
        super.getPropertyDefinitions().add(entryRankingExpression);
        super.getPropertyDefinitions().add(entryExemptionCondition);
        super.getPropertyDefinitions().add(outputFlowControlMode);
        super.getPropertyDefinitions().add(flowSplitAllocationRule);
        super.getPropertyDefinitions().add(outboundLinkPreference);
        super.getPropertyDefinitions().add(outboundLinkRule);
        super.getPropertyDefinitions().add(outboundLinkName);
        super.getPropertyDefinitions().add(sequenceExpectedOperationTime);
        outputFlowControlMode.SetLocalVisible(false, super.getPropertyDefinitions());
        flowSplitAllocationRule.SetLocalVisible(false, super.getPropertyDefinitions());
        QueueStateObject<IneligiblePickupSelection> ridePickupQueue = new QueueStateObject<>(
                "RidePickupQueue");
        ridePickupQueue.Description(EngineResources.Node_RidePickupQueue_Description);
        QueueStateObject<NodeEntryAssociatedObjectRunSpace> entryQueue = new QueueStateObject<>(
                "EntryQueue");
        entryQueue.Description(EngineResources.Node_EntryQueue_Description);
        entryQueue.CanRemove(true);
        BaseStatePropertyObject currentTravelerCapacity = new BaseStatePropertyObject("CurrentTravelerCapacity",
                false, false,
                NumericDataType.Integer);
        currentTravelerCapacity.Description(EngineResources.Node_CurrentTravelerCapacity_Description);
        super.getStateDefinitions().addStateProperty(ridePickupQueue);
        super.getStateDefinitions().addStateProperty(entryQueue);
        super.getStateDefinitions().addStateProperty(currentTravelerCapacity);
        super.createProcessProperties(ObjectClass.Node);

    }

    @Override
    public IntelligentObjectDefinition CreateNewBaseClassDefinition() {
        return NodeDefinition.create();
    }


    public static NodeDefinition create() {
        return new NodeDefinition((ActiveModel) null);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Node(this, name, ElementScope.Private);
    }

    @Override
    public void GetInterfaceProcessInformation(Collection<IEntityProcess> infoList) {
        super.GetInterfaceProcessInformation(infoList);
        super.injectEntityProcessToObjectClass(infoList, NodeInterfaceProcess.class, ObjectClass.Node);
    }

    @Override
    public IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.EIGHT;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public NodeShapeType getNodeShapeType() {
        return this.nodeShapeType;
    }

    public void setNodeShapeType(NodeShapeType nodeShapeType) {
        this.nodeShapeType = nodeShapeType;
    }

    @Override
    public void GetAdditionalProperties(GridItemProperties gridItemProperties,
                                        GridObjectDefinition gridObjectDefinition) {
        super.GetAdditionalProperties(gridItemProperties, gridObjectDefinition);
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_General);
        gridItemProperties.add(new GridItemProperty(EngineResources.Node_NodeColor_DisplayName, gridItemProperty, -100
                , this.getColor(), NodeDefinition.defaultColor, PropertyGridFeel.none,
                EngineResources.Node_NodeColor_Description,
                new PropertyOperator_0<>(Color.class, this::getColor, this::setColor)));
        gridItemProperties.add(new GridItemProperty(EngineResources.Node_NodeShape_DisplayName, gridItemProperty, -101,
                this.getNodeShapeType(), NodeDefinition.defaultNodeShapeType, PropertyGridFeel.none,
                EngineResources.Node_NodeShape_Description,
                new PropertyOperator_0<>(NodeShapeType.class, this::getNodeShapeType, this::setNodeShapeType)));
    }

    @Override
    protected void ReadAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        super.ReadAttributesFromXml(xmlReader, intelligentObjectXml);
        SomeXmlOperator.readAttributeColorOperator(xmlReader, "Color", this::setColor);
        SomeXmlOperator.readEnumAttribute(xmlReader, "ShapeType", this::setNodeShapeType,
                NodeShapeType.class);
    }

    @Override
    public ObjectClass ObjectClass() {
        return ObjectClass.Node;
    }

    @Override
    public boolean vmethod_0() {
        return false;
    }


    @BaseElementFunction("IsExternalNode")
    public static double smethod_178(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (nodeRunSpace.getExternalNode() == null) {
            return 0.0;
        }
        return 1.0;
    }

    @BaseElementFunction("IsInputNode")
    public static double smethod_179(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (!nodeRunSpace.haveInputNode()) {
            return 0.0;
        }
        return 1.0;
    }

    @BaseElementFunction("IsOutputNode")
    public static double smethod_180(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (!nodeRunSpace.haveOutputNode()) {
            return 0.0;
        }
        return 1.0;
    }

    @BaseElementFunction("Token")
    @ElementFunctionReferenceReturnType(FixedDefinition.class)
    public static ExpressionValue smethod_181(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (nodeRunSpace.getExternalNode() == null) ? null :
                ExpressionValue.from(nodeRunSpace.getExternalNode().getFixedRunSpace());
    }

    @BaseElementFunction("AssociatedStation")
    @ElementFunctionReferenceReturnType(StationDefinition.class)
    public static ExpressionValue smethod_182(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (nodeRunSpace.getExternalNode() == null) ? null :
                ExpressionValue.from(nodeRunSpace.getExternalNode().getAssociatedStation(true, true));
    }

    @BaseElementFunction("AssociatedStationLoad")
    public static double smethod_183(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        RideStation associatedStation = (nodeRunSpace.getExternalNode() == null) ? null :
                nodeRunSpace.getExternalNode().getAssociatedStation(true, true);
        if (associatedStation == null) {
            return 0.0;
        }
        double num =
                (double) (nodeRunSpace.getRoutingTravelers().getRoutingInToEnterAssociatedObject() + associatedStation.EntryQueue().NumberInQueue() + associatedStation.getQueueGridItemTrace().NumberInQueue());
        while (associatedStation.method_8() != null) {
            associatedStation = associatedStation.method_8();
            num += (double) associatedStation.getQueueGridItemTrace().NumberInQueue();
        }
        return num;
    }

    @BaseElementFunction("AssociatedStationOverload")
    public static double smethod_184(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        RideStation associatedStation = (nodeRunSpace.getExternalNode() == null) ? null :
                nodeRunSpace.getExternalNode().getAssociatedStation(true, true);
        if (associatedStation == null) {
            return Double.NEGATIVE_INFINITY;
        }
        double num =
                (double) (nodeRunSpace.getRoutingTravelers().getRoutingInToEnterAssociatedObject() + associatedStation.EntryQueue().NumberInQueue() + associatedStation.getQueueGridItemTrace().NumberInQueue());
        double currentCapacity = associatedStation.CurrentCapacity().DoubleValue();
        while (associatedStation.method_8() != null) {
            associatedStation = associatedStation.method_8();
            num += (double) associatedStation.getQueueGridItemTrace().NumberInQueue();
            currentCapacity += associatedStation.CurrentCapacity().DoubleValue();
        }
        return num - currentCapacity;
    }

    @BaseElementFunction("NumberTravelers")
    public static double smethod_185(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.getRoutingTravelers().getTravelersNumber();
    }

    @BaseElementFunction("NumberTravelers.RoutingIn")
    public static double smethod_186(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.getRoutingTravelers().getRoutingInNumber();
    }

    @BaseElementFunction("NumberTravelers.RoutingInToEnterAssociatedObject")
    public static double smethod_187(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.getRoutingTravelers().getRoutingInToEnterAssociatedObject();
    }

    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    @BaseElementFunction("Nearest.Node")
    public static ExpressionValue smethod_188(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return ExpressionValue.from(nodeRunSpace.getNearestNode());
    }

    @BaseElementFunction("Nearest.InputNode")
    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    public static ExpressionValue smethod_189(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return ExpressionValue.from(nodeRunSpace.getInputNode());
    }

    @BaseElementFunction("Nearest.OutputNode")
    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    public static ExpressionValue smethod_190(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return ExpressionValue.from(nodeRunSpace.getNearestOutputNode());
    }

    @BaseElementFunction("InboundLinks.NumberItems")
    public static ExpressionValue smethod_191(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (nodeRunSpace.getInboundNetworkWrapper() != null) {
            return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().backLinksCount());
        }
        return ExpressionValue.from(0.0);
    }

    @BaseElementFunction("InboundLinks.FirstItem")
    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    public static ExpressionValue smethod_192(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (nodeRunSpace.getInboundNetworkWrapper() != null && nodeRunSpace.getInboundNetworkWrapper().backLinksCount() > 0) {
            return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().getBackLinkByIndex(0));
        }
        return null;
    }

    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    @BaseElementFunction("InboundLinks.LastItem")
    public static ExpressionValue smethod_193(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (nodeRunSpace.getInboundNetworkWrapper() != null && nodeRunSpace.getInboundNetworkWrapper().backLinksCount() > 0) {
            return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().getBackLinkByIndex(nodeRunSpace.getInboundNetworkWrapper().backLinksCount() - 1));
        }
        return null;
    }

    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @BaseElementFunction(value = "InboundLinks.ItemAtIndex", Arguments = {
            "index"
    })
    public static ExpressionValue smethod_194(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                              ExpressionValue[] param2) throws Exception0 {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        int num;
        try {
            num = param2[0].toInt();
        } catch (Exception e) {
            throw new Exception0(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    nodeRunSpace.HierarchicalDisplayName(), "InboundLinks.ItemAtIndex"));
        }
        if (nodeRunSpace.getInboundNetworkWrapper() == null || nodeRunSpace.getInboundNetworkWrapper().backLinksCount() < num || num < 1) {
            return null;
        }
        return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().getBackLinkByIndex(num - 1));
    }

    @BaseElementFunction(value = "InboundLinks.IndexOfItem", Arguments = {"link"})
    public static double smethod_195(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace, ExpressionValue[] param2) throws Exception0 {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        LinkRunSpace space = null;
        try {
            space = (LinkRunSpace) param2[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (space == null) {
            throw new Exception0(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    nodeRunSpace.HierarchicalDisplayName(), "InboundLinks.IndexOfItem"));
        }
        if (nodeRunSpace.getInboundNetworkWrapper() != null) {
            int num = 0;
            for (LinkRunSpace linkRunSpace : nodeRunSpace.getInboundNetworkWrapper().getBackLinksIterator()) {
                num++;
                if (linkRunSpace == space) {
                    return num;
                }
            }
            return 0.0;
        }
        return 0.0;
    }

    @BaseElementFunction(value = "InboundLinks.Contains", Arguments = {
            "link"
    })
    public static double smethod_196(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                     ExpressionValue[] expressionValues) throws Exception0 {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        LinkRunSpace linkRunSpace = null;
        try {
            linkRunSpace = (LinkRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (linkRunSpace == null) {
            throw new Exception0(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    nodeRunSpace.HierarchicalDisplayName(), "InboundLinks.Contains"));
        }
        if (nodeRunSpace.getInboundNetworkWrapper() != null) {
            for (LinkRunSpace space : nodeRunSpace.getInboundNetworkWrapper().getBackLinksIterator()) {
                if (space == linkRunSpace) {
                    return 1.0;
                }
            }
            return 0.0;
        }
        return 0.0;
    }

    @BaseElementFunction("OutboundLinks.NumberItems")
    public static ExpressionValue smethod_197(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (nodeRunSpace.getInboundNetworkWrapper() != null) {
            return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().forwardLinksCount());
        }
        return ExpressionValue.from(0.0);
    }

    @BaseElementFunction("OutboundLinks.FirstItem")
    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    public static ExpressionValue smethod_198(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (nodeRunSpace.getInboundNetworkWrapper() != null && nodeRunSpace.getInboundNetworkWrapper().forwardLinksCount() > 0) {
            return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().getForwardLinkByIndex(0));
        }
        return null;
    }

    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    @BaseElementFunction("OutboundLinks.LastItem")
    public static ExpressionValue smethod_199(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (nodeRunSpace.getInboundNetworkWrapper() != null && nodeRunSpace.getInboundNetworkWrapper().forwardLinksCount() > 0) {
            return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().getForwardLinkByIndex(nodeRunSpace.getInboundNetworkWrapper().forwardLinksCount() - 1));
        }
        return null;
    }

    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @BaseElementFunction(value = "OutboundLinks.ItemAtIndex", Arguments = {
            "index"
    })
    public static ExpressionValue smethod_200(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                              ExpressionValue[] expressionValues) throws Exception0 {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception e) {
            throw new Exception0(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    nodeRunSpace.HierarchicalDisplayName(), "OutboundLinks.ItemAtIndex"));
        }
        if (nodeRunSpace.getInboundNetworkWrapper() == null || nodeRunSpace.getInboundNetworkWrapper().forwardLinksCount() < num || num < 1) {
            return null;
        }
        return ExpressionValue.from(nodeRunSpace.getInboundNetworkWrapper().getForwardLinkByIndex(num - 1));
    }

    @BaseElementFunction(value = "OutboundLinks.IndexOfItem", Arguments = {
            "link"
    })
    public static double smethod_201(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                     ExpressionValue[] expressionValues) throws Exception0 {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        LinkRunSpace linkRunSpace = null;
        try {
            linkRunSpace = (LinkRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception e) {
        }
        if (linkRunSpace == null) {
            throw new Exception0(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    nodeRunSpace.HierarchicalDisplayName(), "OutboundLinks.IndexOfItem"));
        }
        if (nodeRunSpace.getInboundNetworkWrapper() != null) {
            int num = 0;
            for (LinkRunSpace space : nodeRunSpace.getInboundNetworkWrapper().getForwardLinks()) {
                num++;
                if (space == linkRunSpace) {
                    return (double) num;
                }
            }
            return 0.0;
        }
        return 0.0;
    }

    @BaseElementFunction(value = "OutboundLinks.Contains", Arguments = {
            "link"
    })
    public static double smethod_202(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                     ExpressionValue[] expressionValues) throws Exception0 {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        LinkRunSpace linkRunSpace = null;
        try {
            linkRunSpace = (LinkRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (linkRunSpace == null) {
            throw new Exception0(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    nodeRunSpace.HierarchicalDisplayName(), "OutboundLinks.Contains"));
        }
        if (nodeRunSpace.getInboundNetworkWrapper() != null) {
            for (LinkRunSpace space : nodeRunSpace.getInboundNetworkWrapper().getForwardLinks()) {
                if (space == linkRunSpace) {
                    return 1.0;
                }
            }
            return 0.0;
        }
        return 0.0;
    }

    @ElementFunction("IsExternalInputNode")
    public static double smethod_203(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return NodeDefinition.smethod_179(absBaseRunSpace, runSpace);
    }

    @ElementFunction("InputLocation.Capacity")
    public static double smethod_204(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (!nodeRunSpace.haveInputNode()) {
            return 0.0;
        }
        RideStation rideStation = nodeRunSpace.getExternalNode().getAssociatedStation(true, true);
        if (rideStation != null) {
            return rideStation.CurrentCapacity().DoubleValue();
        }
        return Double.POSITIVE_INFINITY;
    }

    @ElementFunction("InputLocation.CapacityRemaining")
    public static double smethod_205(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (!nodeRunSpace.haveInputNode()) {
            return 0.0;
        }
        RideStation rideStation = nodeRunSpace.getExternalNode().getAssociatedStation(true, true);
        if (rideStation != null) {
            return rideStation.RideCapacityRemaining();
        }
        return Double.POSITIVE_INFINITY;
    }

    @ElementFunction("InputLocation.NumberInLocation")
    public static double smethod_206(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (!nodeRunSpace.haveInputNode()) {
            return 0.0;
        }
        RideStation rideStation = nodeRunSpace.getExternalNode().getAssociatedStation(true, true);
        if (rideStation != null) {
            return (double) rideStation.getQueueGridItemTrace().NumberInQueue();
        }
        return 0.0;
    }

    @ElementFunction("InputLocation.NumberWaitingEntry")
    public static double smethod_207(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        if (!nodeRunSpace.haveInputNode()) {
            return 0.0;
        }
        RideStation rideStation = nodeRunSpace.getExternalNode().getAssociatedStation(true, true);
        if (rideStation != null) {
            return (double) rideStation.EntryQueue().NumberInQueue();
        }
        return 0.0;
    }

    @ElementFunction("InputLocation.Load")
    public static double smethod_208(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.getRoutingTravelers().getRoutingInToEnterAssociatedObject() + NodeDefinition.smethod_207(absBaseRunSpace, runSpace) + NodeDefinition.smethod_206(absBaseRunSpace, runSpace);
    }

    @ElementFunction("InputLocation.Overload")
    public static double smethod_209(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.getRoutingTravelers().getRoutingInToEnterAssociatedObject() + NodeDefinition.smethod_207(absBaseRunSpace, runSpace) + NodeDefinition.smethod_206(absBaseRunSpace, runSpace) - NodeDefinition.smethod_204(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberRoutingIn")
    public static double smethod_210(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return NodeDefinition.smethod_186(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberRoutingIn.CanEnterAssociatedObject")
    public static double smethod_211(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.getRoutingTravelers().getRoutingInToEnterAssociatedObject();
    }

    @ElementFunction("NumberRoutingIn.CanEnterObjects")
    public static double smethod_212(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.getRoutingTravelers().getRoutingInToEnterAssociatedObject();
    }

    @ElementFunction("InputLocation.NumberInProcess")
    public static double smethod_213(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return NodeDefinition.smethod_206(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberRouting")
    public static double smethod_214(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return NodeDefinition.smethod_186(absBaseRunSpace, runSpace);
    }

    @ElementFunction("InputCapacity")
    public static double smethod_215(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return NodeDefinition.smethod_204(absBaseRunSpace, runSpace);
    }

    @ElementFunction("InputRemainingCapacity")
    public static double HesMtEnhvFR(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return NodeDefinition.smethod_205(absBaseRunSpace, runSpace);
    }

    @ElementFunction("InputNumberWaiting")
    public static double smethod_216(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return NodeDefinition.smethod_207(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberParked")
    public static double smethod_217(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NodeRunSpace nodeRunSpace = (NodeRunSpace) absBaseRunSpace;
        return (double) nodeRunSpace.ParkingStation().getQueueGridItemTrace().NumberInQueue();
    }

    @Override
    public StringPropertyDefinition GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 61 && (StringHelper.equalsLocal(name, "OutputFlowStrategy") || StringHelper.equalsLocal(name,
                "OutputFlowControlStrategy"))) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("OutputFlowControlMode");
        }
        if (intelligentObjectXml.FileVersion() < 63 && StringHelper.equalsLocal(name,
                "OutputFlowSplitAllocationRule")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("FlowSplitAllocationRule");
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }
}
