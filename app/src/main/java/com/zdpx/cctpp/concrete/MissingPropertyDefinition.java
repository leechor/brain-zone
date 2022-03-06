package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.fake.XmlWriter;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;

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
