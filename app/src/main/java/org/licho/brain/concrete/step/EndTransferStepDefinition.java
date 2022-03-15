package org.licho.brain.concrete.step;

import org.licho.brain.annotations.StepCategory;
import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.ElementPropertyDefinition;
import org.licho.brain.concrete.Resources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.brainEnums.TransferObjectType;

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
