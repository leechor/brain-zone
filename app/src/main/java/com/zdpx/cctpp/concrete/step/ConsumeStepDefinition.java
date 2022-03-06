package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.annotations.StepCategory;
import com.zdpx.cctpp.concrete.AbsBaseStepDefinition;
import com.zdpx.cctpp.concrete.AbsPropertyObject;
import com.zdpx.cctpp.concrete.ElementPropertyDefinition;
import com.zdpx.cctpp.concrete.ExpressionPropertyDefinition;
import com.zdpx.cctpp.concrete.Material;
import com.zdpx.cctpp.concrete.Resources;
import com.zdpx.cctpp.concrete.entity.EnumPropertyDefinition;
import com.zdpx.cctpp.enu.BooleanType;
import com.zdpx.cctpp.enu.OwnerType;
import com.zdpx.cctpp.enu.ProductComplexityLevel;
import com.zdpx.cctpp.simioEnums.BOMActionRules;
import com.zdpx.cctpp.simioEnums.SwitchNumericConditions;

@StepCategory(category = "Regular")
public class ConsumeStepDefinition extends AbsBaseStepDefinition<ConsumeStepDefinition> {

    private ConsumeStepDefinition() {
        super("Consume");
        super.Description(Resources.StepDescription_Consume);
        EnumPropertyDefinition ownerType = new EnumPropertyDefinition("OwnerType", OwnerType.class);
        ownerType.DisplayName(Resources.Consume_OwnerType_DisplayName);
        ownerType.Description(Resources.Consume_OwnerType_Description);
        ownerType.DefaultString(OwnerType.AssociatedObject.toString());
        ownerType.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        ownerType.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition ownerObject = new ElementPropertyDefinition("OwnerObject");
        ownerObject.DisplayName(Resources.Consume_OwnerObject_DisplayName);
        ownerObject.Description(Resources.Consume_OwnerObject_Description);
        ownerObject.DefaultString("");
        ownerObject.RequiredValue(false);
        ownerObject.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        ownerObject.SwitchNumericProperty(ownerType);
        ownerObject.SwitchNumericValue(2.0);
        ownerObject.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition consumptionType = new EnumPropertyDefinition("ConsumptionType", BOMActionRules.class);
        consumptionType.Description(Resources.Consume_ConsumptionType_Description);
        consumptionType.DisplayName(Resources.Consume_ConsumptionType_DisplayName);
        consumptionType.DefaultString(BOMActionRules.Material.toString());
        consumptionType.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        ElementPropertyDefinition materialName = new ElementPropertyDefinition("MaterialName", Material.class);
        materialName.Description(Resources.Consume_MaterialName_Description);
        materialName.DisplayName(Resources.Consume_MaterialName_DisplayName);
        materialName.RequiredValue(false);
        materialName.DefaultString("");
        materialName.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        materialName.SwitchNumericProperty(consumptionType);
        materialName.SwitchNumericCondition(SwitchNumericConditions.NotEqual);
        materialName.SwitchNumericValue(2.0);
        ExpressionPropertyDefinition quantity = new ExpressionPropertyDefinition("Quantity");
        quantity.DisplayName(Resources.Consume_Quantity_DisplayName);
        quantity.Description(Resources.Consume_Quantity_Description);
        quantity.RequiredValue(false);
        quantity.DefaultString("1.0");
        quantity.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        quantity.SwitchNumericProperty(consumptionType);
        quantity.SwitchNumericCondition(SwitchNumericConditions.NotEqual);
        quantity.SwitchNumericValue(2.0);
        EnumPropertyDefinition accrueMaterialCosts = new EnumPropertyDefinition("AccrueMaterialCosts",
                BooleanType.class);
        accrueMaterialCosts.DisplayName(Resources.Consume_AccrueMaterialCosts_DisplayName);
        accrueMaterialCosts.Description(Resources.Consume_AccrueMaterialCosts_Description);
        accrueMaterialCosts.DefaultString("True");
        accrueMaterialCosts.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        accrueMaterialCosts.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(ownerType);
        super.getPropertyDefinitions().add(ownerObject);
        super.getPropertyDefinitions().add(consumptionType);
        super.getPropertyDefinitions().add(materialName);
        super.getPropertyDefinitions().add(quantity);
        super.getPropertyDefinitions().add(accrueMaterialCosts);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new ConsumeStepProperty(ConsumeStepDefinition.class, name);
    }

}
