package org.licho.brain.concrete;

import org.licho.brain.resource.Image;
import org.licho.brain.utils.simu.system.CultureInfo;
import org.licho.brain.utils.simu.system.ResourceManager;

import java.util.Objects;

/**
 *
 */
public class Resources {

    public static String CantCloseProjectBecauseModelRunning = "";
    public static String AskSaveCurrentProject = "";
    public static String AskSaveCurrentProjectWithName = "";
    public static String RunSetupEndTimeBeforeStartTime = "";
    public static String SimBitsViewTitle = "";
    public static String StartViewTitle = "";
    public static String ProjectViewTitle = "";
    public static String BasicNode_Description = "";
    public static String CategoryName_CrossingLogic = "";
    public static String CategoryName_RoutingLogic = "";
    public static String CategoryName_TallyStatistics = "";
    public static String CategoryDescription_TallyStatistics = "";
    public static String CategoryName_AddOnProcessTriggers = "";
    public static String CategoryName_Financials = "";
    public static String CategoryName_AdvancedOptions = "";
    public static String CategoryName_NestedBasicLogic = "";
    public static String CategoryDescription_NestedTallyStatisticsBasicLogic = "";
    public static String AllNodes_TallyStatisticsOnEntering_DisplayName = "";
    public static String AllNodes_TallyStatisticsOnEntering_Description = "";
    public static String AllNodes_TallyStatistics_TallyConditionType_DisplayName = "";
    public static String AllNodes_TallyStatistics_TallyConditionType_Description = "";
    public static String AllNodes_TallyStatistics_TallyCondition_DisplayName = "";
    public static String AllNodes_TallyStatistics_TallyCondition_Description = "";
    public static String AllNodes_TallyStatistics_TallyStatisticName_DisplayName = "";
    public static String AllNodes_TallyStatistics_TallyStatisticName_Description = "";
    public static String AllNodes_TallyStatistics_ValueType_DisplayName = "";
    public static String AllNodes_TallyStatistics_ValueType_Description = "";
    public static String AllNodes_TallyStatistics_Value_DisplayName = "";
    public static String AllNodes_TallyStatistics_Value_Description = "";
    public static String AllNodes_TallyStatisticsOnExited_DisplayName = "";
    public static String AllNodes_TallyStatisticsOnExited_Description = "";
    public static String All_RunInitializedAddOnProcess_Description = "";
    public static String All_RunInitializedAddOnProcess_DisplayName = "";
    public static String All_RunEndingAddOnProcess_Description = "";
    public static String All_RunEndingAddOnProcess_DisplayName = "";
    public static String BasicNode_EnteredAddOnProcess_Description = "";
    public static String BasicNode_EnteredAddOnProcess_DisplayName = "";
    public static String BasicNode_ExitedAddOnProcess_Description = "";
    public static String BasicNode_ExitedAddOnProcess_DisplayName = "";
    public static String AllNodes_RandomNumberStream_DisplayName = "";
    public static String AllNodes_RandomNumberStream_Description = "";
    public static String BasicNode_BoundExternalOutputNode_DisplayName = "";
    public static String BasicNode_BoundExternalOutputNode_Description = "";
    public static String BasicNode_EnteredEvent_Description = "";
    public static String BasicNode_ExitedEvent_Description = "";

    public static Image StandardLibraryIcon = new Image();
    public static Image basicnode;
    public static Image transfernode;
    public static Image StatusRun = null;
    public static Image StatusPause = null;
    public static Image StatusBreakpoint = null;
    public static Image StatusStop = null;
    public static Image StatusEndOfRun = null;
    public static Image source;
    public static Image sink;
    public static Image server;
    public static Image workstation;
    public static Image combiner;
    public static Image seperator;
    public static Image resource;
    public static Image vehicle;
    public static Image worker;
    public static Image path;
    public static Image timepath;
    public static Image conveyor;

    private static ResourceManager resourceMan;
    private static CultureInfo resourceCulture;

    public static ResourceManager ResourceManager() {
        if (Objects.equals(Resources.resourceMan, null)) {
            ResourceManager resourceManager = new ResourceManager("SimioElements.Properties.Resources",
                    Resources.class);
            Resources.resourceMan = resourceManager;
        }
        return Resources.resourceMan;
    }

    public static String ProjectLibraryTitle() {
        return Resources.ResourceManager().GetString("ProjectLibraryTitle", Resources.resourceCulture);
    }

    public static String ElementDescription_Timer() {
        return Resources.ResourceManager().GetString("ElementDescription_Timer", Resources.resourceCulture);
    }

    public static String Timer_IntervalType_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_IntervalType_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_IntervalType_Description() {
        return Resources.ResourceManager().GetString("Timer_IntervalType_Description", Resources.resourceCulture);
    }

