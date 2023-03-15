package org.licho.brain.concrete;

import lombok.AllArgsConstructor;
import lombok.Builder;
import org.licho.brain.annotations.PropertyDefinitionFactory;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.ElementReferenceType;
import org.licho.brain.enu.PropertyGridFeel;

import java.text.MessageFormat;

@PropertyDefinitionName("ElementProperty")
@PropertyDefinitionFactory(ElementPropertyFactory.class)
@AllArgsConstructor
public class ElementPropertyDefinition extends StringPropertyDefinition {
    private Class<?> type;
    private AbsDefinition absDefinition;
    private String name;
    private ElementReferenceType elementReferenceType;
    private PropertyGridObjectOperator propertyGridObjectOperator;

    public ElementPropertyDefinition(String name) {
        super(name);
    }

    public ElementPropertyDefinition(String name, Class<?> type) {
        super(name);
        this.defaultString = super.NullNullString();
        this.dataFormat = DataFormat.List;
        this.type = type;
    }


    public Class<?> getType() {
        return type;
    }

    public void setType(Class<?> type) {
        this.type = type;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new ElementPropertyRow(this, properties);
    }

    AbsDefinition getAbsDefinition() {
        if (this.absDefinition == null) {
            this.absDefinition =
                    AbsDefinition.getDefinitions().stream().filter(t -> t.ElementType() == this.type)
                            .findFirst().orElse(null);
        }
        return this.absDefinition;
    }

    public String getName() {
        if (this.name == null) {
            AbsDefinition definition = this.getAbsDefinition();
            if (definition != null) {
                this.name = definition.Name();
            }
        }
        return this.name;
    }


    @Override
    public boolean QualifyAsValidReference(StringPropertyDefinition StringPropertyDefinition) {
        if (super.QualifyAsValidReference(StringPropertyDefinition)) {
            if (this.type == null) {
                return StringPropertyDefinition instanceof ElementPropertyDefinition || StringPropertyDefinition instanceof
                        ObjectInstancePropertyDefinition;
            }
            ElementPropertyDefinition elementPropertyDefinition = (ElementPropertyDefinition) StringPropertyDefinition;
            if (elementPropertyDefinition.type == this.type) {
                return true;
            }
        }
        return false;
    }

    @Override
    public AbsDefinition ElementReferenceValueType() {
        return this.getAbsDefinition();
    }

    @Override
    public boolean ElementReferenceValueIsAnyElement() {
        return this.type == null;
    }

    @Override
    public String getObjectClassName() {
        String arg = "Element";
        if (this.type != null) {
            arg = this.getName();
        }
        return MessageFormat.format(EngineResources.ElementProperty_ClassName, arg);
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.ElementProperty_ClassDescription;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        super.getPropertyItemList(gridItemProperties, gridObjectDefinition);
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_AdvancedOptions);
        gridItemProperties.add(new GridItemProperty(EngineResources.ElementProperty_ReferenceTypeName, gridItemProperty
                , -10, this.ReferenceType(), ElementReferenceType.Reference, PropertyGridFeel.list,
                EngineResources.ElementProperty_ReferenceTypeDescription,
                new ElementPropertyDefinition.ElementPropertyOperator(this)));
        String elementProperty_InitialPropertyValuesName = EngineResources.ElementProperty_InitialPropertyValuesName;
        GridItemProperty itemProperty = gridItemProperty;
        int param3 = -11;
        Object param4 = this.getDescription();
        Object param5 = this.getDescription();
        PropertyGridFeel param6 = PropertyGridFeel.button;
        String elementProperty_InitialPropertyValuesDescription =
                EngineResources.ElementProperty_InitialPropertyValuesDescription;

        gridItemProperties.add(new GridItemProperty(elementProperty_InitialPropertyValuesName, itemProperty, param3,
                param4, param5, param6, elementProperty_InitialPropertyValuesDescription,
                new PropertyOperator_0<>(String.class, this::getDescription, t -> {
                }, null, null, null,
                        () -> this.ReferenceType() == ElementReferenceType.Create, null,
                        this::getPropertyGridObjectOperator,
                        null)));
        return gridItemProperties;
    }

    private String getDescription() {
        return MessageFormat.format("%s Rows", this.getPropertyGridObjectOperator().Count());

    }

    public PropertyGridObjectOperator getPropertyGridObjectOperator() {
        if (this.propertyGridObjectOperator == null) {
            this.propertyGridObjectOperator = new PropertyGridObjectOperator(null, false,
                    () -> this.getAbsDefinition().getPropertyDefinitions());
        }
        return this.propertyGridObjectOperator;
    }


    public ElementReferenceType ReferenceType() {
        return this.elementReferenceType;
    }

    public void ReferenceType(ElementReferenceType value) {
        this.setReferenceType(value, null);
    }

    private Object setReferenceType(ElementReferenceType elementReferenceType, Object param1) {
        if (elementReferenceType != this.ReferenceType()) {
            ElementReferenceType referenceType = this.ReferenceType();
            this.elementReferenceType = elementReferenceType;
            if (this.owner != null) {
                return this.owner.method_20(this, this.owner, referenceType, this.ReferenceType(), param1);
            }
        }
        return null;
    }


    public class ElementPropertyOperator extends PropertyOperator_0<ElementReferenceType> implements IReferenceOperator {
        private ElementPropertyDefinition elementPropertyDefinition;

        public ElementPropertyOperator(ElementPropertyDefinition elementPropertyDefinition) {
            super(ElementReferenceType.class, null, null);
            this.elementPropertyDefinition = elementPropertyDefinition;
        }

        @Override
        protected void SetPropertyValue(ElementReferenceType elementReferenceType) {
            this.elementPropertyDefinition.ReferenceType(elementReferenceType);
        }

        @Override
        protected ElementReferenceType GetPropertyValue() {
            return this.elementPropertyDefinition.ReferenceType();
        }


        @Override
        public Object setReferenceType(Object param0, Object param1) {
            return this.elementPropertyDefinition.setReferenceType((ElementReferenceType) param0, param1);
        }
    }
}
