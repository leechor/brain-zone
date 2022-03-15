package org.licho.brain.concrete.step;

import org.licho.brain.annotations.StepCategory;
import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.DynamicObjectInstancePropertyDefinition;
import org.licho.brain.concrete.ElementPropertyDefinition;
import org.licho.brain.concrete.ExitPointPropertyDefinition;
import org.licho.brain.concrete.ExpressionPropertyDefinition;
import org.licho.brain.concrete.PropertyDefinitions;
import org.licho.brain.concrete.Resources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.simioEnums.CreateType;

@StepCategory(category = "Common Steps", pairedStep = DestroyStepDefinition.class)
public class CreateStepDefinition extends AbsBaseStepDefinition<CreateStepDefinition> {

    private CreateStepDefinition() {
        super("Create");
        super.Description(Resources.StepDescription_Create);
        super.getExitPointPropertyDefinition().Name("Original");
        super.getExitPointPropertyDefinition().DisplayName(Resources.Create_OriginalExit_DisplayName);
        super.getExitPointPropertyDefinition().Description(Resources.Create_OriginalExit_Description);
        ExitPointPropertyDefinition created = new ExitPointPropertyDefinition("Created");
        created.DisplayName(Resources.Create_CreatedExit_DisplayName);
        created.Description(Resources.Create_CreatedExit_Description);
        EnumPropertyDefinition createType = new EnumPropertyDefinition("CreateType", CreateType.class);
        createType.DisplayName(Resources.Create_CreateType_DisplayName);
        createType.Description(Resources.Create_CreateType_Description);
        createType.DefaultString(CreateType.NewObject.toString());
        createType.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        ElementPropertyDefinition sourceEntityObject = new ElementPropertyDefinition("SourceEntityObject");
        sourceEntityObject.DisplayName(Resources.Create_SourceEntityObject_DisplayName);
        sourceEntityObject.Description(Resources.Create_SourceEntityObject_Description);
        sourceEntityObject.DefaultString("");
        sourceEntityObject.RequiredValue(false);
        sourceEntityObject.SwitchNumericProperty(createType);
        sourceEntityObject.SwitchNumericValue(3.0);
        sourceEntityObject.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        DynamicObjectInstancePropertyDefinition objectInstanceName = new DynamicObjectInstancePropertyDefinition(
				"ObjectInstanceName");
        objectInstanceName.DisplayName(Resources.Create_EntityType_DisplayName);
        objectInstanceName.Description(Resources.Create_EntityType_Description);
        objectInstanceName.RequiredValue(false);
        objectInstanceName.DefaultString("");
        objectInstanceName.UseDefaultEntity(false);
        objectInstanceName.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        ExpressionPropertyDefinition numberOfObjects = new ExpressionPropertyDefinition("NumberOfObjects");
        numberOfObjects.DisplayName(Resources.Create_NumberOfObjects_DisplayName);
        numberOfObjects.Description(Resources.Create_NumberOfObjects_Description);
        numberOfObjects.DefaultString("1");
        numberOfObjects.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        PropertyDefinitions definitions = super.getPropertyDefinitions();
        definitions.add(created);
        definitions.add(createType);
        definitions.add(sourceEntityObject);
        definitions.add(objectInstanceName);
        definitions.add(numberOfObjects);
        this.level = 3;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new CreateStepProperty(CreateStepDefinition.class, name);
    }

}
