package org.licho.brain.concrete;

import org.licho.brain.annotations.PropertyDefinitionFactory;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.PropertyGridFeel;

@PropertyDefinitionName("SequenceDestinationProperty")
@PropertyDefinitionFactory(SequenceDestinationPropertyFactory.class)
public class SequenceDestinationPropertyDefinition extends StringPropertyDefinition {

    private boolean acceptsAnyNode = true;

    public SequenceDestinationPropertyDefinition(String name) {
        super(name);
        super.DefaultString(super.NullNullString());
        this.dataFormat = DataFormat.List;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new SequenceDestinationPropertyRow(this, properties);
    }

    @Override
    public String getObjectClassName() {
        return EngineResources.SequenceDestinationProperty_ClassName;
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.SequenceDestinationProperty_ClassDescription;
    }

    @Override
    IValueProvider ValueProvider() {
        return new SequenceDestinationPropertyValueProvider(super.NullNullString(), this.AcceptsAnyNode());
    }

    public boolean AcceptsAnyNode() {
        return this.acceptsAnyNode;
    }

    public void AcceptsAnyNode(boolean value) {
        this.acceptsAnyNode = value;
        super.propertyChangedEventHandler("AcceptsAnyNode");
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        super.method_2(gridItemProperties, gridObjectDefinition);
        GridItemProperty categoryName =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_Value);
        String sequenceDestinationProperty_AcceptsAnyNodeName =
                EngineResources.SequenceDestinationProperty_AcceptsAnyNodeName;
        String sequenceDestinationProperty_AcceptsAnyNodeDescription =
                EngineResources.SequenceDestinationProperty_AcceptsAnyNodeDescription;

        gridItemProperties.add(new GridItemProperty(sequenceDestinationProperty_AcceptsAnyNodeName, categoryName,
                -314,
                this.AcceptsAnyNode(), true, PropertyGridFeel.none,
                sequenceDestinationProperty_AcceptsAnyNodeDescription,
                new PropertyOperator_0<>(Boolean.class, this::AcceptsAnyNode, this::AcceptsAnyNode,
                        () -> new String[]{"True", "False"})));
        return gridItemProperties;
    }

    @Override
    public AbstractGridObjectDefinition ElementReferenceValueType() {
        return NodeDefinition.NodeFacility;
    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {
        super.ReadXmlAttributes(xmlReader, propertyDefinitions);
        SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "AcceptsAnyNode", this::AcceptsAnyNode);
    }
}
