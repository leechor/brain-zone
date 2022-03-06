package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.annotations.StepCategory;
import com.zdpx.cctpp.concrete.AbsBaseStepDefinition;
import com.zdpx.cctpp.concrete.AbsPropertyObject;
import com.zdpx.cctpp.concrete.ElementPropertyDefinition;
import com.zdpx.cctpp.concrete.Resources;
import com.zdpx.cctpp.concrete.entity.EnumPropertyDefinition;
import com.zdpx.cctpp.enu.ProductComplexityLevel;
import com.zdpx.cctpp.simioEnums.TransferObjectType;

@StepCategory(category = "Common Steps")
public class EndTransferStepDefinition extends AbsBaseStepDefinition<EndTransferStepDefinition> {
    private EndTransferStepDefinition() {
        super("EndTransfer");
        super.Description(Resources.StepDescription_EndTransfer);
        EnumPropertyDefinition entityType = new EnumPropertyDefinition("EntityType", TransferObjectType.class);
        entityType.DisplayName(Resources.EndTransfer_EntityType_DisplayName);
        entityType.Description(Resources.EndTransfer_EntityType_Description);
        entityType.DefaultString(TransferObjectType.AssociatedObject.toString());
        entityType.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        entityType.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition entityObject = new ElementPropertyDefinition("EntityObject");
        entityObject.DisplayName(Resources.EndTransfer_EntityObject_DisplayName);
        entityObject.Description(Resources.EndTransfer_EntityObject_Description);
        entityObject.DefaultString("");
        entityObject.RequiredValue(false);
        entityObject.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        entityObject.SwitchNumericProperty(entityType);
        entityObject.SwitchNumericValue(2.0);
        entityObject.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(entityType);
        super.getPropertyDefinitions().add(entityObject);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new EndTransferStepProperty(EndTransferStepDefinition.class, name);
    }
}
