package org.licho.brain.concrete;

import org.licho.brain.annotations.ElementFunctionReferenceReturnType;
import org.licho.brain.annotations.PopulationFunction;
import org.licho.brain.annotations.UnitClass;
import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.annotation.ElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.enu.AgentInterfaceProcess;
import org.licho.brain.enu.IconIndex;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.utils.simu.IEntityProcess;

import java.text.MessageFormat;
import java.util.Collection;

/**
 *
 */
public class AgentDefinition extends IntelligentObjectDefinition {
    public static final Guid guid = new Guid("{319A96C1-CB6F-4fcc-9170-E621A107C806}");
    public static final String name = "Agent";
    public static final AgentDefinition AgentFacility = new AgentDefinition(null);

    static {
        InternalReference.put(AgentDefinition.name, AgentDefinition.AgentFacility);
    }

    public AgentDefinition(String name, ActiveModel activeModel, Guid guid) {
        super(name, activeModel, guid);
        super.Description(EngineResources.Agent_Description);
        super.ResourceLogic(false);
    }

    public AgentDefinition(String name, ActiveModel activeModel,
                           IntelligentObjectDefinition baseClassDefinition) {
        super(name, activeModel, baseClassDefinition);
    }

    private AgentDefinition(ActiveModel activeModel) {
        this(AgentDefinition.name, activeModel, AgentDefinition.guid);
    }

    protected void DefineSchema() {
        super.DefineSchema();
        ExpressionPropertyDefinition initialDesiredSpeed = new ExpressionPropertyDefinition("InitialDesiredSpeed");
        initialDesiredSpeed.DisplayName(EngineResources.Agent_InitialDesiredSpeed_DisplayName);
        initialDesiredSpeed.Description(EngineResources.Agent_InitialDesiredSpeed_Description);
        initialDesiredSpeed.DefaultString("2.0");
        initialDesiredSpeed.CanBeDeleted(false);
        initialDesiredSpeed.CategoryName(EngineResources.CategoryName_TravelLogic);
        initialDesiredSpeed.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.TravelRate);
        initialDesiredSpeed.DefaultUnitSubType(1);
        initialDesiredSpeed.ComplexityLevel(ProductComplexityLevel.Basic);

        NumericDataPropertyDefinition initialNumberInSystem = new NumericDataPropertyDefinition("InitialNumberInSystem"
                , NumericDataType.Integer);
        initialNumberInSystem.DisplayName(EngineResources.Agent_InitialNumberInSystem_DisplayName);
        initialNumberInSystem.Description(EngineResources.Agent_InitialNumberInSystem_Description);
        initialNumberInSystem.DefaultString("0");
        initialNumberInSystem.CanBeDeleted(false);
        initialNumberInSystem.CategoryName(EngineResources.CategoryName_Population);
        initialNumberInSystem.ComplexityLevel(ProductComplexityLevel.Advanced);

        NumericDataPropertyDefinition maximumNumberInSystem = new NumericDataPropertyDefinition(
                "MaximumNumberInSystem", NumericDataType.Integer);
        maximumNumberInSystem.DisplayName(EngineResources.Agent_MaximumNumberInSystem_DisplayName);
        maximumNumberInSystem.Description(EngineResources.Agent_MaximumNumberInSystem_Description);
        maximumNumberInSystem.DefaultString("2500");
        maximumNumberInSystem.CanBeDeleted(false);
        maximumNumberInSystem.CategoryName(EngineResources.CategoryName_Population);
        maximumNumberInSystem.ComplexityLevel(ProductComplexityLevel.Advanced);

        BooleanPropertyDefinition destroyable = new BooleanPropertyDefinition("Destroyable");
        destroyable.DisplayName(EngineResources.Agent_Destroyable_DisplayName);
        destroyable.Description(EngineResources.Agent_Destroyable_Description);
        destroyable.DefaultString("True");
        destroyable.CanBeDeleted(false);
        destroyable.CategoryName(EngineResources.CategoryName_Population);
        destroyable.ComplexityLevel(ProductComplexityLevel.Advanced);

        super.getPropertyDefinitions().Add(initialDesiredSpeed);
        super.getPropertyDefinitions().Add(initialNumberInSystem);
        super.getPropertyDefinitions().Add(maximumNumberInSystem);
        super.getPropertyDefinitions().Add(destroyable);

