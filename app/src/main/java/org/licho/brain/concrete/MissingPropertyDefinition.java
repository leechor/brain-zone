package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

@PropertyDefinitionName("MissingProperty")
public class MissingPropertyDefinition extends StringPropertyDefinition {
    public MissingPropertyDefinition(String name) {
        super(name);
    }


    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new MissingPropertyRow(this, properties);
    }

    @Override
    protected void ReadFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                               PropertyDefinitions propertyDefinitions, IIdentityName identityName) {
        super.readXmlAttribute(xmlReader, intelligentObjectXml, propertyDefinitions, identityName);
        this.outerXml = xmlReader.ReadOuterXml();
    }


    public void WriteToXml(XmlWriter xmlWriter, PropertyDefinitions propertyDefinitions) {
        xmlWriter.WriteRaw(this.outerXml);
    }


    private String outerXml;

}
