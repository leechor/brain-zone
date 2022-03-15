package org.licho.brain.concrete;

import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.annotation.ElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.element.Station;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainEnums.QueueRanking;
import org.licho.brain.brainEnums.SwitchNumericConditions;

/**
 *
 */
public class StationDefinition extends AbsDefinition {
    private static StationDefinition stationDefinition = new StationDefinition();
    private BaseStatePropertyObject baseState;

    public StationDefinition() {
        super("Station");
        ExpressionPropertyDefinition initialCapacity = new ExpressionPropertyDefinition("InitialCapacity");
        initialCapacity.Description(EngineResources.Station_InitialCapacity_Description);
        initialCapacity.DisplayName(EngineResources.Station_InitialCapacity_DisplayName);
        initialCapacity.DefaultString("Infinity");
        initialCapacity.CategoryName(EngineResources.CategoryName_BasicLogic);
        EnumPropertyDefinition entryRankingRule = new EnumPropertyDefinition("EntryRankingRule", QueueRanking.class);
        entryRankingRule.Description(EngineResources.Station_EntryRankingRule_Description);
        entryRankingRule.DisplayName(EngineResources.Station_EntryRankingRule_DisplayName);
        entryRankingRule.DefaultString(QueueRanking.FirstInFirstOut.toString());
        entryRankingRule.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        entryRankingRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition entryRankingExpression = new ExpressionPropertyDefinition(
                "EntryRankingExpression");
        entryRankingExpression.DisplayName(EngineResources.Station_EntryRankingExpression_DisplayName);
        entryRankingExpression.Description(EngineResources.Station_EntryRankingExpression_Description);
        entryRankingExpression.DefaultString("Entity.Priority");
        entryRankingExpression.ParentPropertyDefinition(entryRankingRule);
        entryRankingExpression.SwitchNumericProperty(entryRankingRule);
        entryRankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        entryRankingExpression.SwitchNumericValue(1.0);
        entryRankingExpression.RequiredValue(false);
        entryRankingExpression.ComplexityLevel(ProductComplexityLevel.Advanced);
        SelectionRulePropertyDefinition dynamicSelectionRule = new SelectionRulePropertyDefinition(
                "DynamicSelectionRule");
        dynamicSelectionRule.DisplayName(EngineResources.Station_DynamicSelectionRule_DisplayName);
        dynamicSelectionRule.Description(EngineResources.Station_DynamicSelectionRule_Description);
        dynamicSelectionRule.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        dynamicSelectionRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition contentsRankingRule = new EnumPropertyDefinition("ContentsRankingRule",
                QueueRanking.class);
        contentsRankingRule.DisplayName(EngineResources.Station_ContentsRankingRule_DisplayName);
        contentsRankingRule.Description(EngineResources.Station_ContentsRankingRule_Description);
        contentsRankingRule.DefaultString(QueueRanking.FirstInFirstOut.toString());
        contentsRankingRule.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        contentsRankingRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition contentsRankingExpression = new ExpressionPropertyDefinition(
                "ContentsRankingExpression");
        contentsRankingExpression.DisplayName(EngineResources.Station_ContentsRankingExpression_DisplayName);
        contentsRankingExpression.Description(EngineResources.Station_ContentsRankingExpression_Description);
        contentsRankingExpression.DefaultString("Entity.Priority");
        contentsRankingExpression.ParentPropertyDefinition(contentsRankingRule);
        contentsRankingExpression.SwitchNumericProperty(contentsRankingRule);
        contentsRankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        contentsRankingExpression.SwitchNumericValue(1.0);
        contentsRankingExpression.RequiredValue(false);
        contentsRankingExpression.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition redirectStation = new ElementPropertyDefinition("RedirectStation", Station.class);
        redirectStation.Description(EngineResources.Station_RedirectStation_Description);
        redirectStation.DisplayName(EngineResources.Station_RedirectStation_DisplayName);
        redirectStation.DefaultString("");
        redirectStation.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        redirectStation.RequiredValue(false);
        redirectStation.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition parentCostCenter = new ElementPropertyDefinition("ParentCostCenter",
                CostCenter.class);
        parentCostCenter.DisplayName(EngineResources.Station_ParentCostCenter_DisplayName);
        parentCostCenter.Description(EngineResources.Station_ParentCostCenter_Description);
        parentCostCenter.DefaultString("");
        parentCostCenter.CategoryName(EngineResources.CategoryName_Financials);
        parentCostCenter.RequiredValue(false);
        parentCostCenter.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition costPerUse = new ExpressionPropertyDefinition("CostPerUse");
        costPerUse.DisplayName(EngineResources.Station_CostPerUse_DisplayName);
        costPerUse.Description(EngineResources.Station_CostPerUse_Description);
        costPerUse.DefaultString("0.0");
        costPerUse.CategoryName(EngineResources.CategoryName_Financials);
        costPerUse.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Currency);
        costPerUse.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition holdingCostRate = new ExpressionPropertyDefinition("HoldingCostRate");
        holdingCostRate.DisplayName(EngineResources.Station_HoldingCostRate_DisplayName);
        holdingCostRate.Description(EngineResources.Station_HoldingCostRate_Description);
        holdingCostRate.DefaultString("0.0");
        holdingCostRate.CategoryName(EngineResources.CategoryName_Financials);
        holdingCostRate.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.CurrencyPerTimeUnit);
        holdingCostRate.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(initialCapacity);
        super.getPropertyDefinitions().add(entryRankingRule);
        super.getPropertyDefinitions().add(entryRankingExpression);
        super.getPropertyDefinitions().add(dynamicSelectionRule);
        super.getPropertyDefinitions().add(contentsRankingRule);
        super.getPropertyDefinitions().add(contentsRankingExpression);
        super.getPropertyDefinitions().add(redirectStation);
        super.getPropertyDefinitions().add(parentCostCenter);
        super.getPropertyDefinitions().add(costPerUse);
        super.getPropertyDefinitions().add(holdingCostRate);
        BaseStatePropertyObject transferInState = new BaseStatePropertyObject("TransferInState", true, false,
                NumericDataType.Integer);
        transferInState.Description(EngineResources.Station_TransferInState_Description);
        EntityObjectQueueState contents = new EntityObjectQueueState("Contents");
        contents.Description(EngineResources.Station_ContentsQueue_Description);
        QueueStateObject<StationEntryAssociatedObjectRunSpace> entryQueue = new QueueStateObject<>("EntryQueue");
        entryQueue.Description(EngineResources.Station_EntryQueue_Description);
        entryQueue.CanRemove(true);
        BaseStatePropertyObject currentCapacity = new BaseStatePropertyObject("CurrentCapacity", false, false,
                NumericDataType.Integer);
        currentCapacity.Description(EngineResources.Station_CurrentCapacity_Description);
        CostStatePropertyObject cost = new CostStatePropertyObject("Cost", true, false);
        cost.Description(EngineResources.Station_CostState_Description);
        cost.UnitType(UnitType.Currency);
        cost.updateParameter(0, EngineResources.Station_CostRateParameter_Description);
        super.getStateDefinitions().addStateProperty(transferInState);
        super.getStateDefinitions().addStateProperty(contents);
        super.getStateDefinitions().addStateProperty(entryQueue);
        super.getStateDefinitions().addStateProperty(currentCapacity);
        super.getStateDefinitions().addStateProperty(cost);
        EventDefinition entered = new EventDefinition("Entered", false);
        entered.Description(EngineResources.Station_Entered_Description);
        EventDefinition exited = new EventDefinition("Exited", false);
        exited.Description(EngineResources.Station_Exited_Description);
        EventDefinition capacityChanged = new EventDefinition("CapacityChanged", false);
        capacityChanged.Description(EngineResources.Station_CapacityChanged_Description);
        super.getEventDefinitions().InsertEventDefinition(entered);
        super.getEventDefinitions().InsertEventDefinition(exited);
        super.getEventDefinitions().InsertEventDefinition(capacityChanged);
    }

    public static Station createStation(String name) {
        return (Station) StationDefinition.stationDefinition.CreateInstance(name);

    }

    public static Station create(String name) {
        return (Station) StationDefinition.stationDefinition.CreateInstance(name);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Station(this, name, ElementScope.Private);
    }

    @Override
    public Class<?> ElementType() {
        return Station.class;
    }

    @Override
    public Class<?> RunSpaceType() {
        return RideStation.class;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return StationDefinition.stationDefinition;
    }

    private BaseStatePropertyObject getBaseStateProperty() {
        if (this.baseState == null) {
            StateDefinitions source = super.getStateDefinitions();
            this.baseState = source.StateProperties.values.stream()
                    .filter(t -> StringHelper.equalsLocal(t.Name(), "Contents"))
                    .findFirst()
                    .orElse(null);
        }
        return this.baseState;
    }

    @Override
    public BaseStatePropertyObject StateForBinding(String name) {
        if (name.equalsIgnoreCase("InProcess")) {
            return this.getBaseStateProperty();
        }
        return super.StateForBinding(name);
    }

    @BaseElementFunction("NumberEntered")
    public static double smethod_7(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.NumberEntered();
    }

    @BaseElementFunction("NumberExited")
    public static double smethod_8(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.NumberExited();
    }

    @BaseElementFunction("Capacity")
    public static double smethod_9(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.CurrentCapacity().DoubleValue();
    }

    @BaseElementFunction("Capacity.Initial")
    public static double smethod_10(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.InitialCapacity();
    }

    @BaseElementFunction("Capacity.Previous")
    public static double getLastCapacity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.getCapacityPrevious();
    }

    @BaseElementFunction("Capacity.Remaining")
    public static double getRemainingCapacity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.RideCapacityRemaining();
    }

    @BaseElementFunction("Capacity.Average")
    public static double smethod_13(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.getCapacityAverage();
    }

    @BaseElementFunction("Capacity.Minimum")
    public static double smethod_14(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.getCapacityMinimum();
    }

    @BaseElementFunction("Capacity.Maximum")
    public static double smethod_15(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.getCapacityMaximum();
    }

    @ElementFunction("RemainingCapacity")
    public static double smethod_16(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return StationDefinition.getRemainingCapacity(absBaseRunSpace, runSpace);
    }

    @ElementFunction("LastCapacity")
    public static double smethod_17(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return StationDefinition.getLastCapacity(absBaseRunSpace, runSpace);
    }

    @ElementFunction("AverageInProcess")
    public static double smethod_18(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.getQueueGridItemTrace().AverageNumberWaiting();
    }

    @ElementFunction("MaximumInProcess")
    public static double smethod_19(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        RideStation rideStation = (RideStation) absBaseRunSpace;
        return rideStation.getQueueGridItemTrace().MaximumNumberWaiting();
    }

    @ElementFunction("NumberArrivals")
    public static double smethod_20(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return StationDefinition.smethod_7(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberDepartures")
    public static double smethod_21(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return StationDefinition.smethod_8(absBaseRunSpace, runSpace);
    }
}
