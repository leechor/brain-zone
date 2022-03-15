package org.licho.brain.concrete.step;

import org.licho.brain.annotations.StepCategory;
import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.BatchLogic;
import org.licho.brain.concrete.ElementPropertyDefinition;
import org.licho.brain.concrete.Resources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.brainEnums.BatchCategories;
import org.licho.brain.brainEnums.BatchEntityType;

@StepCategory(category = "Regular", pairedStep = UnBatchStepDefinition.class)
public class BatchStepDefinition extends AbsBaseStepDefinition<BatchStepDefinition> {

    private BatchStepDefinition() {
        super("Batch");
        super.Description(Resources.StepDescription_Batch);
        EnumPropertyDefinition entityType = new EnumPropertyDefinition("EntityType", BatchEntityType.class);
        entityType.DisplayName(Resources.Batch_EntityType_DisplayName);
        entityType.Description(Resources.Batch_EntityType_Description);
        entityType.DefaultString(BatchEntityType.AssociatedObject.toString());
        entityType.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        entityType.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition entityObject = new ElementPropertyDefinition("EntityObject");
        entityObject.DisplayName(Resources.Batch_EntityObject_DisplayName);
        entityObject.Description(Resources.Batch_EntityObject_Description);
        entityObject.DefaultString("");
        entityObject.RequiredValue(false);
        entityObject.CategoryName(Resources.StepPropertyCategory_AdvancedOptions);
        entityObject.SwitchNumericProperty(entityType);
        entityObject.SwitchNumericValue(2.0);
        entityObject.ComplexityLevel(ProductComplexityLevel.Advanced);
        ElementPropertyDefinition batchLogicName = new ElementPropertyDefinition("BatchLogicName", BatchLogic.class);
        batchLogicName.DisplayName(Resources.Batch_BatchLogicName_DisplayName);
        batchLogicName.Description(Resources.Batch_BatchLogicName_Description);
        batchLogicName.DefaultString("");
        batchLogicName.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        batchLogicName.RequiredValue(false);
        EnumPropertyDefinition category = new EnumPropertyDefinition("Category", BatchCategories.class);
        category.DisplayName(Resources.Batch_Category_DisplayName);
        category.Description(Resources.Batch_Category_Description);
        category.DefaultString(BatchCategories.Member.toString());
        category.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        super.getPropertyDefinitions().add(entityType);
        super.getPropertyDefinitions().add(entityObject);
        super.getPropertyDefinitions().add(batchLogicName);
        super.getPropertyDefinitions().add(category);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new BatchStepProperty(BatchStepDefinition.class, name);
    }

}
