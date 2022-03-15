package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainEnums.QueueRanking;
import org.licho.brain.brainEnums.SwitchNumericConditions;

/**
 *
 */
public class ContainerDefinition extends AbsDefinition {
    public static final ContainerDefinition Instance = new ContainerDefinition();

    public ContainerDefinition() {
        super("Container");

        super.Description(EngineResources.ElementDescription_Container);
        ExpressionPropertyDefinition initialVolumeCapacity = new ExpressionPropertyDefinition("InitialVolumeCapacity");
        initialVolumeCapacity.DisplayName(EngineResources.Container_InitialVolumeCapacity_DisplayName);
        initialVolumeCapacity.Description(EngineResources.Container_InitialVolumeCapacity_Description);
        initialVolumeCapacity.DefaultString("Infinity");
        initialVolumeCapacity.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Volume);
        initialVolumeCapacity.CategoryName(EngineResources.CategoryName_BasicLogic);
        ExpressionPropertyDefinition initialWeightCapacity = new ExpressionPropertyDefinition("InitialWeightCapacity");
        initialWeightCapacity.DisplayName(EngineResources.Container_InitialWeightCapacity_DisplayName);
        initialWeightCapacity.Description(EngineResources.Container_InitialWeightCapacity_Description);
        initialWeightCapacity.DefaultString("Infinity");
        initialWeightCapacity.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Weight);
        initialWeightCapacity.CategoryName(EngineResources.CategoryName_BasicLogic);
        EnumPropertyDefinition contentsRankingRule = new EnumPropertyDefinition("ContentsRankingRule",
                QueueRanking.class);
        contentsRankingRule.DisplayName(EngineResources.Container_ContentsRankingRule_DisplayName);
        contentsRankingRule.Description(EngineResources.Container_ContentsRankingRule_Description);
        contentsRankingRule.DefaultString(QueueRanking.FirstInFirstOut.toString());
        contentsRankingRule.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        contentsRankingRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition contentsRankingExpression = new ExpressionPropertyDefinition(
                "ContentsRankingExpression");
        contentsRankingExpression.DisplayName(EngineResources.Container_ContentsRankingExpression_DisplayName);
        contentsRankingExpression.Description(EngineResources.Container_ContentsRankingExpression_Description);
        contentsRankingExpression.DefaultString("Entity.Priority");
        contentsRankingExpression.ParentPropertyDefinition(contentsRankingRule);
        contentsRankingExpression.SwitchNumericProperty(contentsRankingRule);
        contentsRankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        contentsRankingExpression.SwitchNumericValue(1.0);
        contentsRankingExpression.RequiredValue(false);
        contentsRankingExpression.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition onFullProcess = new ElementPropertyDefinition("OnFullProcess", ProcessProperty.class);
        onFullProcess.DisplayName(EngineResources.Container_OnFullProcess_DisplayName);
        onFullProcess.Description(EngineResources.Container_OnFullProcess_Description);
        onFullProcess.DefaultString("");
        onFullProcess.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        onFullProcess.RequiredValue(false);
        onFullProcess.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition onEmptyProcess = new ElementPropertyDefinition("OnEmptyProcess",
                ProcessProperty.class);
        onEmptyProcess.DisplayName(EngineResources.Container_OnEmptyProcess_DisplayName);
        onEmptyProcess.Description(EngineResources.Container_OnEmptyProcess_Description);
        onEmptyProcess.DefaultString("");
        onEmptyProcess.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        onEmptyProcess.RequiredValue(false);
        onEmptyProcess.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(initialVolumeCapacity);
        super.getPropertyDefinitions().add(initialWeightCapacity);
        super.getPropertyDefinitions().add(contentsRankingRule);
        super.getPropertyDefinitions().add(contentsRankingExpression);
        super.getPropertyDefinitions().add(onFullProcess);
        super.getPropertyDefinitions().add(onEmptyProcess);
        EntityObjectQueueState contents = new EntityObjectQueueState("Contents");
        contents.Description(EngineResources.Container_ContentsQueue_Description);
        BaseStatePropertyObject currentVolumeCapacity = new BaseStatePropertyObject("CurrentVolumeCapacity", false, false,
                NumericDataType.Real);
        currentVolumeCapacity.Description(EngineResources.Container_CurrentVolumeCapacity_Description);
        currentVolumeCapacity.UnitType(UnitType.Volume);
        BaseStatePropertyObject currentWeightCapacity = new BaseStatePropertyObject("CurrentWeightCapacity", false, false,
                NumericDataType.Real);
        currentWeightCapacity.Description(EngineResources.Container_CurrentWeightCapacity_Description);
        currentWeightCapacity.UnitType(UnitType.Weight);
        CostStatePropertyObject currentVolumeFlowIn = new CostStatePropertyObject("CurrentVolumeFlowIn", true, false);
        currentVolumeFlowIn.Description(EngineResources.Container_CurrentVolumeFlowIn_Description);
        currentVolumeFlowIn.UnitType(UnitType.Volume);
        CostStatePropertyObject currentVolumeFlowOut = new CostStatePropertyObject("CurrentVolumeFlowOut", true, false);
        currentVolumeFlowOut.Description(EngineResources.Container_CurrentVolumeFlowOut_Description);
        currentVolumeFlowOut.UnitType(UnitType.Volume);
        CostStatePropertyObject currentWeightFlowIn = new CostStatePropertyObject("CurrentWeightFlowIn", true, false);
        currentWeightFlowIn.Description(EngineResources.Container_CurrentWeightFlowIn_Description);
        currentWeightFlowIn.UnitType(UnitType.Weight);
        CostStatePropertyObject currentWeightFlowOut = new CostStatePropertyObject("CurrentWeightFlowOut", true, false);
        currentWeightFlowOut.Description(EngineResources.Container_CurrentWeightFlowOut_Description);
        currentWeightFlowOut.UnitType(UnitType.Weight);
        super.getStateDefinitions().addStateProperty(contents);
        super.getStateDefinitions().addStateProperty(currentVolumeCapacity);
        super.getStateDefinitions().addStateProperty(currentWeightCapacity);
        super.getStateDefinitions().addStateProperty(currentVolumeFlowIn);
        super.getStateDefinitions().addStateProperty(currentVolumeFlowOut);
        super.getStateDefinitions().addStateProperty(currentWeightFlowIn);
        super.getStateDefinitions().addStateProperty(currentWeightFlowOut);
        EventDefinition entered = new EventDefinition("Entered", false);
        entered.Description(EngineResources.Container_EnteredEvent_Description);
        EventDefinition exited = new EventDefinition("Exited", false);
        exited.Description(EngineResources.Container_ExitedEvent_Description);
        EventDefinition full = new EventDefinition("Full", false);
        full.Description(EngineResources.Container_FullEvent_Description);
        EventDefinition empty = new EventDefinition("Empty", false);
        empty.Description(EngineResources.Container_EmptyEvent_Description);
        super.getEventDefinitions().InsertEventDefinition(entered);
        super.getEventDefinitions().InsertEventDefinition(exited);
        super.getEventDefinitions().InsertEventDefinition(full);
        super.getEventDefinitions().InsertEventDefinition(empty);

    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Container(this, name, ElementScope.Private);
    }


    public static Container createContainer(String param0) {
        return (Container) ContainerDefinition.Instance.CreateInstance(param0);
    }


    @Override
    public Class<?> ElementType() {
        return Container.class;
    }

    @Override
    public Class<?> RunSpaceType() {
        return ContainerElementRunSpace.class;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return ContainerDefinition.Instance;
    }
}
