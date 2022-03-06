package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.annotations.StepCategory;
import com.zdpx.cctpp.concrete.AbsBaseStepDefinition;
import com.zdpx.cctpp.concrete.AbsPropertyObject;
import com.zdpx.cctpp.concrete.DynamicObjectInstancePropertyDefinition;
import com.zdpx.cctpp.concrete.ElementPropertyDefinition;
import com.zdpx.cctpp.concrete.ExitPointPropertyDefinition;
import com.zdpx.cctpp.concrete.ExpressionPropertyDefinition;
import com.zdpx.cctpp.concrete.PropertyDefinitions;
import com.zdpx.cctpp.concrete.Resources;
import com.zdpx.cctpp.concrete.entity.EnumPropertyDefinition;
import com.zdpx.cctpp.simioEnums.CreateType;

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
