package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.enu.TrafficDirection;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.simioEnums.SwitchNumericConditions;
import org.licho.brain.utils.simu.EntityAlignment;
import org.licho.brain.utils.simu.LinkAutoAlignCellsOption;
import org.licho.brain.utils.simu.LinkCellSpacingType;
import org.licho.brain.utils.simu.LinkCrossSectionShape;
import org.licho.brain.utils.simu.LinkType;
import org.licho.brain.utils.simu.TrafficDirectionRule;
import org.licho.brain.simioEnums.QueueRanking;

/**
 *
 */
public class LinkDefinition extends FixedDefinition {
    private double height;
    private double width;

    public LinkDefinition(String name) {
        super(name);
    }

    public LinkDefinition(String name, ActiveModel activeModel, LinkDefinition parent) {
        super(name, activeModel, parent);
    }

    public static IntelligentObjectDefinition create() {
        return new LinkDefinition(null);
    }

    public double DefaultHeight() {
        return this.height;
    }

    public void DefaultHeight(double value) {
        this.height = Math.max(0.0, value);
        ;
    }

    @Override
    public ObjectClass ObjectClass() {
        return ObjectClass.Link;
    }

    public double DefaultWidth() {
        return this.width;
    }

    public void DefaultWidth(double value) {
        this.width = Math.max(0.0, value);
    }


    @Override
    protected void DefineSchema() {
        super.DefineSchema();
        EnumPropertyDefinition type = new EnumPropertyDefinition("Type", LinkType.class);
        type.DisplayName(EngineResources.Link_Type_DisplayName);
        type.Description(EngineResources.Link_Type_Description);
        type.DefaultString(LinkType.Unidirectional.toString());
        type.CanReferenceParent(false);
        type.CategoryName(EngineResources.CategoryName_TravelLogic);
        type.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition initialTravelerCapacity = new ExpressionPropertyDefinition(
                "InitialTravelerCapacity");
        initialTravelerCapacity.Description(EngineResources.Link_InitialTravelerCapacity_Description);
        initialTravelerCapacity.DisplayName(EngineResources.Link_InitialTravelerCapacity_DisplayName);
        initialTravelerCapacity.CategoryName(EngineResources.CategoryName_TravelLogic);
        initialTravelerCapacity.DefaultString("Infinity");
        initialTravelerCapacity.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition trafficDirectionRule = new EnumPropertyDefinition("TrafficDirectionRule",
                TrafficDirectionRule.class);
        trafficDirectionRule.Description(EngineResources.Link_TrafficDirectionRule_Description);
        trafficDirectionRule.DisplayName(EngineResources.Link_TrafficDirectionRule_DisplayName);
        trafficDirectionRule.DefaultString("First In Entry Queue");
        trafficDirectionRule.SwitchNumericProperty(type);
        trafficDirectionRule.SwitchNumericValue(1.0);
        trafficDirectionRule.stringValues = new String[]
                {
                        "First In Entry Queue",
                        "Match Desired Direction",
                        "Prefer Desired Direction"
                };
        trafficDirectionRule.ParentPropertyDefinition(type);
        trafficDirectionRule.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition initialDesiredDirection = new EnumPropertyDefinition("InitialDesiredDirection",
                TrafficDirection.class);
        initialDesiredDirection.DisplayName(EngineResources.Link_InitialDesiredDirection_DisplayName);
        initialDesiredDirection.Description(EngineResources.Link_InitialDesiredDirection_Description);
        initialDesiredDirection.DefaultString(TrafficDirection.Either.toString());
        initialDesiredDirection.ParentPropertyDefinition(type);
        initialDesiredDirection.SwitchNumericProperty(type);
        initialDesiredDirection.SwitchNumericValue(1.0);
        initialDesiredDirection.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition entryRankingRule = new EnumPropertyDefinition("EntryRankingRule", QueueRanking.class);
        entryRankingRule.Description(EngineResources.Link_EntryRankingRule_Description);
        entryRankingRule.DisplayName(EngineResources.Link_EntryRankingRule_DisplayName);
        entryRankingRule.DefaultString("First In First Out");
        entryRankingRule.stringValues = new String[]
                {
                        "First In First Out",
                        "Last In First Out",
                        "Smallest Value First",
                        "Largest Value First"
                };
        entryRankingRule.CategoryName(EngineResources.CategoryName_TravelLogic);
        entryRankingRule.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition entryRankingExpression = new ExpressionPropertyDefinition(
                "EntryRankingExpression");
        entryRankingExpression.DisplayName(EngineResources.Link_EntryRankingExpression_DisplayName);
        entryRankingExpression.Description(EngineResources.Link_EntryRankingExpression_Description);
        entryRankingExpression.DefaultString("Entity.Priority");
        entryRankingExpression.SwitchNumericProperty(entryRankingRule);
        entryRankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        entryRankingExpression.SwitchNumericValue(1.0);
        entryRankingExpression.ParentPropertyDefinition(entryRankingRule);
        entryRankingExpression.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition initialDesiredSpeed = new ExpressionPropertyDefinition("InitialDesiredSpeed");
        initialDesiredSpeed.DisplayName(EngineResources.Link_InitialDesiredSpeed_DisplayName);
        initialDesiredSpeed.Description(EngineResources.Link_InitialDesiredSpeed_Description);
        initialDesiredSpeed.DefaultString("2.0");
        initialDesiredSpeed.CategoryName(EngineResources.CategoryName_TravelLogic);
        initialDesiredSpeed.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.TravelRate);
        initialDesiredSpeed.DefaultUnitSubType(1);
        initialDesiredSpeed.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition entityAlignment = new EnumPropertyDefinition("EntityAlignment", EntityAlignment.class);
        entityAlignment.DisplayName(EngineResources.Link_EntityAlignment_DisplayName);
        entityAlignment.Description(EngineResources.Link_EntityAlignment_Description);
        entityAlignment.DefaultString("Any Location");
        entityAlignment.RequiredValue(true);
        entityAlignment.stringValues = new String[]
                {
                        "Any Location",
                        "Cell Location"
                };
        entityAlignment.CategoryName(EngineResources.CategoryName_TravelLogic);
        entityAlignment.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition cellSpacingType = new EnumPropertyDefinition("CellSpacingType",
                LinkCellSpacingType.class);
        cellSpacingType.DisplayName(EngineResources.Link_CellSpacingType_DisplayName);
        cellSpacingType.Description(EngineResources.Link_CellSpacingType_Description);
        cellSpacingType.DefaultString("Fixed Cell Size");
        cellSpacingType.RequiredValue(true);
        cellSpacingType.stringValues = new String[]
                {
                        "Fixed Number Cells",
                        "Fixed Cell Size"
                };
        cellSpacingType.SwitchNumericProperty(entityAlignment);
        cellSpacingType.SwitchNumericValue(1.0);
        cellSpacingType.ParentPropertyDefinition(entityAlignment);
        cellSpacingType.ComplexityLevel(ProductComplexityLevel.Advanced);

