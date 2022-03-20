package org.licho.brain.concrete;


import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.enu.BooleanType;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.brainEnums.SwitchNumericConditions;
import org.licho.brain.utils.simu.TimerIntervalType;

/**
 *
 */
public class TimerDefinition extends AbsDefinition {
    public TimerDefinition(String name) {
        super(name);

        super.Description(Resources.ElementDescription_Timer());
        EnumPropertyDefinition intervalType = new EnumPropertyDefinition("IntervalType", TimerIntervalType.class);
        intervalType.DisplayName(Resources.Timer_IntervalType_DisplayName());
        intervalType.Description(Resources.Timer_IntervalType_Description());
        intervalType.DefaultString(TimerIntervalType.Time.toString());
        intervalType.CategoryName(Resources.ElementPropertyCategory_BasicLogic());

        ExpressionPropertyDefinition timeOffset = new ExpressionPropertyDefinition("TimeOffset");
        timeOffset.DisplayName(Resources.Timer_TimeOffset_DisplayName());
        timeOffset.Description(Resources.Timer_TimeOffset_Description());
        timeOffset.DefaultString("0.0");
        timeOffset.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        timeOffset.SwitchNumericProperty(intervalType);
        timeOffset.SwitchNumericValue(2.0);
        timeOffset.SwitchNumericCondition(SwitchNumericConditions.LessThan);
        timeOffset.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
        timeOffset.DefaultUnitSubType(0);

        ExpressionPropertyDefinition timeInterval = new ExpressionPropertyDefinition("TimeInterval");
        timeInterval.DisplayName(Resources.Timer_TimeInterval_DisplayName());
        timeInterval.Description(Resources.Timer_TimeInterval_Description());
        timeInterval.DefaultString("1.0");
        timeInterval.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        timeInterval.SwitchNumericProperty(intervalType);
        timeInterval.SwitchNumericValue(2.0);
        timeInterval.SwitchNumericCondition(SwitchNumericConditions.LessThan);
        timeInterval.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
        timeInterval.DefaultUnitSubType(0);

        StatePropertyDefinition stateVariableName = new StatePropertyDefinition("StateVariableName");
        stateVariableName.DisplayName(Resources.Timer_StateVariableName_DisplayName());
        stateVariableName.Description(Resources.Timer_StateVariableName_Description());
        stateVariableName.DefaultString("");
        stateVariableName.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        stateVariableName.SwitchNumericProperty(intervalType);
        stateVariableName.SwitchNumericValue(1.0);
        stateVariableName.SwitchNumericCondition(SwitchNumericConditions.Equal);
        stateVariableName.RequiredValue(false);

        ExpressionPropertyDefinition stateValue = new ExpressionPropertyDefinition("StateValue");
        stateValue.DisplayName(Resources.Timer_StateValue_DisplayName());
        stateValue.Description(Resources.Timer_StateValue_Description());
        stateValue.DefaultString("0");
        stateValue.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        stateValue.SwitchNumericProperty(intervalType);
        stateValue.SwitchNumericValue(1.0);
        stateValue.SwitchNumericCondition(SwitchNumericConditions.Equal);
        stateValue.UnitTypePropertyDefinition(stateVariableName);

        RateTablePropertyDefinition rateTable = new RateTablePropertyDefinition("RateTable");
        rateTable.DisplayName(Resources.Timer_RateTable_DisplayName());
        rateTable.DefaultString("");
        rateTable.Description(Resources.Timer_RateTable_Description());
        rateTable.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        rateTable.SwitchNumericProperty(intervalType);
        rateTable.SwitchNumericValue(2.0);
        rateTable.RequiredValue(false);

        ExpressionPropertyDefinition rateScaleFactor = new ExpressionPropertyDefinition("RateScaleFactor");
        rateScaleFactor.DisplayName(Resources.Timer_RateScaleFactor_DisplayName());
        rateScaleFactor.DefaultString("1.0");
        rateScaleFactor.Description(Resources.Timer_RateScaleFactor_Description());
        rateScaleFactor.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        rateScaleFactor.SwitchNumericProperty(intervalType);
        rateScaleFactor.SwitchNumericValue(2.0);

        EventPropertyDefinition triggeringEventName = new EventPropertyDefinition("TriggeringEventName");
        triggeringEventName.DisplayName(Resources.Timer_TriggeringEventName_DisplayName());
        triggeringEventName.Description(Resources.Timer_TriggeringEventName_Description());
        triggeringEventName.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        triggeringEventName.SwitchNumericProperty(intervalType);
        triggeringEventName.SwitchNumericValue(3.0);
        triggeringEventName.RequiredValue(false);
        triggeringEventName.DefaultString("");

        ExpressionPropertyDefinition triggeringEventCount = new ExpressionPropertyDefinition("TriggeringEventCount");
        triggeringEventCount.DisplayName(Resources.Timer_TriggeringEventCount_DisplayName());
        triggeringEventCount.DefaultString("1");
        triggeringEventCount.Description(Resources.Timer_TriggeringEventCount_Description());
        triggeringEventCount.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        triggeringEventCount.SwitchNumericProperty(intervalType);
        triggeringEventCount.SwitchNumericValue(3.0);

        TableAndColumnPropertyDefinition arrivalTimeProperty = new TableAndColumnPropertyDefinition(
        		"ArrivalTimeProperty");
        arrivalTimeProperty.DisplayName(Resources.Timer_ArrivalTimeProperty_DisplayName());
        arrivalTimeProperty.Description(Resources.Timer_ArrivalTimeProperty_Description());
        arrivalTimeProperty.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        arrivalTimeProperty.SwitchNumericProperty(intervalType);
        arrivalTimeProperty.SwitchNumericValue(4.0);
        arrivalTimeProperty.RequiredValue(false);
        arrivalTimeProperty.DefaultString("");

        ExpressionPropertyDefinition arrivalEventsPerTimeSlot = new ExpressionPropertyDefinition(
        		"ArrivalEventsPerTimeSlot");
        arrivalEventsPerTimeSlot.DisplayName(Resources.Timer_ArrivalEventsPerTimeSlot_DisplayName());
        arrivalEventsPerTimeSlot.DefaultString("1");
        arrivalEventsPerTimeSlot.Description(Resources.Timer_ArrivalEventsPerTimeSlot_Description());
        arrivalEventsPerTimeSlot.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        arrivalEventsPerTimeSlot.SwitchNumericProperty(intervalType);
        arrivalEventsPerTimeSlot.SwitchNumericValue(4.0);

        ExpressionPropertyDefinition arrivalTimeDeviation = new ExpressionPropertyDefinition("ArrivalTimeDeviation");
        arrivalTimeDeviation.DisplayName(Resources.Timer_ArrivalTimeDeviation_DisplayName());
        arrivalTimeDeviation.DefaultString("0.0");
        arrivalTimeDeviation.Description(Resources.Timer_ArrivalTimeDeviation_Description());
        arrivalTimeDeviation.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        arrivalTimeDeviation.SwitchNumericProperty(intervalType);
        arrivalTimeDeviation.SwitchNumericValue(4.0);
        arrivalTimeDeviation.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
        arrivalTimeDeviation.DefaultUnitSubType(0);

        ExpressionPropertyDefinition arrivalNoShowProbability = new ExpressionPropertyDefinition(
        		"ArrivalNoShowProbability");
        arrivalNoShowProbability.DisplayName(Resources.Timer_ArrivalNoShowProbability_DisplayName());
        arrivalNoShowProbability.DefaultString("0.0");
        arrivalNoShowProbability.Description(Resources.Timer_ArrivalNoShowProbability_Description());
        arrivalNoShowProbability.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        arrivalNoShowProbability.SwitchNumericProperty(intervalType);
        arrivalNoShowProbability.SwitchNumericValue(4.0);
        EnumPropertyDefinition repeatArrivalPattern = new EnumPropertyDefinition("RepeatArrivalPattern",
                BooleanType.class);
        repeatArrivalPattern.DisplayName(Resources.Timer_RepeatArrivalPattern_DisplayName());
        repeatArrivalPattern.Description(Resources.Timer_RepeatArrivalPattern_Description());
        repeatArrivalPattern.CategoryName(Resources.ElementPropertyCategory_BasicLogic());
        repeatArrivalPattern.SwitchNumericProperty(intervalType);
        repeatArrivalPattern.SwitchNumericValue(4.0);
        repeatArrivalPattern.DefaultString("False");

        ExpressionPropertyDefinition maximumEvents = new ExpressionPropertyDefinition("MaximumEvents");
        maximumEvents.DisplayName(Resources.Timer_MaximumEvents_DisplayName());
        maximumEvents.DefaultString("Infinity");
        maximumEvents.Description(Resources.Timer_MaximumEvents_Description());
        maximumEvents.CategoryName(Resources.ElementPropertyCategory_StoppingConditions());

        ExpressionPropertyDefinition maximumTime = new ExpressionPropertyDefinition("MaximumTime");
        maximumTime.DisplayName(Resources.Timer_MaximumTime_DisplayName());
        maximumTime.DefaultString("Infinity");
        maximumTime.Description(Resources.Timer_MaximumTime_Description());
        maximumTime.CategoryName(Resources.ElementPropertyCategory_StoppingConditions());
        maximumTime.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Time);
        maximumTime.DefaultUnitSubType(0);

