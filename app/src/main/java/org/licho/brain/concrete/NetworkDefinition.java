package org.licho.brain.concrete;

import org.licho.brain.annotations.ElementFunctionReferenceReturnType;
import org.licho.brain.annotations.UnitClass;
import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.exception.Exception0;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.element.Network;
import org.licho.brain.enu.NetworkTurnaroundMethod;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.simioEnums.SwitchNumericConditions;
import org.licho.brain.utils.simu.IVisitRequest;

/**
 *
 */
public class NetworkDefinition extends AbsDefinition {
    public static final NetworkDefinition Instance = new NetworkDefinition();
    public static final String name = "Global";

    private NetworkDefinition() {
        super("Network");

        super.Description(EngineResources.ElementDescription_Network);
        super.getPropertyDefinitions().getPropertyDefinitionList().clear();
        PropertyDefinitionFacade travelLogic = new PropertyDefinitionFacade();
        travelLogic.Name(EngineResources.CategoryName_TravelLogic);
        travelLogic.Description(EngineResources.CategoryName_TravelLogic);
        travelLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade shortestPathCalculations = new PropertyDefinitionFacade();
        shortestPathCalculations.Name(EngineResources.CategoryName_ShortestPathCalculations);
        shortestPathCalculations.Description(EngineResources.CategoryName_ShortestPathCalculations);
        shortestPathCalculations.InitiallyExpanded(false);

        super.getPropertyDefinitions().getPropertyDefinitionList().add(travelLogic);
        super.getPropertyDefinitions().getPropertyDefinitionList().add(shortestPathCalculations);
        EnumPropertyDefinition turnaroundMethod = new EnumPropertyDefinition("TurnaroundMethod",
                NetworkTurnaroundMethod.class);
        turnaroundMethod.DisplayName(EngineResources.Network_TurnaroundMethod_DisplayName);
        turnaroundMethod.Description(EngineResources.Network_TurnaroundMethod_Description);
        turnaroundMethod.DefaultString("Default");
        turnaroundMethod.stringValues = new String[]
                {
                        "Default",
                        "Exit & Re-enter",
                        "Rotate In Place",
                        "Reverse"
                };
        turnaroundMethod.CategoryName(EngineResources.CategoryName_TravelLogic);
        BooleanPropertyDefinition autoUpdateShortestPaths = new BooleanPropertyDefinition("AutoUpdateShortestPaths");
        autoUpdateShortestPaths.DisplayName(EngineResources.Network_AutoUpdateShortestPaths_DisplayName);
        autoUpdateShortestPaths.Description(EngineResources.Network_AutoUpdateShortestPaths_Description);
        autoUpdateShortestPaths.DefaultString("False");
        autoUpdateShortestPaths.CategoryName(EngineResources.CategoryName_ShortestPathCalculations);
        autoUpdateShortestPaths.ComplexityLevel(ProductComplexityLevel.Advanced);
        EventPropertyDefinition updateShortestPathsEvent = new EventPropertyDefinition("UpdateShortestPathsEvent");
        updateShortestPathsEvent.DisplayName(EngineResources.Network_UpdateShortestPathsEvent_DisplayName);
        updateShortestPathsEvent.Description(EngineResources.Network_UpdateShortestPathsEvent_Description);
        updateShortestPathsEvent.DefaultString("");
        updateShortestPathsEvent.CategoryName(EngineResources.CategoryName_ShortestPathCalculations);
        updateShortestPathsEvent.SwitchNumericProperty(autoUpdateShortestPaths);
        updateShortestPathsEvent.SwitchNumericValue(0.0);
        updateShortestPathsEvent.SwitchNumericCondition(SwitchNumericConditions.Equal);
        updateShortestPathsEvent.RequiredValue(false);
        updateShortestPathsEvent.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(turnaroundMethod);
        super.getPropertyDefinitions().add(autoUpdateShortestPaths);
        super.getPropertyDefinitions().add(updateShortestPathsEvent);
        QueueStateObject<IVisitRequest> visitRequestQueue = new QueueStateObject<>("VisitRequestQueue");
        visitRequestQueue.Description(EngineResources.Network_VisitRequestQueue_Description);
        super.getStateDefinitions().addStateProperty(visitRequestQueue);

    }

    @Override
    public Class<?> ElementType() {
        return Network.class;
    }

    @Override
    public Class<?> RunSpaceType() {
        return NetworkElementRunSpace.class;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return NetworkDefinition.Instance;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Network(this, name, ElementScope.Private);
    }

    public static Network createInstance(String name) {
        return (Network) NetworkDefinition.Instance.CreateInstance(name);
    }

    @Override
    public boolean IsValidIdentifier(String param0, StringBuffer error) {
        return false;
    }

    @Override
    public String GetUniqueName(String param0) {
        return null;
    }

