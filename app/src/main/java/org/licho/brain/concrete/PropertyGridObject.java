package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

import java.text.MessageFormat;
import java.util.Objects;


public class PropertyGridObject implements IGridObject {

    private final PropertyGridObjectOperator propertyGridObjectOperator;
    private String propertyName;
    private String name;

    public PropertyGridObject(PropertyGridObjectOperator propertyGridObjectOperator) {
        this.propertyGridObjectOperator = propertyGridObjectOperator;
    }

    public String PropertyName() {
        return this.propertyName;
    }

    public void PropertyName(String value) {
        this.propertyName = value;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
        if (this.propertyGridObjectOperator != null && this.propertyGridObjectOperator.getIntelligentObjectDefinition() != null) {
            this.propertyGridObjectOperator.getIntelligentObjectDefinition().resetTable(255);
        }
    }


    boolean readXml(XmlReader xmlReader, String name) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, name, (XmlReader attr) -> {
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Name", this::PropertyName);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Value", this::setName);
        }, null);
    }

    static PropertyGridObject readXml(XmlReader xmlReader, String name,
                                      PropertyGridObjectOperator propertyGridObjectOperator) {
        if (Objects.equals(xmlReader.Name(), name)) {
            PropertyGridObject propertyGridObject = (PropertyGridObject) propertyGridObjectOperator.AddNew();
            propertyGridObject.readXml(xmlReader, name);
            if (propertyGridObject.getName() == null) {
                propertyGridObject.setName("");
            }
            return propertyGridObject;
        }
        return null;
    }

    @Override
    public String toString() {
        return MessageFormat.format("Name: '{0}', Value: '{1}'", this.PropertyName() == null ? "" : this.PropertyName(),
                this.getName() == null ? "" : this.getName());
    }

    @Override
    public String getObjectClassName() {
        return "";
    }

    @Override
    public String getObjectDescription() {
        return this.PropertyName();
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        return new String[0];
    }
}