        NumericDataPropertyDefinition numberOfCells = new NumericDataPropertyDefinition("NumberOfCells",
                NumericDataType.Integer);
        numberOfCells.Description(EngineResources.Link_NumberOfCells_Description);
        numberOfCells.DisplayName(EngineResources.Link_NumberOfCells_DisplayName);
        numberOfCells.DefaultString("1");
        numberOfCells.RequiredValue(true);
        numberOfCells.SwitchNumericProperty(cellSpacingType);
        numberOfCells.SwitchNumericValue(0.0);
        numberOfCells.ParentPropertyDefinition(entityAlignment);
        numberOfCells.ComplexityLevel(ProductComplexityLevel.Advanced);

        NumericDataPropertyDefinition cellSize = new NumericDataPropertyDefinition("CellSize", NumericDataType.Real);
        cellSize.DefaultString("1.0");
        cellSize.DisplayName(EngineResources.Link_CellSize_DisplayName);
        cellSize.Description(EngineResources.Link_CellSize_Description);
        cellSize.SwitchNumericProperty(cellSpacingType);
        cellSize.SwitchNumericValue(1.0);
        cellSize.ParentPropertyDefinition(entityAlignment);
        cellSize.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Length);
        cellSize.DefaultUnitSubType(0);
        cellSize.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition autoAlignCells = new EnumPropertyDefinition("AutoAlignCells",
                LinkAutoAlignCellsOption.class);
        autoAlignCells.DisplayName(EngineResources.Link_AutoAlignCells_DisplayName);
        autoAlignCells.Description(EngineResources.Link_AutoAlignCells_Description);
        autoAlignCells.DefaultString("With First Entity");
        autoAlignCells.RequiredValue(true);
        autoAlignCells.stringValues = new String[]
                {
                        "No",
                        "With First Entity"
                };
        autoAlignCells.SwitchNumericProperty(entityAlignment);
        autoAlignCells.SwitchNumericValue(1.0);
        autoAlignCells.ParentPropertyDefinition(entityAlignment);
        autoAlignCells.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition selectionWeight = new ExpressionPropertyDefinition("SelectionWeight");
        selectionWeight.CategoryName(EngineResources.CategoryName_RoutingLogic);
        selectionWeight.DisplayName(EngineResources.Link_SelectionWeight_DisplayName);
        selectionWeight.Description(EngineResources.Link_SelectionWeight_Description);
        selectionWeight.DefaultString("1.0");
        selectionWeight.ComplexityLevel(ProductComplexityLevel.Basic);

        BooleanPropertyDefinition drawnToScale = new BooleanPropertyDefinition("DrawnToScale");
        drawnToScale.DisplayName(EngineResources.Link_DrawnToScale_DisplayName);
        drawnToScale.Description(EngineResources.Link_DrawnToScale_Description);
        drawnToScale.DefaultString("True");
        drawnToScale.CanReferenceParent(false);
        drawnToScale.CategoryName(EngineResources.CategoryName_TravelLogic);
        drawnToScale.ComplexityLevel(ProductComplexityLevel.Advanced);

        NumericDataPropertyDefinition logicalLength = new NumericDataPropertyDefinition("LogicalLength",
                NumericDataType.Real);
        logicalLength.DefaultString("10.0");
        logicalLength.DisplayName(EngineResources.Link_LogicalLength_DisplayName);
        logicalLength.Description(EngineResources.Link_LogicalLength_Description);
        logicalLength.SwitchNumericProperty(drawnToScale);
        logicalLength.SwitchNumericValue(0.0);
        logicalLength.CanReferenceParent(false);
        logicalLength.CategoryName(EngineResources.CategoryName_TravelLogic);
        logicalLength.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Length);
        logicalLength.DefaultUnitSubType(0);
        logicalLength.ComplexityLevel(ProductComplexityLevel.Advanced);

        EnumPropertyDefinition crossSectionShape = new EnumPropertyDefinition("CrossSectionShape",
                LinkCrossSectionShape.class);
        crossSectionShape.DisplayName(EngineResources.Link_CrossSectionShape_DisplayName);
        crossSectionShape.Description(EngineResources.Link_CrossSectionShape_Description);
        crossSectionShape.DefaultString(LinkCrossSectionShape.Rectangle.toString());
        crossSectionShape.CategoryName(EngineResources.CategoryName_PhysicalCharacteristics);
        crossSectionShape.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition trapezoidRatio = new ExpressionPropertyDefinition("TrapezoidRatio");
        trapezoidRatio.DisplayName(EngineResources.Link_TrapezoidRatio_DisplayName);
        trapezoidRatio.Description(EngineResources.Link_TrapezoidRatio_Description);
        trapezoidRatio.DefaultString("0.5");
        trapezoidRatio.CategoryName(EngineResources.CategoryName_PhysicalCharacteristics);
        trapezoidRatio.SwitchNumericProperty(crossSectionShape);
        trapezoidRatio.SwitchNumericValue(2.0);
        trapezoidRatio.ParentPropertyDefinition(crossSectionShape);
        trapezoidRatio.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(type);
        super.getPropertyDefinitions().add(initialTravelerCapacity);
        super.getPropertyDefinitions().add(trafficDirectionRule);
        super.getPropertyDefinitions().add(initialDesiredDirection);
        super.getPropertyDefinitions().add(entryRankingRule);
        super.getPropertyDefinitions().add(entryRankingExpression);
        super.getPropertyDefinitions().add(initialDesiredSpeed);
        super.getPropertyDefinitions().add(entityAlignment);
        super.getPropertyDefinitions().add(cellSpacingType);
        super.getPropertyDefinitions().add(numberOfCells);
        super.getPropertyDefinitions().add(cellSize);
        super.getPropertyDefinitions().add(autoAlignCells);
        super.getPropertyDefinitions().add(selectionWeight);
        super.getPropertyDefinitions().add(drawnToScale);
        super.getPropertyDefinitions().add(logicalLength);
        super.getPropertyDefinitions().add(crossSectionShape);
        super.getPropertyDefinitions().add(trapezoidRatio);

        BaseStatePropertyObject transferInState = new BaseStatePropertyObject("TransferInState", true, false,
                NumericDataType.Integer);
        transferInState.Description(EngineResources.Link_TransferInState_Description);

        BaseStatePropertyObject desiredSpeed = new BaseStatePropertyObject("DesiredSpeed", false, false);
        desiredSpeed.Description(EngineResources.Link_DesiredSpeed_Description);
        desiredSpeed.UnitType(UnitType.TravelRate);

        CostStatePropertyObject movement = new CostStatePropertyObject("Movement", false, false);
        movement.Description(EngineResources.Link_Movement_Description);
        movement.UnitType(UnitType.Length);

        SecondOrderStatePropertyObject distanceFromFirstEdgeToEnd = new SecondOrderStatePropertyObject(
                "DistanceFromFirstEdgeToEnd", true, true);
        distanceFromFirstEdgeToEnd.Description(EngineResources.Link_DistanceFromFirstEdgeToEnd_Description);
        distanceFromFirstEdgeToEnd.UnitType(UnitType.Length);

        QueueStateObject<EntryAssociatedObject> entryQueue = new QueueStateObject<EntryAssociatedObject>("EntryQueue");
        entryQueue.Description(EngineResources.Link_EntryQueue_Description);
        entryQueue.CanRemove(true);

        BaseStatePropertyObject desiredDirection = new BaseStatePropertyObject("DesiredDirection", false, false,
                NumericDataType.Integer);
        desiredDirection.Description(EngineResources.Link_DesiredDirection_Description);

        BaseStatePropertyObject currentTravelerCapacity = new BaseStatePropertyObject("CurrentTravelerCapacity", false, false,
                NumericDataType.Integer);
        currentTravelerCapacity.Description(EngineResources.Link_CurrentTravelerCapacity_Description);

        CostStatePropertyObject currentVolumeFlowIn = new CostStatePropertyObject("CurrentVolumeFlowIn", true, false);
        currentVolumeFlowIn.Description(EngineResources.Link_CurrentVolumeFlowIn_Description);
        currentVolumeFlowIn.UnitType(UnitType.Volume);

        CostStatePropertyObject currentVolumeFlowOut = new CostStatePropertyObject("CurrentVolumeFlowOut", true, false);
        currentVolumeFlowOut.Description(EngineResources.Link_CurrentVolumeFlowOut_Description);
        currentVolumeFlowOut.UnitType(UnitType.Volume);

        CostStatePropertyObject currentWeightFlowIn = new CostStatePropertyObject("CurrentWeightFlowIn", true, false);
        currentWeightFlowIn.Description(EngineResources.Link_CurrentWeightFlowIn_Description);
        currentWeightFlowIn.UnitType(UnitType.Weight);

        CostStatePropertyObject currentWeightFlowOut = new CostStatePropertyObject("CurrentWeightFlowOut", true, false);
        currentWeightFlowOut.Description(EngineResources.Link_CurrentWeightFlowOut_Description);
        currentWeightFlowOut.UnitType(UnitType.Weight);
        super.getStateDefinitions().addStateProperty(transferInState);
        super.getStateDefinitions().addStateProperty(desiredSpeed);
        super.getStateDefinitions().addStateProperty(movement);
        super.getStateDefinitions().addStateProperty(entryQueue);
        super.getStateDefinitions().addStateProperty(distanceFromFirstEdgeToEnd);
        super.getStateDefinitions().addStateProperty(desiredDirection);
        super.getStateDefinitions().addStateProperty(currentTravelerCapacity);
        super.getStateDefinitions().addStateProperty(currentVolumeFlowIn);
        super.getStateDefinitions().addStateProperty(currentVolumeFlowOut);
        super.getStateDefinitions().addStateProperty(currentWeightFlowIn);
        super.getStateDefinitions().addStateProperty(currentWeightFlowOut);
        super.createProcessProperties(ObjectClass.Link);
    }


    @Override
    public void GetAdditionalProperties(GridItemProperties gridItemProperties,
                                        GridObjectDefinition gridObjectDefinition) {


    }

    public static final Guid guid = new Guid("{C02F2689-EA20-4927-8B76-EEAFE0DD7784}");

    // Token: 0x040018F9 RID: 6393
    public static final String name = "Link";

    // Token: 0x040018FA RID: 6394
    public static final LinkDefinition LinkFacility = new LinkDefinition(null);

}