        EventPropertyDefinition resetEvent = new EventPropertyDefinition("ResetEvent");
        resetEvent.DisplayName(Resources.Timer_ResetEvent_DisplayName());
        resetEvent.Description(Resources.Timer_ResetEvent_Description());
        resetEvent.SwitchNumericProperty(intervalType);
        resetEvent.SwitchNumericCondition(SwitchNumericConditions.NotEqual);
        resetEvent.SwitchNumericValue(4.0);
        resetEvent.RequiredValue(false);
        resetEvent.DefaultString("");
        resetEvent.CategoryName(Resources.ElementPropertyCategory_AdvancedOptions());
        resetEvent.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition initiallyEnabled = new EnumPropertyDefinition("InitiallyEnabled", BooleanType.class);
        initiallyEnabled.DisplayName(Resources.Timer_InitiallyEnabled_DisplayName());
        initiallyEnabled.Description(Resources.Timer_InitiallyEnabled_Description());
        initiallyEnabled.CategoryName(Resources.ElementPropertyCategory_AdvancedOptions());
        initiallyEnabled.DefaultString("True");
        initiallyEnabled.ComplexityLevel(ProductComplexityLevel.Advanced);

        super.getPropertyDefinitions().add(intervalType);
        super.getPropertyDefinitions().add(timeOffset);
        super.getPropertyDefinitions().add(timeInterval);
        super.getPropertyDefinitions().add(stateVariableName);
        super.getPropertyDefinitions().add(stateValue);
        super.getPropertyDefinitions().add(rateTable);
        super.getPropertyDefinitions().add(rateScaleFactor);
        super.getPropertyDefinitions().add(triggeringEventName);
        super.getPropertyDefinitions().add(triggeringEventCount);
        super.getPropertyDefinitions().add(arrivalTimeProperty);
        super.getPropertyDefinitions().add(arrivalEventsPerTimeSlot);
        super.getPropertyDefinitions().add(arrivalTimeDeviation);
        super.getPropertyDefinitions().add(arrivalNoShowProbability);
        super.getPropertyDefinitions().add(repeatArrivalPattern);
        super.getPropertyDefinitions().add(maximumEvents);
        super.getPropertyDefinitions().add(maximumTime);
        super.getPropertyDefinitions().add(resetEvent);
        super.getPropertyDefinitions().add(initiallyEnabled);

        BaseStatePropertyObject enabled = new BaseStatePropertyObject("Enabled", false, false, NumericDataType.Boolean);
        enabled.Description( Resources.Timer_Enabled_Description());
        CostStatePropertyObject elapsedTime = new CostStatePropertyObject("ElapsedTime", true, false);
        elapsedTime.Description( Resources.Timer_ElapsedTime_Description());
        super.getStateDefinitions().addStateProperty(enabled);
        super.getStateDefinitions().addStateProperty(elapsedTime);
        EventDefinition eventDefinitions = new EventDefinition("Event", false);
        eventDefinitions.Description( Resources.Timer_Event_Description());
        super.getEventDefinitions().InsertEventDefinition(eventDefinitions);
    }

    @Override
    public Class<?> ElementType() {
        return null;
    }

    @Override
    public Class<?> RunSpaceType() {
        return null;
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return null;
    }
}
