package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.brainEnums.ValidObjectType;

@PropertyDefinitionName("DynamicObjectInstanceProperty")
public class DynamicObjectInstancePropertyDefinition extends ObjectInstancePropertyDefinition {
    private boolean useDefaultEntity;

    public DynamicObjectInstancePropertyDefinition(String name) {
        super(name, ValidObjectType.Agent);
        this.UseDefaultEntity(true);
    }

    public Boolean UseDefaultEntity() {
        return this.useDefaultEntity;
    }

    ;

    public void UseDefaultEntity(boolean value) {
        this.useDefaultEntity = value;
    }


    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new DynamicObjectInstancePropertyRow(this, properties);
    }

    @Override
    public String getObjectClassName() {
        return EngineResources.DynamicObjectInstanceProperty_ClassName;
    }

    @Override
    public AbstractGridObjectDefinition ElementReferenceValueType() {
        return AgentDefinition.AgentFacility;
    }

    @Override
    public boolean ElementReferenceValueIsIndexed() {
        return true;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        super.getPropertyItemList(gridItemProperties, gridObjectDefinition);
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        gridItemProperties.add(new GridItemProperty(EngineResources.DynamicObjectInstanceProperty_UseDefaultEntityName,
                gridItemProperty, -4899, this.UseDefaultEntity(), true, PropertyGridFeel.list,
                EngineResources.DynamicObjectInstanceProperty_UseDefaultEntityDescription,
                new PropertyOperator_0<>(Boolean.class, this::UseDefaultEntity, this::UseDefaultEntity)));
        return gridItemProperties;
    }
}
