package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.PropertyGridFeel;

@PropertyDefinitionName("ExpressionProperty")
public class ExpressionPropertyDefinition extends NumericDataPropertyDefinition {
    private boolean supportForeignStates;
    private boolean supportActiveReferences;

    public ExpressionPropertyDefinition(String name) {
        super(name, NumericDataType.Real);
        this.defaultString = "0.0";
        this.dataFormat = DataFormat.String;
    }

    @Override
    public Class<?> NativeObjectType() {
        return String.class;
    }

    @Override
    public boolean vmethod_0() {
        return false;
    }

    public boolean SupportForeignStates() {
        return this.supportForeignStates;
    }

    public void SupportForeignStates(boolean value) {
        this.supportForeignStates = value;
        super.propertyChangedEventHandler("SupportForeignStates");
    }


    public boolean SupportActiveReferences() {
        return this.supportActiveReferences;
    }

    public void SupportActiveReferences(boolean value) {
        this.supportActiveReferences = value;
        super.propertyChangedEventHandler("SupportActiveReferences");
    }

    public boolean CanReferenceInputParameter() {
        return true;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new ExpressionPropertyRow(this, properties);
    }

    @Override
    public String GetGridObjectClassName() {
        return EngineResources.ExpressionProperty_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.ExpressionProperty_ClassDescription;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        super.method_2(gridItemProperties, gridObjectDefinition);
        GridItemProperty gridItemProperty =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        gridItemProperties.add(new GridItemProperty(EngineResources.ExpressionProperty_ForeignStatesName,
                gridItemProperty,
                -6,
                this.SupportForeignStates(),
                false,
                PropertyGridFeel.none,
                EngineResources.ExpressionProperty_ForeignStatesDescription,
                new PropertyOperator_0<>(Boolean.class, this::SupportForeignStates, this::SupportForeignStates)));

        super.method_40(gridItemProperties, gridObjectDefinition);
        return gridItemProperties;
    }

    @Override
    protected void WriteXmlAttributes(XmlWriter xmlWriter, PropertyDefinitions propertyDefinitions) {
        super.WriteXmlAttributes(xmlWriter, propertyDefinitions);
        if (this.SupportForeignStates()) {
            xmlWriter.WriteAttributeString("SupportForeign", "True");
        }
        if (!this.SupportActiveReferences()) {
            xmlWriter.WriteAttributeString("SupportActive", "False");
        }
    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {
        super.ReadXmlAttributes(xmlReader, propertyDefinitions);
        SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "SupportForeign", b -> this.supportForeignStates = b);
        SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "SupportActive", b -> supportActiveReferences = b);
    }

}
