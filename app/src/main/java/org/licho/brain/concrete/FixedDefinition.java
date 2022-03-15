package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.fixed.Fixed;
import org.licho.brain.enu.FixedInterfaceProcess;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.simioEnums.SwitchNumericConditions;
import org.licho.brain.simioEnums.TransferConstraintsType;
import org.licho.brain.utils.simu.IEntityProcess;

import java.util.Collection;

/**
 *
 */
public class FixedDefinition extends IntelligentObjectDefinition {
    public static final Guid guid = new Guid("{D436E34A-6628-4ac6-AA5C-14FBE45E69E5}");
    public Location location;

    public static final String name = "Fixed";

    public static final FixedDefinition FixedFacility = new FixedDefinition((ActiveModel) null);

    static {
        InternalReference.put(FixedDefinition.name, FixedDefinition.FixedFacility);
    }

    private Entity entity;

    public FixedDefinition(String name) {
        super(name);
    }

    public FixedDefinition(String name, ActiveModel activeModel, IntelligentObjectDefinition parent) {
        super(name, activeModel, parent);
    }

    public FixedDefinition(String name, ActiveModel activeModel, Guid guid) {
        super(name, activeModel, guid);
        super.ResourceLogic(true);
    }

    private FixedDefinition(ActiveModel activeModel) {
        this(FixedDefinition.name, activeModel, FixedDefinition.guid);
    }

    @Override
    protected void DefineSchema() {

        super.DefineSchema();
        EnumPropertyDefinition transferInConstraintsType = new EnumPropertyDefinition("TransferInConstraintsType",
                TransferConstraintsType.class);
        transferInConstraintsType.DisplayName(EngineResources.FixedObject_TransferInConstraintsType_DisplayName);
        transferInConstraintsType.Description(EngineResources.FixedObject_TransferInConstraintsType_Description);
        transferInConstraintsType.DefaultString(TransferConstraintsType.Default.toString());
        transferInConstraintsType.stringValues = new String[]{
                "Disable",
                "Default",
                "Custom Condition"
        };
        transferInConstraintsType.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        transferInConstraintsType.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition transferInCondition = new ExpressionPropertyDefinition("TransferInCondition");
        transferInCondition.Description(EngineResources.FixedObject_TransferInCondition_Description);
        transferInCondition.DisplayName(EngineResources.FixedObject_TransferInCondition_DisplayName);
        transferInCondition.DefaultString("");
        transferInCondition.ParentPropertyDefinition(transferInConstraintsType);
        transferInCondition.RequiredValue(false);
        transferInCondition.SwitchNumericProperty(transferInConstraintsType);
        transferInCondition.SwitchNumericCondition(SwitchNumericConditions.Equal);
        transferInCondition.SwitchNumericValue(2.0);
        transferInCondition.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition transferOutConstraintsType = new EnumPropertyDefinition("TransferOutConstraintsType",
                TransferConstraintsType.class);
        transferOutConstraintsType.DisplayName(EngineResources.FixedObject_TransferOutConstraintsType_DisplayName);
        transferOutConstraintsType.Description(EngineResources.FixedObject_TransferOutConstraintsType_Description);
        transferOutConstraintsType.DefaultString(TransferConstraintsType.Default.toString());
        transferOutConstraintsType.stringValues = new String[]{
                "Disable",
                "Default",
                "Custom Condition"
        };
        transferOutConstraintsType.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        transferOutConstraintsType.ComplexityLevel(ProductComplexityLevel.Advanced);
        ExpressionPropertyDefinition transferOutCondition = new ExpressionPropertyDefinition("TransferOutCondition");
        transferOutCondition.Description(EngineResources.FixedObject_TransferOutCondition_Description);
        transferOutCondition.DisplayName(EngineResources.FixedObject_TransferOutCondition_DisplayName);
        transferOutCondition.DefaultString("");
        transferOutCondition.ParentPropertyDefinition(transferOutConstraintsType);
        transferOutCondition.RequiredValue(false);
        transferOutCondition.SwitchNumericProperty(transferOutConstraintsType);
        transferOutCondition.SwitchNumericCondition(SwitchNumericConditions.Equal);
        transferOutCondition.SwitchNumericValue(2.0);
        transferOutCondition.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(transferInConstraintsType);
        super.getPropertyDefinitions().add(transferInCondition);
        super.getPropertyDefinitions().add(transferOutConstraintsType);
        super.getPropertyDefinitions().add(transferOutCondition);
        super.createProcessProperties(ObjectClass.Fixed);
        if (!this.vmethod_0()) {
            super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("TransferInConstraintsType").SetLocalVisible(false, super.getPropertyDefinitions());
            super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("TransferInCondition").SetLocalVisible(false, super.getPropertyDefinitions());
            super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("TransferOutConstraintsType").SetLocalVisible(false, super.getPropertyDefinitions());
            super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("TransferOutCondition").SetLocalVisible(false, super.getPropertyDefinitions());
        }

    }

    protected void OnInitializeFacilityLocation(double x, double y, double z) {
    }

    @Override
    public IntelligentObjectDefinition CreateNewBaseClassDefinition() {
        return FixedDefinition.create();
    }

    public static IntelligentObjectDefinition create() {
        return new FixedDefinition((ActiveModel) null);
    }

    @Override
    public ObjectClass ObjectClass() {
        return ObjectClass.Fixed;
    }

    protected boolean vmethod_0() {
        return true;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Fixed(this, name, ElementScope.Private);
    }

    @Override
    public void GetInterfaceProcessInformation(Collection<IEntityProcess> infoList) {
        super.GetInterfaceProcessInformation(infoList);
        super.injectEntityProcessToObjectClass(infoList, FixedInterfaceProcess.class, ObjectClass.Fixed);
    }

    @Override
    public boolean DefaultRunnable() {
        return true;
    }

    public Entity DefaultEntity() {
        return this.entity;
    }

    public void DefaultEntity(Entity value) {
        this.entity = value;
    }

    public Entity createEntity(EntityDefinition entityDefinition) {
        this.entity = (Entity) super.createEntity(entityDefinition, ElementScope.Public, Location.DefaultLocation);
        this.entity.InstanceName(EngineResources.DefaultEntityInstanceName);
        return this.entity;
    }

    public boolean setEntity(Entity entity) {
        return entity == this.entity;
    }

    @Override
    public void DestroyObjectInstance(IntelligentObject intelligentObject) {
        if (this.entity == intelligentObject) {
            this.entity = null;
        }
        super.DestroyObjectInstance(intelligentObject);
    }

    @Override
    public IntelligentObjectDefinition.NodeRestoreHandle RemoveObjectInstance(IntelligentObject intelligentObject) {
        if (this.entity == intelligentObject) {
            this.entity = null;
        }
        return super.RemoveObjectInstance(intelligentObject);
    }

}