        super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("ParentCostCenter").SetLocalVisible(false,
                super.getPropertyDefinitions());
        super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("InitialCost").SetLocalDescription(EngineResources.Agent_InitialCost_Description, super.getPropertyDefinitions());
        super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("InitialCostRate").SetLocalDescription(EngineResources.Agent_InitialCostRate_Description, super.getPropertyDefinitions());

        MovementStatePropertyObject movement = new MovementStatePropertyObject("Movement", false, false);
        movement.value = 0.0;
        movement.Description(EngineResources.Agent_Movement_Description);
        movement.UnitType(UnitType.Length);

        BaseStatePropertyObject desiredSpeed = new BaseStatePropertyObject("DesiredSpeed", false, false);
        desiredSpeed.value = 1.0;
        desiredSpeed.Description(EngineResources.Agent_DesiredSpeed_Description);
        desiredSpeed.UnitType(UnitType.TravelRate);

        super.getStateDefinitions().addStateProperty(movement);
        super.getStateDefinitions().addStateProperty(desiredSpeed);
        super.createProcessProperties(ObjectClass.Agent);
    }

    @BaseElementFunction("TimeCreated")
    @UnitClass(UnitType.Time)
    public static double TimeCreated(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getTimeCreated();
    }

    @BaseElementFunction("TimeInSystem")
    @UnitClass(UnitType.Time)
    public static double TimeInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        return intelligentObjectRunSpace.getSomeRun().TimeNow() - intelligentObjectRunSpace.getTimeCreated();
    }

    @PopulationFunction
    @BaseElementFunction("Population.Name")
    public static ExpressionValue Population_Name(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return new ExpressionValue(intelligentObjectRunSpace.getPopulation().getAgentElementRunSpace().Name());
    }

    @PopulationFunction
    @BaseElementFunction("Population.NumberInSystem")
    public static double Population_NumberInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getNumberInSystem();
    }

    @PopulationFunction
    @BaseElementFunction("Population.NumberInSystem.Minimum")
    public static double Population_NumberInSystem_Minimum(AbsBaseRunSpace absBaseRunSpace,
                                                           IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getNumberInSystem_Minimum();
    }

    @PopulationFunction
    @BaseElementFunction("Population.NumberInSystem.Maximum")
    public static double Population_NumberInSystem_Maximum(AbsBaseRunSpace absBaseRunSpace,
                                                           IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().MaximumNumberInSystem();
    }

    @BaseElementFunction("Population.NumberInSystem.Average")
    @PopulationFunction
    public static double Population_NumberInSystem_Average(AbsBaseRunSpace absBaseRunSpace,
                                                           IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getNumberInSystem_Average();
    }

    @PopulationFunction
    @UnitClass(UnitType.Time)
    @BaseElementFunction("Population.TimeInSystem.Minimum")
    public static double Population_TimeInSystem_Minimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getTimeInSystem_Minimum();
    }

    @BaseElementFunction("Population.TimeInSystem.Maximum")
    @PopulationFunction
    @UnitClass(UnitType.Time)
    public static double Population_TimeInSystem_Maximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getTimeInSystem_Maximum();
    }

    @UnitClass(UnitType.Time)
    @PopulationFunction
    @BaseElementFunction("Population.TimeInSystem.Average")
    public static double Population_TimeInSystem_Average(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getTimeInSystem_Average();
    }

    @UnitClass(UnitType.Currency)
    @PopulationFunction
    @BaseElementFunction("Population.CostPerItem.Average")
    public static double Population_CostPerItem_Average(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getCostPerItem_Average();
    }

    @UnitClass(UnitType.Currency)
    @PopulationFunction
    @BaseElementFunction("Population.CostPerItem.Minimum")
    public static double Population_CostPerItem_Minimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getCostPerItem_Minimum();
    }

    @PopulationFunction
    @UnitClass(UnitType.Currency)
    @BaseElementFunction("Population.CostPerItem.Maximum")
    public static double Population_CostPerItem_Maximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().getCostPerItem_Minimum();
    }

    @BaseElementFunction("Population.NumberCreated")
    @PopulationFunction
    public static double Population_NumberCreated(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().NumberCreated();
    }

    @PopulationFunction
    @BaseElementFunction("Population.NumberDestroyed")
    public static double Population_NumberDestroyed(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getPopulation().NumberDestroyed();
    }

    @PopulationFunction
    @ResourceFunction
    @BaseElementFunction("Population.Capacity")
    public static double Population_Capacity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().CurrentCapacity();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Population.Capacity.Average")
    @PopulationFunction
    public static double Population_Capacity_Average(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacity_Average();
        }
        return 0.0;
    }

    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Minimum")
    @ResourceFunction
    public static double Population_Capacity_Minimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityMinimum();
        }
        return 0.0;
    }

    @ResourceFunction
    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Maximum")
    public static double Population_Capacity_Maximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityMaximum();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Population.Capacity.Allocated")
    @PopulationFunction
    public static double Population_Capacity_Allocated(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityAllocated();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Population.Capacity.Allocated.Average")
    @PopulationFunction
    public static double Population_Capacity_Allocated_Average(AbsBaseRunSpace absBaseRunSpace,
                                                               IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getAllocatedAverage();
        }
        return 0.0;
    }

    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Allocated.Minimum")
    @ResourceFunction
    public static double Population_Capacity_Allocated_Minimum(AbsBaseRunSpace absBaseRunSpace,
                                                               IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getAllocated_Minimum();
        }
        return 0.0;
    }

    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Allocated.Maximum")
    @ResourceFunction
    public static double Population_Capacity_Allocated_Maximum(AbsBaseRunSpace absBaseRunSpace,
                                                               IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getAllocatedMaximum();
        }
        return 0.0;
    }

    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Allocated.Total")
    @ResourceFunction
    public static double Population_Capacity_Allocated_Total(AbsBaseRunSpace absBaseRunSpace,
                                                             IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityAllocatedTotal();
        }
        return 0.0;
    }

    @PopulationFunction
    @ResourceFunction
    @BaseElementFunction("Population.Capacity.Remaining")
    public static double Population_Capacity_Remaining(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityRemaining();
        }
        return 0.0;
    }

    @ResourceFunction
    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Utilized")
    public static double Population_Capacity_Utilized(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityUtilized();
        }
        return 0.0;
    }

    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Utilized.Average")
    @ResourceFunction
    public static double Population_Capacity_Utilized_Average(AbsBaseRunSpace absBaseRunSpace,
                                                              IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getUtilizedAverage();
        }
        return 0.0;
    }

    @BaseElementFunction("Population.Capacity.Utilized.Minimum")
    @ResourceFunction
    @PopulationFunction
    public static double Population_Capacity_Utilized_Minimum(AbsBaseRunSpace absBaseRunSpace,
                                                              IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityUtilizedMinimum();
        }
        return 0.0;
    }

    @PopulationFunction
    @BaseElementFunction("Population.Capacity.Utilized.Maximum")
    @ResourceFunction
    public static double Population_Capacity_Utilized_Maximum(AbsBaseRunSpace absBaseRunSpace,
                                                              IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityUtilizedMaximum();
        }
        return 0.0;
    }

    @BaseElementFunction("Population.Capacity.ScheduledUtilization")
    @ResourceFunction
    @PopulationFunction
    public static double Population_Capacity_ScheduledUtilization(AbsBaseRunSpace absBaseRunSpace,
                                                                  IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getPopulation().getCapacityScheduledUtilization();
        }
        return 0.0;
    }

    @BaseElementFunction("Population.Index")
    public static double Population_Index(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.getIndex() + 1;
    }

    @BaseElementFunction("Population.FirstItem")
    @ElementFunctionReferenceReturnType(AgentDefinition.class)
    @PopulationFunction
    public static ExpressionValue Population_FirstItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces().size() > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces().get(0));
        }
        return null;
    }

    @PopulationFunction
    @BaseElementFunction("Population.LastItem")
    @ElementFunctionReferenceReturnType(AgentDefinition.class)
    public static ExpressionValue Population_LastItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces().size() > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces()
                    .get(intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces().size() - 1));
        }
        return null;
    }

    @BaseElementFunction(value = "Population.ItemAtIndex", Arguments = "index")
    @ElementFunctionReferenceReturnType(AgentDefinition.class)
    @PopulationFunction
    public static ExpressionValue Population_ItemAtIndex(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                         ExpressionValue[] expressionValues) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception ignored) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "Population.ItemAtIndex"));
        }
        if (intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces().size() < num || num < 1) {
            return null;
        }
        return ExpressionValue.from(intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces().get(num - 1));
    }

    @PopulationFunction
    @BaseElementFunction(value = "Population.IndexOfItem", Arguments = "entity")
    public static double Population_IndexOfItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                ExpressionValue[] expressionValues) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        AgentElementRunSpace intelligentObjectRunSpace2 = null;

        try {
            intelligentObjectRunSpace2 = (AgentElementRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }

        if (intelligentObjectRunSpace2 == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "Population.IndexOfItem"));
        }
        return intelligentObjectRunSpace.getPopulation().getAgentElementRunSpaces().indexOf(intelligentObjectRunSpace2) + 1;
    }

    @PopulationFunction
    @BaseElementFunction(value = "Population.Contains", Arguments = "entity")
    public static double Population_Contains(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                             ExpressionValue[] expressionValues) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        AgentElementRunSpace intelligentObjectRunSpace2 = null;
        try {
            intelligentObjectRunSpace2 = (AgentElementRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (intelligentObjectRunSpace2 == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "Population.Contains"));
        }
        if (intelligentObjectRunSpace.getPopulation() == intelligentObjectRunSpace2.getPopulation()) {
            return 1.0;
        }
        return 0.0;
    }

    @ElementFunction("MemberIndex")
    public static double MemberIndex(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_Index(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberInSystem")
    @PopulationFunction
    public static double NumberInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_NumberInSystem(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberInSystem.Minimum")
    @PopulationFunction
    public static double NumberInSystem_Minimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_NumberInSystem_Minimum(absBaseRunSpace, runSpace);
    }

    @PopulationFunction
    @ElementFunction("NumberInSystem.Maximum")
    public static double NumberInSystem_Maximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_NumberInSystem_Maximum(absBaseRunSpace, runSpace);
    }

    @PopulationFunction
    @ElementFunction("NumberInSystem.Average")
    public static double NumberInSystem_Average(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_NumberInSystem_Average(absBaseRunSpace, runSpace);
    }

    @ElementFunction("TimeInSystem.Minimum")
    @PopulationFunction
    @UnitClass(UnitType.Time)
    public static double TimeInSystem_Minimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_TimeInSystem_Minimum(absBaseRunSpace, runSpace);
    }

    @PopulationFunction
    @ElementFunction("TimeInSystem.Maximum")
    @UnitClass(UnitType.Time)
    public static double TimeInSystem_Maximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_TimeInSystem_Maximum(absBaseRunSpace, runSpace);
    }

    @ElementFunction("TimeInSystem.Average")
    @UnitClass(UnitType.Time)
    @PopulationFunction
    public static double TimeInSystem_Average(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_TimeInSystem_Average(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberCreated")
    @PopulationFunction
    public static double NumberCreated(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_NumberCreated(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberDestroyed")
    @PopulationFunction
    public static double NumberDestroyed(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.Population_NumberDestroyed(absBaseRunSpace, runSpace);
    }

    @ElementFunction("MinimumNumberInSystem")
    public static double MinimumNumberInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.NumberInSystem_Minimum(absBaseRunSpace, runSpace);
    }

    @ElementFunction("AverageNumberInSystem")
    public static double AverageNumberInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.NumberInSystem_Average(absBaseRunSpace, runSpace);
    }

    @ElementFunction("MinimumTimeInSystem")
    public static double MinimumTimeInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.TimeInSystem_Minimum(absBaseRunSpace, runSpace);
    }

    @ElementFunction("MaximumTimeInSystem")
    public static double MaximumTimeInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.TimeInSystem_Maximum(absBaseRunSpace, runSpace);
    }

    @ElementFunction("AverageTimeInSystem")
    public static double AverageTimeInSystem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return AgentDefinition.TimeInSystem_Average(absBaseRunSpace, runSpace);
    }

    @ElementFunction("TableRow")
    public static double TableRow(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return (double) (intelligentObjectRunSpace.tableRowReferences.getTableRow() + 1);
    }

    @ElementFunction("TableRowCount")
    public static double TableRowCount(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        AgentElementRunSpace intelligentObjectRunSpace = (AgentElementRunSpace) absBaseRunSpace;
        return (double) intelligentObjectRunSpace.tableRowReferences.getTableRowCount();
    }

    @Override
    public IntelligentObjectDefinition createDefaultParentDefinition() {
        return AgentDefinition.create();
    }

    public static AgentDefinition create() {
        return new AgentDefinition(null);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Agent(this, name, ElementScope.Private);
    }

    @Override
    public void GetInterfaceProcessInformation(Collection<IEntityProcess> infoList) {
        super.GetInterfaceProcessInformation(infoList);
        super.injectEntityProcessToObjectClass(infoList, AgentInterfaceProcess.class, ObjectClass.Agent);
    }

    @Override
    protected boolean ShowCreatedDestroyingProcesses() {
        return true;
    }

    @Override
    public boolean IsDynamic() {
        return true;
    }

    @Override
    public IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.SEVEN;
    }


}
