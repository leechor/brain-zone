package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.ElementPropertyDefinition;
import org.licho.brain.concrete.ExitPointPropertyDefinition;
import org.licho.brain.concrete.Resources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.brainEnums.DestroyType;

/**
 *
 */
public class DestroyStepDefinition extends AbsBaseStepDefinition<DestroyStepDefinition> {

    private DestroyStepDefinition() {
        super("Destroy");
        super.Description(Resources.StepDescription_Destroy);
        super.getExitPointPropertyDefinition().Name("OK");
        super.getExitPointPropertyDefinition().Description(Resources.Destroy_OkExit_Description);
        super.getExitPointPropertyDefinition().DisplayName(Resources.Destroy_OkExit_DisplayName);
        ExitPointPropertyDefinition failed = new ExitPointPropertyDefinition("Failed");
        failed.DisplayName(Resources.Destroy_FailedExit_DisplayName);
        failed.Description(Resources.Destroy_FailedExit_Description);
        EnumPropertyDefinition destroyType = new EnumPropertyDefinition("DestroyType", DestroyType.class);
        destroyType.DisplayName(Resources.Destroy_DestroyType_DisplayName);
        destroyType.Description(Resources.Destroy_DestroyType_Description);
        destroyType.DefaultString(DestroyType.AssociatedObject.toString());
        destroyType.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        destroyType.RequiredValue(true);
        ElementPropertyDefinition entityObject = new ElementPropertyDefinition("EntityObject");
        entityObject.DisplayName(Resources.Destroy_EntityObject_DisplayName);
        entityObject.Description(Resources.Destroy_EntityObject_Description);
        entityObject.DefaultString("");
        entityObject.RequiredValue(false);
        entityObject.CategoryName(Resources.StepPropertyCategory_BasicLogic);
        entityObject.SwitchNumericProperty(destroyType);
        entityObject.SwitchNumericValue(2.0);
        super.getPropertyDefinitions().add(failed);
        super.getPropertyDefinitions().add(destroyType);
        super.getPropertyDefinitions().add(entityObject);
        this.level = 3;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new DestroyStepProperty(DestroyStepDefinition.class, name);
    }

}
