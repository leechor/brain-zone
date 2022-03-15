package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 *
 */
public class MissingPropertyRow extends IntelligentObjectProperty {
    public MissingPropertyRow(MissingPropertyDefinition missingPropertyDefinition, Properties properties) {
        super(missingPropertyDefinition, properties);
    }

    public void ReadFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        this.outerXml = xmlReader.ReadOuterXml();
    }


    public void WriteToXml(XmlWriter xmlWriter, CommonItems commonItems) {
        xmlWriter.WriteRaw(this.outerXml);
    }

    public boolean ShouldWriteOutToXMLUnder(PropertyDefinitions propertyDefinitions, CommonItems commonItems) {
        return true;
    }

    private String outerXml;

}
