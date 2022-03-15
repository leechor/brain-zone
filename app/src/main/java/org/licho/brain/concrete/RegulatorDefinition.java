package org.licho.brain.concrete;

import org.licho.brain.annotations.ElementFunctionReferenceReturnType;
import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.enu.BooleanType;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainEnums.FlowRateUnitType;
import org.licho.brain.brainEnums.QueueRanking;
import org.licho.brain.brainEnums.RegulatorInputFlowStrategy;
import org.licho.brain.brainEnums.RegulatorMergeAllocationRule;
import org.licho.brain.brainEnums.RegulatorMergeMatchingRule;
import org.licho.brain.brainEnums.SwitchNumericConditions;

import java.text.MessageFormat;

/**
 *
 */
public class RegulatorDefinition extends AbsDefinition {
    public static final RegulatorDefinition Instance = new RegulatorDefinition();

    private RegulatorDefinition() {
        super("Regulator");
        super.Description(EngineResources.ElementDescription_Regulator);
        EnumPropertyDefinition flowRateUnitType = new EnumPropertyDefinition("FlowRateUnitType",
                FlowRateUnitType.class);
        flowRateUnitType.DisplayName(EngineResources.Regulator_FlowRateUnitType_DisplayName);
        flowRateUnitType.Description(EngineResources.Regulator_FlowRateUnitType_Description);
        flowRateUnitType.DefaultString(FlowRateUnitType.VolumeFlowRate.toString());
        flowRateUnitType.CategoryName(EngineResources.CategoryName_BasicLogic);
        ExpressionPropertyDefinition initialMaximumFlowRate = new ExpressionPropertyDefinition(
                "InitialMaximumFlowRate");
        initialMaximumFlowRate.DisplayName(EngineResources.Regulator_InitialMaximumFlowRate_DisplayName);
        initialMaximumFlowRate.Description(EngineResources.Regulator_InitialMaximumFlowRate_Description);
        initialMaximumFlowRate.DefaultString("0.0");
        initialMaximumFlowRate.UnitTypePropertyDefinition(flowRateUnitType);
        initialMaximumFlowRate.CategoryName(EngineResources.CategoryName_BasicLogic);
        ExpressionPropertyDefinition initialOutputYieldFactor = new ExpressionPropertyDefinition(
                "InitialOutputYieldFactor");
        initialOutputYieldFactor.DisplayName(EngineResources.Regulator_InitialOutputYieldFactor_DisplayName);
        initialOutputYieldFactor.Description(EngineResources.Regulator_InitialOutputYieldFactor_Description);
        initialOutputYieldFactor.DefaultString("1.0");
        initialOutputYieldFactor.CategoryName(EngineResources.CategoryName_BasicLogic);
        DynamicObjectInstancePropertyDefinition initialDesiredOutputEntityType =
                new DynamicObjectInstancePropertyDefinition("InitialDesiredOutputEntityType");
        initialDesiredOutputEntityType.DisplayName(EngineResources.Regulator_InitialDesiredOutputEntityType_DisplayName);
        initialDesiredOutputEntityType.Description(EngineResources.Regulator_InitialDesiredOutputEntityType_Description);
        initialDesiredOutputEntityType.RequiredValue(false);
        initialDesiredOutputEntityType.DefaultString("");
        initialDesiredOutputEntityType.UseDefaultEntity(false);
        initialDesiredOutputEntityType.CategoryName(EngineResources.CategoryName_BasicLogic);
        EnumPropertyDefinition flowRequestRankingRule = new EnumPropertyDefinition("FlowRequestRankingRule",
                QueueRanking.class);
        flowRequestRankingRule.DisplayName(EngineResources.Regulator_FlowRequestRankingRule_DisplayName);
        flowRequestRankingRule.Description(EngineResources.Regulator_FlowRequestRankingRule_Description);
        flowRequestRankingRule.DefaultString(QueueRanking.FirstInFirstOut.toString());
        flowRequestRankingRule.CategoryName(EngineResources.CategoryName_InputFlowControl);
        ExpressionPropertyDefinition flowRequestRankingExpression = new ExpressionPropertyDefinition(
                "FlowRequestRankingExpression");
        flowRequestRankingExpression.DisplayName(EngineResources.Regulator_FlowRequestRankingExpression_DisplayName);
        flowRequestRankingExpression.Description(EngineResources.Regulator_FlowRequestRankingExpression_Description);
        flowRequestRankingExpression.DefaultString("Entity.Priority");
        flowRequestRankingExpression.ParentPropertyDefinition(flowRequestRankingRule);
        flowRequestRankingExpression.SwitchNumericProperty(flowRequestRankingRule);
        flowRequestRankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        flowRequestRankingExpression.SwitchNumericValue(1.0);
        flowRequestRankingExpression.RequiredValue(false);
        EnumPropertyDefinition inputFlowControlMode = new EnumPropertyDefinition("InputFlowControlMode",
                RegulatorInputFlowStrategy.class);
        inputFlowControlMode.DisplayName(EngineResources.Regulator_InputFlowControlMode_DisplayName);
        inputFlowControlMode.Description(EngineResources.Regulator_InputFlowControlMode_Description);
        inputFlowControlMode.DefaultString(RegulatorInputFlowStrategy.SingleFlow.toString());
        inputFlowControlMode.CategoryName(EngineResources.CategoryName_InputFlowControl);
        EnumPropertyDefinition flowMergeMatchingRule = new EnumPropertyDefinition("FlowMergeMatchingRule",
                RegulatorMergeMatchingRule.class);
        flowMergeMatchingRule.DisplayName(EngineResources.Regulator_FlowMergeMatchingRule_DisplayName);
        flowMergeMatchingRule.Description(EngineResources.Regulator_FlowMergeMatchingRule_Description);
        flowMergeMatchingRule.DefaultString(RegulatorMergeMatchingRule.AnyEntityType.toString());
        flowMergeMatchingRule.ParentPropertyDefinition(inputFlowControlMode);
        flowMergeMatchingRule.SwitchNumericProperty(inputFlowControlMode);
        flowMergeMatchingRule.SwitchNumericCondition(SwitchNumericConditions.Equal);
        flowMergeMatchingRule.SwitchNumericValue(1.0);
        EnumPropertyDefinition flowMergeAllocationRule = new EnumPropertyDefinition("FlowMergeAllocationRule",
                RegulatorMergeAllocationRule.class);
        flowMergeAllocationRule.DisplayName(EngineResources.Regulator_FlowMergeAllocationRule_DisplayName);
        flowMergeAllocationRule.Description(EngineResources.Regulator_FlowMergeAllocationRule_Description);
        flowMergeAllocationRule.DefaultString(RegulatorMergeAllocationRule.ProportionalBasedOnInflowRates.toString());
        flowMergeAllocationRule.ParentPropertyDefinition(inputFlowControlMode);
        flowMergeAllocationRule.SwitchNumericProperty(inputFlowControlMode);
        flowMergeAllocationRule.SwitchNumericCondition(SwitchNumericConditions.Equal);
        flowMergeAllocationRule.SwitchNumericValue(1.0);
        ExpressionPropertyDefinition flowMergeProportionExpression = new ExpressionPropertyDefinition(
                "FlowMergeProportionExpression");
        flowMergeProportionExpression.DisplayName(EngineResources.Regulator_FlowMergeProportionExpression_DisplayName);
        flowMergeProportionExpression.Description(EngineResources.Regulator_FlowMergeProportionExpression_Description);
        flowMergeProportionExpression.RequiredValue(false);
        flowMergeProportionExpression.DefaultString("");
        flowMergeProportionExpression.ParentPropertyDefinition(inputFlowControlMode);
        flowMergeProportionExpression.SwitchNumericProperty(flowMergeAllocationRule);
        flowMergeProportionExpression.SwitchNumericCondition(SwitchNumericConditions.Equal);
        flowMergeProportionExpression.SwitchNumericValue(2.0);
        EnumPropertyDefinition initiallyEnabled = new EnumPropertyDefinition("InitiallyEnabled", BooleanType.class);
        initiallyEnabled.DisplayName(EngineResources.Regulator_InitiallyEnabled_DisplayName);
        initiallyEnabled.Description(EngineResources.Regulator_InitiallyEnabled_Description);
        initiallyEnabled.DefaultString("True");
        initiallyEnabled.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        initiallyEnabled.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(flowRateUnitType);
        super.getPropertyDefinitions().add(initialMaximumFlowRate);
        super.getPropertyDefinitions().add(initialOutputYieldFactor);
        super.getPropertyDefinitions().add(initialDesiredOutputEntityType);
        super.getPropertyDefinitions().add(flowRequestRankingRule);
        super.getPropertyDefinitions().add(flowRequestRankingExpression);
        super.getPropertyDefinitions().add(inputFlowControlMode);
        super.getPropertyDefinitions().add(flowMergeMatchingRule);
        super.getPropertyDefinitions().add(flowMergeAllocationRule);
        super.getPropertyDefinitions().add(flowMergeProportionExpression);
        super.getPropertyDefinitions().add(initiallyEnabled);
        QueueStateObject<FlowRequestRunSpace> flowRequestQueue = new QueueStateObject<>("FlowRequestQueue");
        flowRequestQueue.CanRemove(true);
        flowRequestQueue.Description(EngineResources.Regulator_FlowRequestQueue_Description);
        BaseStatePropertyObject currentMaximumFlowRate = new BaseStatePropertyObject("CurrentMaximumFlowRate", false, false,
                NumericDataType.Real);
        currentMaximumFlowRate.UnitTypePropertyName("FlowRateUnitType");
        currentMaximumFlowRate.Description(EngineResources.Regulator_CurrentMaximumFlowRate_Description);
        BaseStatePropertyObject currentOutputYieldFactor = new BaseStatePropertyObject("CurrentOutputYieldFactor", false, false,
                NumericDataType.Real);
        currentOutputYieldFactor.Description(EngineResources.Regulator_CurrentOutputYieldFactor_Description);
        BaseStatePropertyObject enabled = new BaseStatePropertyObject("Enabled", false, false, NumericDataType.Boolean);
        enabled.Description(EngineResources.Regulator_Enabled_Description);
        CostStatePropertyObject currentVolumeFlowIn = new CostStatePropertyObject("CurrentVolumeFlowIn", true, false);
        currentVolumeFlowIn.Description(EngineResources.Regulator_CurrentVolumeFlowIn_Description);
        currentVolumeFlowIn.UnitType(UnitType.Volume);
        CostStatePropertyObject currentVolumeFlowOut = new CostStatePropertyObject("CurrentVolumeFlowOut", true, false);
        currentVolumeFlowOut.Description(EngineResources.Regulator_CurrentVolumeFlowOut_Description);
        currentVolumeFlowOut.UnitType(UnitType.Volume);
        CostStatePropertyObject currentWeightFlowIn = new CostStatePropertyObject("CurrentWeightFlowIn", true, false);
        currentWeightFlowIn.Description(EngineResources.Regulator_CurrentWeightFlowIn_Description);
        currentWeightFlowIn.UnitType(UnitType.Weight);
        CostStatePropertyObject currentWeightFlowOut = new CostStatePropertyObject("CurrentWeightFlowOut", true, false);
        currentWeightFlowOut.Description(EngineResources.Regulator_CurrentWeightFlowOut_Description);
        currentWeightFlowOut.UnitType(UnitType.Weight);
        ElementReferenceStatePropertyObject currentDesiredOutputEntityType = new ElementReferenceStatePropertyObject(
                "CurrentDesiredOutputEntityType", false, false, EntityDefinition.EntityFacility);
        currentDesiredOutputEntityType.Description(EngineResources.Regulator_CurrentDesiredOutputEntityType_Description);
        super.getStateDefinitions().addStateProperty(flowRequestQueue);
        super.getStateDefinitions().addStateProperty(currentMaximumFlowRate);
        super.getStateDefinitions().addStateProperty(currentOutputYieldFactor);
        super.getStateDefinitions().addStateProperty(enabled);
        super.getStateDefinitions().addStateProperty(currentVolumeFlowIn);
        super.getStateDefinitions().addStateProperty(currentVolumeFlowOut);
        super.getStateDefinitions().addStateProperty(currentWeightFlowIn);
        super.getStateDefinitions().addStateProperty(currentWeightFlowOut);
        super.getStateDefinitions().addStateProperty(currentDesiredOutputEntityType);
        currentMaximumFlowRate.RefreshIfInError();
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Regulator(this, name, ElementScope.Private);
    }

