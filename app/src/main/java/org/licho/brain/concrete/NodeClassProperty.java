package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

import java.util.Objects;
import java.util.UUID;

/**
 *
 */
public class NodeClassProperty extends AbsPropertyObject {
    private final ElementPropertyRow containerElementPropertyRow;
    private final ElementPropertyRow stationElementPropertyRow;
    private final SequenceDestinationPropertyRow nodeSequenceDestinationPropertyRow;
    private final EnumPropertyRow enumPropertyRow;
    private final NodeClassPropertyRow nodeClassPropertyRow;
    public int index;
    private final String id;

    private Location location = Location.DefaultLocation;

    public final String string_2 = "ExternalNode";

    public final String string_3 = "InitialValues";

    public final String string_4 = "InitialValue";
    private PropertyGridObjectOperator propertyGridObjectOperator;
    private String name;

    public NodeClassProperty(GridObjectDefinition definition, String name) {
        super(definition, name);
        this.index = -1;
        this.nodeClassPropertyRow = (NodeClassPropertyRow) this.properties.values.get(0);
        this.enumPropertyRow = (EnumPropertyRow) this.properties.values.get(1);
        this.nodeSequenceDestinationPropertyRow =
                (SequenceDestinationPropertyRow) this.properties.values.get(2);
        this.stationElementPropertyRow = (ElementPropertyRow) this.properties.values.get(3);
        this.containerElementPropertyRow = (ElementPropertyRow) this.properties.values.get(4);
        this.id = UUID.randomUUID().toString();
    }

    public static NodeClassProperty readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                            IntelligentObjectDefinition intelligentObjectDefinition, Enum27 enum27) {
        NodeClassProperty nodeClassProperty = NodeClassProperty.readXml(xmlReader, intelligentObjectDefinition);
        if (nodeClassProperty != null) {
            nodeClassProperty.readXmlExternalNode(xmlReader, intelligentObjectXml, enum27);
            return nodeClassProperty;
        }
        return null;
    }

    private void readXmlExternalNode(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Enum27 enum27) {

        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ExternalNode", (XmlReader attr) ->
                {
                    String name = attr.GetAttribute("Name");
                    if (!Strings.isNullOrEmpty(name) && !StringHelper.equalsLocal(this.InstanceName(), name)) {
                        if (this.Parent() != null && enum27 == NodeClassProperty.Enum27.One) {
                            this.InstanceName(this.Parent().GetUniqueName(name, false));
                        } else {
                            this.InstanceName(name);
                        }
                    }
                    String description = attr.GetAttribute("Description");
                    if (!Strings.isNullOrEmpty(description)) {
                        this.Description(description);
                    }
                    String locationAttr = attr.GetAttribute("Location");
                    if (!Strings.isNullOrEmpty(locationAttr)) {
                        Location location = Location.DefaultLocation;
                        if (Location.parse(locationAttr, location)) {
                            this.Location(location);
                        }
                    }
                    this.name = this.InstanceName();
                    SomeXmlOperator.readXmlAttributeString(attr, "ID", (String s) -> this.name = s);
                },
                (XmlReader body) -> this.properties.readXmlProperties(xmlReader, intelligentObjectXml) ||
                        (this.getPropertyGridObjectOperator() != null && this.getPropertyGridObjectOperator().readXml(xmlReader, "InitialValues", "InitialValue")));

    }

    public static NodeClassProperty readXml(XmlReader xmlReader,
                                            IntelligentObjectDefinition intelligentObjectDefinition) {
        NodeClassProperty nodeClassProperty = null;
        if (Objects.equals(xmlReader.Name(), "ExternalNode")) {
            return intelligentObjectDefinition.updateNodeClassProperty();
        }
        return nodeClassProperty;
    }

    public Location Location() {
        return this.location;
    }

    public void Location(Location value) {
        if (this.location != value) {
            Location location = value;
            this.location = value;
            if (super.parent != null) {
                super.parent.setLocation(this, location);
            }
        }
    }

    @Override
    public String InstanceName() {
        return super.InstanceName();
    }

    @Override
    public void InstanceName(String name) {
        String instanceName = this.InstanceName();
        super.InstanceName(name);
        if (super.parent != null) {
            super.parent.setInstanceName(this, instanceName);
        }
        super.propertyChanged(EngineResources.Instance_NameProperty_Name);
    }

    public NodeDefinition NodeClass() {
        return this.getNodeClassPropertyRow().getNodeFacility();
    }

    public void NodeClass(NodeDefinition value) {
        this.getNodeClassPropertyRow().StringValue(value.Name());
        super.propertyChanged(this.getNodeClassPropertyRow().Name());
    }

    public NodeInputLogicType InputLocationType() {
        return (NodeInputLogicType) this.getEnumPropertyRow().enumValue;
    }

    public void InputLocationType(NodeInputLogicType value) {
        this.getEnumPropertyRow().StringValue(value.toString());
        super.propertyChanged(this.getEnumPropertyRow().Name());
    }


    @Override
    public String GetGridObjectClassName() {
        return null;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public Object PropertyUpdated(IntelligentObjectProperty intelligentObjectProperty, boolean error,
                                  Object param2) {
        return null;
    }


    @Override
    public String[] DisplayedValuesNeeded(int param0) {
        return new String[0];
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    @Override
    public IntelligentObjectProperty GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 80) {
            if (name.equals("NodeClass")) {
                return this.getNodeClassPropertyRow();
            }
            if (name.equals("InputLogicType")) {
                return this.getEnumPropertyRow();
            }
            if (name.equals("Station")) {
                return this.getStationElementProperty();
            }
            if (name.equals("Node")) {
                return this.getNodeSequenceDestinationPropertyRow();
            }
            if (name.equals("Container")) {
                return this.getContainerElementProperty();
            }
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }


    public NodeClassPropertyRow getNodeClassPropertyRow() {
        return nodeClassPropertyRow;
    }

    public EnumPropertyRow getEnumPropertyRow() {
        return enumPropertyRow;
    }

    public ElementPropertyRow getStationElementProperty() {
        return stationElementPropertyRow;
    }

    public SequenceDestinationPropertyRow getNodeSequenceDestinationPropertyRow() {
        return nodeSequenceDestinationPropertyRow;
    }

    public ElementPropertyRow getContainerElementProperty() {
        return containerElementPropertyRow;
    }

    public PropertyGridObjectOperator getPropertyGridObjectOperator() {
        if (this.propertyGridObjectOperator == null) {
            this.propertyGridObjectOperator = new PropertyGridObjectOperator(super.Parent(), false,
                    () -> {
                        if (this.NodeClass() == null) {
                            return NodeDefinition.NodeFacility.getPropertyDefinitions();
                        }
                        return this.NodeClass().getPropertyDefinitions();
                    });
        }
        return this.propertyGridObjectOperator;
    }

    public enum Enum27 {
        Zero,
        One
    }
}
