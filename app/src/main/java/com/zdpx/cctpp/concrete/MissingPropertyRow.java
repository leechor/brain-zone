package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.fake.XmlWriter;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;

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