    @Override
    public StringPropertyDefinition GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 57 && StringHelper.equalsLocal(name, "AutomaticUpdates")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("AutoUpdateShortestPaths");
        }
        if (intelligentObjectXml.FileVersion() < 57 && StringHelper.equalsLocal(name, "UpdateEvent")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("UpdateShortestPathsEvent");
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }

    @UnitClass(UnitType.Length)
    @BaseElementFunction(value = "Distance", Arguments = {"fromNode", "toNode"})
    public static double smethod_7(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                   ExpressionValue[] expressionValues) throws Exception0 {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        NodeRunSpace fromNode = null;
        NodeRunSpace toNode = null;
        try {
            fromNode = (NodeRunSpace) expressionValues[0].getAbsBaseRunSpace();
            toNode = (NodeRunSpace) expressionValues[1].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (fromNode == null || toNode == null) {
            throw new Exception0(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    networkElementRunSpace.HierarchicalDisplayName(), "Distance"));
        }
        return networkElementRunSpace.getNetworkDistanceToNode(fromNode, toNode);
    }

    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    @BaseElementFunction(value = "NextLink", Arguments = {"fromNode", "toNode"})
    public static ExpressionValue smethod_8(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                            ExpressionValue[] expressionValues) {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        NodeRunSpace fromNode = null;
        NodeRunSpace toNode = null;
        try {
            fromNode = (NodeRunSpace) expressionValues[0].getAbsBaseRunSpace();
            toNode = (NodeRunSpace) expressionValues[1].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (fromNode != null && toNode != null) {
            return ExpressionValue.from(networkElementRunSpace.NextLink(fromNode, toNode));
        }
        RuntimeErrorFullMessageDetails.reportError(runSpace,
                String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                        networkElementRunSpace.HierarchicalDisplayName(), "NextLink"));
        return null;
    }

    @ElementFunctionReferenceReturnType(NodeDefinition.class)
    @BaseElementFunction(value = "NextNode", Arguments = {"fromNode", "toNode"})
    public static ExpressionValue smethod_9(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                            ExpressionValue[] expressionValues) {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        NodeRunSpace fromNode = null;
        NodeRunSpace toNode = null;
        try {
            fromNode = (NodeRunSpace) expressionValues[0].getAbsBaseRunSpace();
            toNode = (NodeRunSpace) expressionValues[1].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (fromNode == null || toNode == null) {
            RuntimeErrorFullMessageDetails.reportError(runSpace,
                    String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                            networkElementRunSpace.HierarchicalDisplayName(), "NextNode"));
            return null;
        }
        LinkRunSpace linkRunSpace = networkElementRunSpace.NextLink(fromNode, toNode);
        if (linkRunSpace != null) {
            return ExpressionValue.from((fromNode == linkRunSpace.getBeginNodeRunSpace()) ?
                    linkRunSpace.getEndNodeRunSpace() :
                    linkRunSpace.getBeginNodeRunSpace());
        }
        if (fromNode == toNode) {
            return ExpressionValue.from(toNode);
        }
        return null;
    }

    @BaseElementFunction(value = "PathExists", Arguments = {"fromNode", "toNode"})
    public static double smethod_10(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                    ExpressionValue[] expressionValues) {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        NodeRunSpace fromNode = null;
        NodeRunSpace toNode = null;
        try {
            fromNode = (NodeRunSpace) expressionValues[0].getAbsBaseRunSpace();
            toNode = (NodeRunSpace) expressionValues[1].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (fromNode == null || toNode == null) {
            RuntimeErrorFullMessageDetails.reportError(runSpace,
                    String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                            networkElementRunSpace.HierarchicalDisplayName(), "PathExists"));
            return Double.NaN;
        }
        if (networkElementRunSpace.PathExists(fromNode, toNode)) {
            return 1.0;
        }
        return 0.0;
    }

    @BaseElementFunction("Links.NumberItems")
    public static ExpressionValue smethod_11(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        return ExpressionValue.from(networkElementRunSpace.NumberItems());
    }

    @BaseElementFunction("Links.FirstItem")
    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    public static ExpressionValue smethod_12(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        if (networkElementRunSpace.NumberItems() > 0) {
            return networkElementRunSpace.ItemAtIndex(0);
        }
        return null;
    }

    @BaseElementFunction("Links.LastItem")
    @ElementFunctionReferenceReturnType(LinkDefinition.class)
    public static ExpressionValue smethod_13(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        if (networkElementRunSpace.NumberItems() > 0) {
            return networkElementRunSpace.ItemAtIndex(networkElementRunSpace.NumberItems() - 1);
        }
        return null;
    }

    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @BaseElementFunction(value = "Links.ItemAtIndex", Arguments = {"index"})
    public static ExpressionValue smethod_14(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                             ExpressionValue[] expressionValues) throws Exception0 {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception ignored) {
            throw new Exception0(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    networkElementRunSpace.HierarchicalDisplayName(), "Links.ItemAtIndex"));
        }
        if (networkElementRunSpace.NumberItems() < num || num < 1) {
            return null;
        }
        return networkElementRunSpace.ItemAtIndex(num - 1);
    }

    @BaseElementFunction(value = "Links.IndexOfItem", Arguments = {"link"})
    public static double smethod_15(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                    ExpressionValue[] expressionValues) throws Exception0 {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        LinkRunSpace linkRunSpace = null;
        try {
            linkRunSpace = (LinkRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (linkRunSpace == null) {
            throw new Exception0(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    networkElementRunSpace.HierarchicalDisplayName(), "Links.IndexOfItem"));
        }
        return (double) (networkElementRunSpace.getLinkRunSpaceList().indexOf(linkRunSpace) + 1);
    }

    @BaseElementFunction(value = "Links.Contains", Arguments = {"link"})
    public static double smethod_16(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                    ExpressionValue[] expressionValues) throws Exception0 {
        NetworkElementRunSpace networkElementRunSpace = (NetworkElementRunSpace) absBaseRunSpace;
        LinkRunSpace linkRunSpace = null;
        try {
            linkRunSpace = (LinkRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (linkRunSpace == null) {
            throw new Exception0(String.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    networkElementRunSpace.HierarchicalDisplayName(), "Links.Contains"));
        }
        if (networkElementRunSpace.getLinkRunSpaceList().contains(linkRunSpace)) {
            return 1.0;
        }
        return 0.0;
    }
}
