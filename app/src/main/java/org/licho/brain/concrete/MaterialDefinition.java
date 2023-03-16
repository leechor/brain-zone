package org.licho.brain.concrete;

import org.licho.brain.annotations.UnitClass;
import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainEnums.QueueRanking;
import org.licho.brain.brainEnums.SwitchNumericConditions;

/**
 *
 */
public class MaterialDefinition extends AbstractGridObjectDefinition {

    public static final MaterialDefinition Definition = new MaterialDefinition();

    private MaterialDefinition() {
        super("Material");
        super.Description(EngineResources.ElementDescription_Material);
        NumericDataPropertyDefinition initialQuantity = new NumericDataPropertyDefinition("InitialQuantity",
				NumericDataType.Real, "0.0");
        initialQuantity.Description(EngineResources.Material_InitialQuantity_Description);
        initialQuantity.DisplayName(EngineResources.Material_InitialQuantity_DisplayName);
        initialQuantity.DefaultString("0.0");
        initialQuantity.CategoryName(EngineResources.CategoryName_BasicLogic);
        EnumPropertyDefinition allocationRankingRule = new EnumPropertyDefinition("AllocationRankingRule",
				QueueRanking.class);
        allocationRankingRule.Description(EngineResources.Material_AllocationRankingRule_Description);
        allocationRankingRule.DisplayName(EngineResources.Material_AllocationRankingRule_DisplayName);
        allocationRankingRule.DefaultString(QueueRanking.FirstInFirstOut.toString());
        allocationRankingRule.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        allocationRankingRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition alllocationRankingExpression = new ExpressionPropertyDefinition(
				"AlllocationRankingExpression");
        alllocationRankingExpression.DisplayName(EngineResources.Material_AllocationRankingExpression_DisplayName);
        alllocationRankingExpression.Description(EngineResources.Material_AllocationRankingExpression_Description);
        alllocationRankingExpression.ParentPropertyDefinition(allocationRankingRule);
        alllocationRankingExpression.SwitchNumericProperty(allocationRankingRule);
        alllocationRankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        alllocationRankingExpression.SwitchNumericValue(1.0);
        alllocationRankingExpression.DefaultString("0.0");
        alllocationRankingExpression.ComplexityLevel(ProductComplexityLevel.Advanced);
        RepeatingPropertyDefinition billOfMaterials = new RepeatingPropertyDefinition("BillOfMaterials", this);
        billOfMaterials.DisplayName(EngineResources.Material_BillOfMaterials_DisplayName);
        billOfMaterials.Description(EngineResources.Material_BillOfMaterials_Description);
        billOfMaterials.CategoryName(EngineResources.CategoryName_BasicLogic);
        billOfMaterials.RequiredValue(false);
        ElementPropertyDefinition materialName = new ElementPropertyDefinition("MaterialName", Material.class);
        materialName.Description(EngineResources.Material_MaterialName_Description);
        materialName.DisplayName(EngineResources.Material_MaterialName_DisplayName);
        materialName.DefaultString("");
        materialName.RequiredValue(false);
        materialName.CategoryName(EngineResources.CategoryName_BasicLogic);
        NumericDataPropertyDefinition quantity = new NumericDataPropertyDefinition("Quantity", NumericDataType.Real,
				"1.0");
        quantity.DisplayName(EngineResources.Material_Quantity_DisplayName);
        quantity.Description(EngineResources.Material_Quantity_Description);
        quantity.DefaultString("1.0");
        quantity.CategoryName(EngineResources.CategoryName_BasicLogic);
        billOfMaterials.propertyDefinitions.add(materialName);
        billOfMaterials.propertyDefinitions.add(quantity);
        ExpressionPropertyDefinition costPerUnit = new ExpressionPropertyDefinition("CostPerUnit");
        costPerUnit.DisplayName(EngineResources.Material_CostPerUnit_DisplayName);
        costPerUnit.Description(EngineResources.Material_CostPerUnit_Description);
        costPerUnit.DefaultString("0.0");
        costPerUnit.CategoryName(EngineResources.CategoryName_Financials);
        costPerUnit.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Currency);
        costPerUnit.ComplexityLevel(ProductComplexityLevel.Advanced);
        BooleanPropertyDefinition logMaterialUsage = new BooleanPropertyDefinition("LogMaterialUsage");
        logMaterialUsage.DisplayName(EngineResources.Material_LogMaterialUsage_DisplayName);
        logMaterialUsage.Description(EngineResources.Material_LogMaterialUsage_Description);
        logMaterialUsage.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        logMaterialUsage.RequiredValue(false);
        logMaterialUsage.DefaultString("False");
        super.getPropertyDefinitions().add(initialQuantity);
        super.getPropertyDefinitions().add(allocationRankingRule);
        super.getPropertyDefinitions().add(alllocationRankingExpression);
        super.getPropertyDefinitions().add(billOfMaterials);
        super.getPropertyDefinitions().add(costPerUnit);
        super.getPropertyDefinitions().add(logMaterialUsage);
        BaseStatePropertyObject quantityAvailable = new BaseStatePropertyObject("QuantityAvailable", false, false);
        quantityAvailable.Description(EngineResources.Material_QuantityAvailable_Description);
        QueueStateObject<MaterialAllocation> allocationQueue = new QueueStateObject<>("AllocationQueue");
        allocationQueue.Description(EngineResources.Material_AllocationQueue_Description);
        super.getStateDefinitions().addStateProperty(quantityAvailable);
        super.getStateDefinitions().addStateProperty(allocationQueue);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Material(this, name, ElementScope.Private);
    }

    public static Material createMaterial(String name) {
        return (Material) MaterialDefinition.Definition.CreateInstance(name);
    }

    @Override
    public Class<?> ElementType() {
        return Material.class;
    }

    @Override
    public Class<?> RunSpaceType() {
        return MaterialElementRunSpace.class;
    }

    @Override
    public AbstractGridObjectDefinition DefaultDefinition() {
        return MaterialDefinition.Definition;
    }

    @Override
    public StringPropertyDefinition GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 37 && StringHelper.equalsLocal(name, "BillOfMaterial")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("BillOfMaterials");
        }
        if (intelligentObjectXml.FileVersion() < 44 && StringHelper.equalsLocal(name, "RankingRule")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("AllocationRankingRule");
        }
        if (intelligentObjectXml.FileVersion() < 44 && StringHelper.equalsLocal(name, "RankingExpression")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(
                    "AlllocationRankingExpression");
        }
        if (intelligentObjectXml.FileVersion() < 87 && StringHelper.equalsLocal(name, "LogUsage")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("LogMaterialUsage");
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }

    @BaseElementFunction("QuantityConsumed")
    public static double DsPfdqoomjW(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        MaterialElementRunSpace materialElementRunSpace = (MaterialElementRunSpace) absBaseRunSpace;
        return materialElementRunSpace.getQuantityConsumed();
    }

    @BaseElementFunction("QuantityProduced")
    public static double smethod_7(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        MaterialElementRunSpace materialElementRunSpace = (MaterialElementRunSpace) absBaseRunSpace;
        return materialElementRunSpace.getQuantityProduced();
    }

    @BaseElementFunction("MaterialCostCharged")
    @UnitClass(UnitType.Currency)
    public static double smethod_8(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        MaterialElementRunSpace materialElementRunSpace = (MaterialElementRunSpace) absBaseRunSpace;
        return materialElementRunSpace.getMaterialCostCharged();
    }

    @BaseElementFunction("AverageQuantityAvailable")
    public static double smethod_9(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        MaterialElementRunSpace materialElementRunSpace = (MaterialElementRunSpace) absBaseRunSpace;
        return materialElementRunSpace.getAverageQuantityAvailable();
    }

    @BaseElementFunction("MinimumQuantityAvailable")
    public static double smethod_10(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        MaterialElementRunSpace materialElementRunSpace = (MaterialElementRunSpace) absBaseRunSpace;
        return materialElementRunSpace.getMinimumQuantityAvailable();
    }

    @BaseElementFunction("MaximumQuantityAvailable")
    public static double smethod_11(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        MaterialElementRunSpace materialElementRunSpace = (MaterialElementRunSpace) absBaseRunSpace;
        return materialElementRunSpace.getMaximumQuantityAvailable();
    }
}
