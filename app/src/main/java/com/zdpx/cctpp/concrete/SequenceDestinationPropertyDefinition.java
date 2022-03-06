package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.annotations.PropertyDefinitionFactory;
import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;
import com.zdpx.cctpp.enu.PropertyGridFeel;

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
    public String GetGridObjectClassName() {
        return EngineResources.SequenceDestinationProperty_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
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
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
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
    public AbsDefinition ElementReferenceValueType() {
        return NodeDefinition.NodeFacility;
    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {
        super.ReadXmlAttributes(xmlReader, propertyDefinitions);
        SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "AcceptsAnyNode", this::AcceptsAnyNode);
    }
}
