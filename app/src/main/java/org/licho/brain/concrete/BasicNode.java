package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainEnums.SwitchNumericConditions;
import org.licho.brain.brainEnums.TallyValueType;
import org.licho.brain.utils.simu.system.Color;

/**
 *
 */
public class BasicNode {


    public static void init(InternalReference internalReference, ActiveModel activeModel, NodeDefinition origin) {
        NodeDefinition nodeDefinition = new NodeDefinition("BasicNode", activeModel, origin);
        internalReference.updateSameIntelligentObjectDefinition(nodeDefinition);
        BasicNode.init(nodeDefinition);
    }

    public static void init(NodeDefinition nodeDefinition) {
        nodeDefinition.setColor(Color.Gray);
        nodeDefinition.Description(Resources.BasicNode_Description);
        nodeDefinition.ResourceLogic(false);
        nodeDefinition.IntelligentObject = (IntelligentObject) nodeDefinition.CreateInstance("BasicNode");
        nodeDefinition.getPropertyDefinitions().getPropertyDefinitionList().clear();
        PropertyDefinitionFacade crossingLogic = new PropertyDefinitionFacade();
        crossingLogic.Name(Resources.CategoryName_CrossingLogic);
        crossingLogic.Description(Resources.CategoryName_CrossingLogic);
        crossingLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade routingLogic = new PropertyDefinitionFacade();
        routingLogic.Name(Resources.CategoryName_RoutingLogic);
        routingLogic.Description(Resources.CategoryName_RoutingLogic);
        routingLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade tallyStatistics = new PropertyDefinitionFacade();
        tallyStatistics.Name(Resources.CategoryName_TallyStatistics);
        tallyStatistics.Description(Resources.CategoryDescription_TallyStatistics);
        tallyStatistics.InitiallyExpanded(false);

        PropertyDefinitionFacade addOnProcessTriggers = new PropertyDefinitionFacade();
        addOnProcessTriggers.Name(Resources.CategoryName_AddOnProcessTriggers);
        addOnProcessTriggers.Description(Resources.CategoryName_AddOnProcessTriggers);
        addOnProcessTriggers.InitiallyExpanded(false);

        PropertyDefinitionFacade financials = new PropertyDefinitionFacade();
        financials.Name(Resources.CategoryName_Financials);
        financials.Description(Resources.CategoryName_Financials);
        financials.InitiallyExpanded(false);

        PropertyDefinitionFacade advancedOptions = new PropertyDefinitionFacade();
        advancedOptions.Name(Resources.CategoryName_AdvancedOptions);
        advancedOptions.Description(Resources.CategoryName_AdvancedOptions);
        advancedOptions.InitiallyExpanded(false);

        nodeDefinition.getPropertyDefinitions().getPropertyDefinitionList().add(crossingLogic);
        nodeDefinition.getPropertyDefinitions().getPropertyDefinitionList().add(routingLogic);
        nodeDefinition.getPropertyDefinitions().getPropertyDefinitionList().add(tallyStatistics);
        nodeDefinition.getPropertyDefinitions().getPropertyDefinitionList().add(addOnProcessTriggers);
        nodeDefinition.getPropertyDefinitions().getPropertyDefinitionList().add(financials);
        nodeDefinition.getPropertyDefinitions().getPropertyDefinitionList().add(advancedOptions);

        PropertyDefinitionFacade nestedBasicLogic = new PropertyDefinitionFacade();
        nestedBasicLogic.Name(Resources.CategoryName_NestedBasicLogic);
        nestedBasicLogic.Description(Resources.CategoryDescription_NestedTallyStatisticsBasicLogic);
        nestedBasicLogic.InitiallyExpanded(true);
        StringList stringList = nodeDefinition.getStringList(null);
        stringList.InstanceName("BasicNodeTallyConditionType");
        stringList.initItems(new String[]{
                "NoCondition",
                "IsEntity",
                "IsTransporter",
                "CustomCondition"
        });

        RepeatingPropertyDefinition talliesOnEntering = new RepeatingPropertyDefinition("TalliesOnEntering",
				nodeDefinition);
        talliesOnEntering.RequiredValue(false);
        talliesOnEntering.DisplayName(Resources.AllNodes_TallyStatisticsOnEntering_DisplayName);
        talliesOnEntering.Description(Resources.AllNodes_TallyStatisticsOnEntering_Description);
        talliesOnEntering.CategoryName(tallyStatistics.Name());
        talliesOnEntering.propertyDefinitions.addPropertyDefinition(nestedBasicLogic);

        ListPropertyDefinition talliesOnEnteringTallyConditionType = new ListPropertyDefinition(
				"TalliesOnEnteringTallyConditionType", stringList);
        talliesOnEnteringTallyConditionType.DisplayName(Resources.AllNodes_TallyStatistics_TallyConditionType_DisplayName);
        talliesOnEnteringTallyConditionType.Description(Resources.AllNodes_TallyStatistics_TallyConditionType_Description);
        talliesOnEnteringTallyConditionType.CategoryName(nestedBasicLogic.Name());
        talliesOnEnteringTallyConditionType.DefaultString("Entity Entering");
        talliesOnEnteringTallyConditionType.RequiredValue(true);
        talliesOnEnteringTallyConditionType.displays = new String[]
                {
                        "No Condition",
                        "Entity Entering",
                        "Transporter Entering",
                        "Custom Condition"
                };
        ExpressionPropertyDefinition talliesOnEnteringTallyCondition = new ExpressionPropertyDefinition(
				"TalliesOnEnteringTallyCondition");
        talliesOnEnteringTallyCondition.DisplayName(Resources.AllNodes_TallyStatistics_TallyCondition_DisplayName);
        talliesOnEnteringTallyCondition.Description(Resources.AllNodes_TallyStatistics_TallyCondition_Description);
        talliesOnEnteringTallyCondition.DefaultString("");
        talliesOnEnteringTallyCondition.ParentPropertyDefinition(talliesOnEnteringTallyConditionType);
        talliesOnEnteringTallyCondition.RequiredValue(true);
        talliesOnEnteringTallyCondition.InfinityInList(false);
        talliesOnEnteringTallyCondition.SwitchNumericProperty(talliesOnEnteringTallyConditionType);
        talliesOnEnteringTallyCondition.SwitchNumericCondition(SwitchNumericConditions.Equal);
        talliesOnEnteringTallyCondition.SwitchNumericValue(3.0);
        ElementPropertyDefinition talliesOnEnteringTallyStatisticName = new ElementPropertyDefinition(
                "TalliesOnEnteringTallyStatisticName", TallyStatistic.class);
        talliesOnEnteringTallyStatisticName.DisplayName(Resources.AllNodes_TallyStatistics_TallyStatisticName_DisplayName);
        talliesOnEnteringTallyStatisticName.Description(Resources.AllNodes_TallyStatistics_TallyStatisticName_Description);
        talliesOnEnteringTallyStatisticName.DefaultString("");
        talliesOnEnteringTallyStatisticName.CategoryName(nestedBasicLogic.Name());
        talliesOnEnteringTallyStatisticName.RequiredValue(false);
        EnumPropertyDefinition talliesOnEnteringValueType = new EnumPropertyDefinition("TalliesOnEnteringValueType",
                TallyValueType.class);
        talliesOnEnteringValueType.DisplayName(Resources.AllNodes_TallyStatistics_ValueType_DisplayName);
        talliesOnEnteringValueType.Description(Resources.AllNodes_TallyStatistics_ValueType_Description);
        talliesOnEnteringValueType.DefaultString(TallyValueType.Expression.toString());
        talliesOnEnteringValueType.CategoryName(nestedBasicLogic.Name());
        ExpressionPropertyDefinition talliesOnEnteringValue = new ExpressionPropertyDefinition(
				"TalliesOnEnteringValue");
        talliesOnEnteringValue.DisplayName(Resources.AllNodes_TallyStatistics_Value_DisplayName);
        talliesOnEnteringValue.Description(Resources.AllNodes_TallyStatistics_Value_Description);
        talliesOnEnteringValue.DefaultString("0.0");
        talliesOnEnteringValue.CategoryName(nestedBasicLogic.Name());
        talliesOnEnteringValue.SwitchNumericProperty(talliesOnEnteringValueType);
        talliesOnEnteringValue.SwitchNumericValue(0.0);
        talliesOnEnteringValue.UnitTypePropertyDefinition(talliesOnEnteringTallyStatisticName);
        talliesOnEntering.propertyDefinitions.add(talliesOnEnteringTallyConditionType);
        talliesOnEntering.propertyDefinitions.add(talliesOnEnteringTallyCondition);
        talliesOnEntering.propertyDefinitions.add(talliesOnEnteringTallyStatisticName);
        talliesOnEntering.propertyDefinitions.add(talliesOnEnteringValueType);
        talliesOnEntering.propertyDefinitions.add(talliesOnEnteringValue);
        RepeatingPropertyDefinition talliesOnExited = new RepeatingPropertyDefinition("TalliesOnExited",
				nodeDefinition);
        talliesOnExited.RequiredValue(false);
        talliesOnExited.DisplayName(Resources.AllNodes_TallyStatisticsOnExited_DisplayName);
        talliesOnExited.Description(Resources.AllNodes_TallyStatisticsOnExited_Description);
        talliesOnExited.CategoryName(tallyStatistics.Name());
        talliesOnExited.propertyDefinitions.addPropertyDefinition(nestedBasicLogic);
        ListPropertyDefinition talliesOnExitedTallyConditionType = new ListPropertyDefinition(
				"TalliesOnExitedTallyConditionType", stringList);
        talliesOnExitedTallyConditionType.DisplayName(Resources.AllNodes_TallyStatistics_TallyConditionType_DisplayName);
        talliesOnExitedTallyConditionType.Description(Resources.AllNodes_TallyStatistics_TallyConditionType_Description);
        talliesOnExitedTallyConditionType.CategoryName(nestedBasicLogic.Name());
        talliesOnExitedTallyConditionType.DefaultString("Entity Exited");
        talliesOnExitedTallyConditionType.RequiredValue(true);
        talliesOnExitedTallyConditionType.displays = new String[]
                {
                        "No Condition",
                        "Entity Exited",
                        "Transporter Exited",
                        "Custom Condition"
                };
        ExpressionPropertyDefinition talliesOnExitedTallyCondition = new ExpressionPropertyDefinition(
				"TalliesOnExitedTallyCondition");
        talliesOnExitedTallyCondition.DisplayName(Resources.AllNodes_TallyStatistics_TallyCondition_DisplayName);
        talliesOnExitedTallyCondition.Description(Resources.AllNodes_TallyStatistics_TallyCondition_Description);
        talliesOnExitedTallyCondition.DefaultString("");
        talliesOnExitedTallyCondition.ParentPropertyDefinition(talliesOnExitedTallyConditionType);
        talliesOnExitedTallyCondition.RequiredValue(true);
        talliesOnExitedTallyCondition.InfinityInList(false);
        talliesOnExitedTallyCondition.SwitchNumericProperty(talliesOnExitedTallyConditionType);
        talliesOnExitedTallyCondition.SwitchNumericCondition(SwitchNumericConditions.Equal);
        talliesOnExitedTallyCondition.SwitchNumericValue(3.0);
        ElementPropertyDefinition talliesOnExitedTallyStatisticName = new ElementPropertyDefinition(
                "TalliesOnExitedTallyStatisticName", TallyStatistic.class);
        talliesOnExitedTallyStatisticName.DisplayName(Resources.AllNodes_TallyStatistics_TallyStatisticName_DisplayName);
        talliesOnExitedTallyStatisticName.Description(Resources.AllNodes_TallyStatistics_TallyStatisticName_Description);
        talliesOnExitedTallyStatisticName.DefaultString("");
        talliesOnExitedTallyStatisticName.CategoryName(nestedBasicLogic.Name());
        talliesOnExitedTallyStatisticName.RequiredValue(false);
        EnumPropertyDefinition talliesOnExitedValueType = new EnumPropertyDefinition("TalliesOnExitedValueType",
                TallyValueType.class);
        talliesOnExitedValueType.DisplayName(Resources.AllNodes_TallyStatistics_ValueType_DisplayName);
        talliesOnExitedValueType.Description(Resources.AllNodes_TallyStatistics_ValueType_Description);
        talliesOnExitedValueType.DefaultString(TallyValueType.Expression.toString());
        talliesOnExitedValueType.CategoryName(nestedBasicLogic.Name());
        ExpressionPropertyDefinition talliesOnExitedValue = new ExpressionPropertyDefinition("TalliesOnExitedValue");
        talliesOnExitedValue.DisplayName(Resources.AllNodes_TallyStatistics_Value_DisplayName);
        talliesOnExitedValue.Description(Resources.AllNodes_TallyStatistics_Value_Description);
        talliesOnExitedValue.DefaultString("0.0");
        talliesOnExitedValue.CategoryName(nestedBasicLogic.Name());
        talliesOnExitedValue.SwitchNumericProperty(talliesOnExitedValueType);
        talliesOnExitedValue.SwitchNumericValue(0.0);
        talliesOnExitedValue.UnitTypePropertyDefinition(talliesOnExitedTallyStatisticName);
        talliesOnExited.propertyDefinitions.add(talliesOnExitedTallyConditionType);
        talliesOnExited.propertyDefinitions.add(talliesOnExitedTallyCondition);
        talliesOnExited.propertyDefinitions.add(talliesOnExitedTallyStatisticName);
        talliesOnExited.propertyDefinitions.add(talliesOnExitedValueType);
        talliesOnExited.propertyDefinitions.add(talliesOnExitedValue);
        ElementPropertyDefinition runInitializedAddOnProcess = new ElementPropertyDefinition(
                "RunInitializedAddOnProcess", ProcessProperty.class);
        runInitializedAddOnProcess.Description(Resources.All_RunInitializedAddOnProcess_Description);
        runInitializedAddOnProcess.DisplayName(Resources.All_RunInitializedAddOnProcess_DisplayName);
        runInitializedAddOnProcess.DefaultString("");
        runInitializedAddOnProcess.CategoryName(addOnProcessTriggers.Name());
        runInitializedAddOnProcess.RequiredValue(false);
        ElementPropertyDefinition runEndingAddOnProcess = new ElementPropertyDefinition("RunEndingAddOnProcess",
                ProcessProperty.class);
        runEndingAddOnProcess.Description(Resources.All_RunEndingAddOnProcess_Description);
        runEndingAddOnProcess.DisplayName(Resources.All_RunEndingAddOnProcess_DisplayName);
        runEndingAddOnProcess.DefaultString("");
        runEndingAddOnProcess.CategoryName(addOnProcessTriggers.Name());
        runEndingAddOnProcess.RequiredValue(false);
        ElementPropertyDefinition enteredAddOnProcess = new ElementPropertyDefinition("EnteredAddOnProcess",
                ProcessProperty.class);
        enteredAddOnProcess.Description(Resources.BasicNode_EnteredAddOnProcess_Description);
        enteredAddOnProcess.DisplayName(Resources.BasicNode_EnteredAddOnProcess_DisplayName);
        enteredAddOnProcess.DefaultString("");
        enteredAddOnProcess.CategoryName(addOnProcessTriggers.Name());
        enteredAddOnProcess.RequiredValue(false);
        ElementPropertyDefinition exitedAddOnProcess = new ElementPropertyDefinition("ExitedAddOnProcess",
                ProcessProperty.class);
        exitedAddOnProcess.Description(Resources.BasicNode_ExitedAddOnProcess_Description);
        exitedAddOnProcess.DisplayName(Resources.BasicNode_ExitedAddOnProcess_DisplayName);
        exitedAddOnProcess.DefaultString("");
        exitedAddOnProcess.CategoryName(addOnProcessTriggers.Name());
        exitedAddOnProcess.RequiredValue(false);
        ExpressionPropertyDefinition randomNumberStream = new ExpressionPropertyDefinition("RandomNumberStream");
        randomNumberStream.DisplayName(Resources.AllNodes_RandomNumberStream_DisplayName);
        randomNumberStream.Description(Resources.AllNodes_RandomNumberStream_Description);
        randomNumberStream.DefaultString("0");
        randomNumberStream.CategoryName(advancedOptions.Name());
        randomNumberStream.RequiredValue(false);
        ExternalNodePropertyDefinition boundExternalOutputNode = new ExternalNodePropertyDefinition(
                "BoundExternalOutputNode");
        boundExternalOutputNode.DisplayName(Resources.BasicNode_BoundExternalOutputNode_DisplayName);
        boundExternalOutputNode.Description(Resources.BasicNode_BoundExternalOutputNode_Description);
        boundExternalOutputNode.CategoryName(advancedOptions.Name());
        boundExternalOutputNode.DefaultString("");
        boundExternalOutputNode.RequiredValue(false);
        PropertyDefinitions propertyDefinitions = nodeDefinition.getPropertyDefinitions();
        propertyDefinitions.add(talliesOnEntering);
        propertyDefinitions.add(talliesOnExited);
        propertyDefinitions.add(runInitializedAddOnProcess);
        propertyDefinitions.add(runEndingAddOnProcess);
        propertyDefinitions.add(enteredAddOnProcess);
        propertyDefinitions.add(exitedAddOnProcess);
        propertyDefinitions.add(randomNumberStream);
        propertyDefinitions.add(boundExternalOutputNode);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("ParentCostCenter").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("InitialCost").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("InitialCostRate").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("EntryExemptionCondition").SetLocalVisible(false,
                propertyDefinitions);
        StringPropertyDefinition reportStatistics = propertyDefinitions.findStringPropertyDefinitionInfoByName("ReportStatistics");
        reportStatistics.SetLocalDefaultString("False", propertyDefinitions);
        nodeDefinition.CheckBaseForAdvancedProperties(true);
        nodeDefinition.process(t -> t.ComplexityLevel(ProductComplexityLevel.Advanced));
		EventDefinition entered = new EventDefinition("Entered", false);
		entered.Description(Resources.BasicNode_EnteredEvent_Description);
		EventDefinition exited = new EventDefinition("Exited", false);
		exited.Description(Resources.BasicNode_ExitedEvent_Description);
		EventDefinitions eventDefinitions = nodeDefinition.getEventDefinitions();
		eventDefinitions.InsertEventDefinition(entered);
		eventDefinitions.InsertEventDefinition(exited);
		IStepExecutor begin = StepExecutor.create(nodeDefinition, "TransferFailureLogic", ElementScope.Private, null,
                "Token").Begin();
        
        // TODO: 2022/2/15
    }

}