    public static Regulator CreateRegulator(String name) {
        return (Regulator) RegulatorDefinition.Instance.CreateInstance(name);
    }

    @Override
    public Class<?> ElementType() {
        return Regulator.class;
    }

    @Override
    public Class<?> RunSpaceType() {
        return RegulatorElementRunSpace.class;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return RegulatorDefinition.Instance;
    }

    @Override
    public StringPropertyDefinition GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 61 && (StringHelper.equalsLocal(name, "InputFlowStrategy") || StringHelper.equalsLocal(name,
                "InputFlowControlStrategy"))) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("InputFlowControlMode");
        }
        if (intelligentObjectXml.FileVersion() < 62 && (StringHelper.equalsLocal(name, "MergeMatchingRule") || StringHelper.equalsLocal(name,
                "InputFlowMergeMatchingRule"))) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("FlowMergeMatchingRule");
        }
        if (intelligentObjectXml.FileVersion() < 62 && (StringHelper.equalsLocal(name, "MergeAllocationRule") || StringHelper.equalsLocal(name,
                "InputFlowMergeAllocationRule"))) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("FlowMergeAllocationRule");
        }
        if (intelligentObjectXml.FileVersion() < 62 && StringHelper.equalsLocal(name,
                "InputFlowMergeProportionalExpression")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(
                    "FlowMergeProportionExpression");
        }
        if (intelligentObjectXml.FileVersion() < 66 && StringHelper.equalsLocal(name, "OutputFlowEntityType")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(
                    "InitialDesiredOutputEntityType");
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }

    @BaseElementFunction("OutputFlowReceivers.NumberItems")
    public static ExpressionValue eNoqOkyknVo(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RegulatorElementRunSpace regulatorElementRunSpace = (RegulatorElementRunSpace) absBaseRunSpace;
        return ExpressionValue.from(regulatorElementRunSpace.getOutputFlowReceiversNumberItems());
    }

    @BaseElementFunction("OutputFlowReceivers.FirstItem")
    @ElementFunctionReferenceReturnType(EntityDefinition.class)
    public static ExpressionValue smethod_7(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RegulatorElementRunSpace regulatorElementRunSpace = (RegulatorElementRunSpace) absBaseRunSpace;
        if (regulatorElementRunSpace.getOutputFlowReceiversNumberItems() > 0) {
            return ExpressionValue.from(regulatorElementRunSpace.getEntityRunSpaceByIndex(0));
        }
        return null;
    }

    @ElementFunctionReferenceReturnType(EntityDefinition.class)
    @BaseElementFunction("OutputFlowReceivers.LastItem")
    public static ExpressionValue smethod_8(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RegulatorElementRunSpace regulatorElementRunSpace = (RegulatorElementRunSpace) absBaseRunSpace;
        if (regulatorElementRunSpace.getOutputFlowReceiversNumberItems() > 0) {
            return ExpressionValue.from(regulatorElementRunSpace.getEntityRunSpaceByIndex(regulatorElementRunSpace.getOutputFlowReceiversNumberItems() - 1));
        }
        return null;
    }

    @BaseElementFunction(value = "OutputFlowReceivers.ItemAtIndex", Arguments = {"index"})
    @ElementFunctionReferenceReturnType(EntityDefinition.class)
    public static ExpressionValue smethod_9(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                            ExpressionValue[] expressionValues) {
        RegulatorElementRunSpace regulatorElementRunSpace = (RegulatorElementRunSpace) absBaseRunSpace;
        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception ignored) {
            RuntimeErrorFullMessageDetails.reportError(runSpace,
                    MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                            regulatorElementRunSpace.HierarchicalDisplayName(), "OutputFlowReceivers.ItemAtIndex"));
            return null;
        }
        if (regulatorElementRunSpace.getOutputFlowReceiversNumberItems() < num || num < 1) {
            return null;
        }
        return ExpressionValue.from(regulatorElementRunSpace.getEntityRunSpaceByIndex(num - 1));
    }

    @BaseElementFunction(value = "OutputFlowReceivers.IndexOfItem", Arguments = {"entity"})
    public static double smethod_10(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                    ExpressionValue[] expressionValues) {
        RegulatorElementRunSpace regulatorElementRunSpace = (RegulatorElementRunSpace) absBaseRunSpace;
        EntityRunSpace entityRunSpace = null;
        try {
            entityRunSpace = (EntityRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (entityRunSpace != null) {
            for (int i = 0; i < regulatorElementRunSpace.getOutputFlowReceiversNumberItems(); i++) {
                if (regulatorElementRunSpace.getEntityRunSpaceByIndex(i) == entityRunSpace) {
                    return (double) (i + 1);
                }
            }
            return 0.0;
        }
        RuntimeErrorFullMessageDetails.reportError(runSpace,
                MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                        regulatorElementRunSpace.HierarchicalDisplayName(), "OutputFlowReceivers.IndexOfItem"));
        return Double.NaN;
    }

    @BaseElementFunction(value = "OutputFlowReceivers.Contains", Arguments = {"entity"})
    public static double smethod_11(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                    ExpressionValue[] expressionValues) {
        RegulatorElementRunSpace regulatorElementRunSpace = (RegulatorElementRunSpace) absBaseRunSpace;
        EntityRunSpace entityRunSpace = null;
        try {
            entityRunSpace = (EntityRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (entityRunSpace != null) {
            for (int i = 0; i < regulatorElementRunSpace.getOutputFlowReceiversNumberItems(); i++) {
                if (regulatorElementRunSpace.getEntityRunSpaceByIndex(i) == entityRunSpace) {
                    return 1.0;
                }
            }
            return 0.0;
        }
        RuntimeErrorFullMessageDetails.reportError(runSpace,
                MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                        regulatorElementRunSpace.HierarchicalDisplayName(), "OutputFlowReceivers.Contains"));
        return Double.NaN;
    }


}