    public static String Timer_TimeOffset_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_TimeOffset_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_TimeOffset_Description() {
        return Resources.ResourceManager().GetString("Timer_TimeOffset_Description", Resources.resourceCulture);
    }


    public static String Timer_TimeInterval_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_TimeInterval_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_TimeInterval_Description() {
        return Resources.ResourceManager().GetString("Timer_TimeInterval_Description", Resources.resourceCulture);
    }

    public static String Timer_StateVariableName_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_StateVariableName_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_StateVariableName_Description() {
        return Resources.ResourceManager().GetString("Timer_StateVariableName_Description", Resources.resourceCulture);
    }

    public static String Timer_StateValue_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_StateValue_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_StateValue_Description() {
        return Resources.ResourceManager().GetString("Timer_StateValue_Description", Resources.resourceCulture);
    }


    public static String Timer_RateTable_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_RateTable_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_RateTable_Description() {
        return Resources.ResourceManager().GetString("Timer_RateTable_Description", Resources.resourceCulture);
    }

    public static String Timer_RateScaleFactor_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_RateScaleFactor_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_RateScaleFactor_Description() {
        return Resources.ResourceManager().GetString("Timer_RateScaleFactor_Description", Resources.resourceCulture);
    }


    public static String Timer_TriggeringEventName_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_TriggeringEventName_DisplayName",
                Resources.resourceCulture);
    }

    public static String Timer_TriggeringEventName_Description() {
        return Resources.ResourceManager().GetString("Timer_TriggeringEventName_Description",
                Resources.resourceCulture);
    }


    public static String Timer_TriggeringEventCount_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_TriggeringEventCount_DisplayName",
                Resources.resourceCulture);
    }

    public static String Timer_TriggeringEventCount_Description() {
        return Resources.ResourceManager().GetString("Timer_TriggeringEventCount_Description",
                Resources.resourceCulture);
    }


    public static String Timer_ArrivalTimeProperty_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_ArrivalTimeProperty_DisplayName",
                Resources.resourceCulture);
    }

    public static String Timer_ArrivalTimeProperty_Description() {
        return Resources.ResourceManager().GetString("Timer_ArrivalTimeProperty_Description",
                Resources.resourceCulture);
    }


    public static String Timer_ArrivalEventsPerTimeSlot_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_ArrivalEventsPerTimeSlot_DisplayName",
                Resources.resourceCulture);
    }

    public static String Timer_ArrivalEventsPerTimeSlot_Description() {
        return Resources.ResourceManager().GetString("Timer_ArrivalEventsPerTimeSlot_Description",
                Resources.resourceCulture);
    }


    public static String Timer_ArrivalTimeDeviation_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_ArrivalTimeDeviation_DisplayName",
                Resources.resourceCulture);
    }

    public static String Timer_ArrivalTimeDeviation_Description() {
        return Resources.ResourceManager().GetString("Timer_ArrivalTimeDeviation_Description",
                Resources.resourceCulture);
    }


    public static String Timer_ArrivalNoShowProbability_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_ArrivalNoShowProbability_DisplayName",
                Resources.resourceCulture);
    }

    public static String Timer_ArrivalNoShowProbability_Description() {
        return Resources.ResourceManager().GetString("Timer_ArrivalNoShowProbability_Description",
                Resources.resourceCulture);
    }


    public static String Timer_RepeatArrivalPattern_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_RepeatArrivalPattern_DisplayName",
                Resources.resourceCulture);
    }

    public static String Timer_RepeatArrivalPattern_Description() {
        return Resources.ResourceManager().GetString("Timer_RepeatArrivalPattern_Description",
                Resources.resourceCulture);
    }


    public static String Timer_MaximumEvents_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_MaximumEvents_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_MaximumEvents_Description() {
        return Resources.ResourceManager().GetString("Timer_MaximumEvents_Description", Resources.resourceCulture);
    }

    public static String Timer_MaximumTime_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_MaximumTime_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_MaximumTime_Description() {
        return Resources.ResourceManager().GetString("Timer_MaximumTime_Description", Resources.resourceCulture);
    }

    public static String Timer_ResetEvent_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_ResetEvent_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_ResetEvent_Description() {
        return Resources.ResourceManager().GetString("Timer_ResetEvent_Description", Resources.resourceCulture);
    }

    public static String Timer_InitiallyEnabled_DisplayName() {
        return Resources.ResourceManager().GetString("Timer_InitiallyEnabled_DisplayName", Resources.resourceCulture);
    }

    public static String Timer_InitiallyEnabled_Description() {
        return Resources.ResourceManager().GetString("Timer_InitiallyEnabled_Description", Resources.resourceCulture);
    }

    public static String Timer_Enabled_Description() {
        return Resources.ResourceManager().GetString("Timer_Enabled_Description", Resources.resourceCulture);
    }

    public static String Timer_ElapsedTime_Description() {
        return Resources.ResourceManager().GetString("Timer_ElapsedTime_Description", Resources.resourceCulture);
    }

    public static String Timer_Event_Description() {
        return Resources.ResourceManager().GetString("Timer_Event_Description", Resources.resourceCulture);
    }

    public static String ElementPropertyCategory_BasicLogic() {
        return Resources.ResourceManager().GetString("ElementPropertyCategory_BasicLogic", Resources.resourceCulture);
    }

    public static String FailedToOpenProject() {
        return Resources.ResourceManager().GetString("FailedToOpenProject", Resources.resourceCulture);
    }

    public static String ElementPropertyCategory_StoppingConditions() {
        return Resources.ResourceManager().GetString("ElementPropertyCategory_StoppingConditions",
                Resources.resourceCulture);
    }

    public static String ElementPropertyCategory_AdvancedOptions() {
        return Resources.ResourceManager().GetString("ElementPropertyCategory_AdvancedOptions",
                Resources.resourceCulture);
    }

    public static String StepDescription_Assign = "";
    public static String Assign_StateVariableName_Description = "";
    public static String Assign_StateVariableName_DisplayName = "";
    public static String StepPropertyCategory_BasicLogic = "";
    public static String Assign_NewValue_Description = "";
    public static String Assign_NewValue_DisplayName = "";
    public static String Assign_Assignments_Description = "";
    public static String Assign_Assignments_DisplayName = "";
    public static String StepDescription_Batch = "";
    public static String Batch_EntityType_DisplayName = "";
    public static String Batch_EntityType_Description = "";
    public static String StepPropertyCategory_AdvancedOptions = "";
    public static String Batch_EntityObject_DisplayName = "";
    public static String Batch_EntityObject_Description = "";
    public static String Batch_BatchLogicName_DisplayName = "";
    public static String Batch_BatchLogicName_Description = "";
    public static String Batch_Category_DisplayName = "";
    public static String Batch_Category_Description = "";

    public static String StepDescription_EndTransfer = "";
    public static String EndTransfer_EntityType_DisplayName = "";
    public static String EndTransfer_EntityType_Description = "";
    public static String EndTransfer_EntityObject_DisplayName = "";
    public static String EndTransfer_EntityObject_Description = "";

    public static String StepDescription_Consume = "";
    public static String Consume_OwnerType_DisplayName = "";
    public static String Consume_OwnerType_Description = "";
    public static String Consume_OwnerObject_DisplayName = "";
    public static String Consume_OwnerObject_Description = "";
    public static String Consume_ConsumptionType_Description = "";
    public static String Consume_ConsumptionType_DisplayName = "";
    public static String Consume_MaterialName_Description = "";
    public static String Consume_MaterialName_DisplayName = "";
    public static String Consume_Quantity_DisplayName = "";
    public static String Consume_Quantity_Description = "";
    public static String Consume_AccrueMaterialCosts_DisplayName = "";
    public static String Consume_AccrueMaterialCosts_Description = "";

    public static String StepDescription_Destroy = "";
    public static String Destroy_OkExit_Description = "";
    public static String Destroy_OkExit_DisplayName = "";
    public static String Destroy_FailedExit_DisplayName = "";
    public static String Destroy_FailedExit_Description = "";
    public static String Destroy_DestroyType_DisplayName = "";
    public static String Destroy_DestroyType_Description = "";
    public static String Destroy_EntityObject_DisplayName = "";
    public static String Destroy_EntityObject_Description = "";

    public static String StepDescription_Decide = "";
    public static String Decide_TrueExit_DisplayName = "";
    public static String Decide_TrueExit_Description = "";
    public static String Decide_FalseExit_DisplayName = "";
    public static String Decide_FalseExit_Description = "";
    public static String Decide_DecideType_DisplayName = "";
    public static String Decide_DecideType_Description = "";
    public static String Decide_Expression_DisplayName = "";
    public static String Decide_Expression_Description = "";
    public static String Decide_RandomNumberStream_DisplayName = "";
    public static String Decide_RandomNumberStream_Description = "";

    public static String StepDescription_Delay = "";
    public static String Delay_DelayTime_DisplayName = "";
    public static String Delay_DelayTime_Description = "";

    public static String StepDescription_Create = "";
    public static String Create_OriginalExit_DisplayName = "";
    public static String Create_OriginalExit_Description = "";
    public static String Create_CreatedExit_DisplayName = "";
    public static String Create_CreatedExit_Description = "";
    public static String Create_CreateType_DisplayName = "";
    public static String Create_CreateType_Description = "";
    public static String Create_SourceEntityObject_DisplayName = "";
    public static String Create_SourceEntityObject_Description = "";
    public static String Create_EntityType_DisplayName = "";
    public static String Create_EntityType_Description = "";
    public static String Create_NumberOfObjects_DisplayName = "";
    public static String Create_NumberOfObjects_Description = "";
}
