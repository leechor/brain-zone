package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class ChangeDescription {
    private int version;
    private String description;

    private ChangeDescription() {
    }

    public static ChangeDescription readXml(XmlReader xmlReader) {
        if (xmlReader.Name() == "ChangeDescription") {
            ChangeDescription changeDescription = new ChangeDescription();
            changeDescription.readXmlChangeDescription(xmlReader);
            return changeDescription;
        }
        return null;
    }

    private void readXmlChangeDescription(XmlReader xmlReader) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ChangeDescription",
                attribute -> SomeXmlOperator.readXmlIntAttribute(attribute, "Version", t -> this.version = t),
                body -> SomeXmlOperator.readXMLText(body, t -> this.description = t));
    }

    public int Version() {
        return this.version;
    }

    public String Description() {
        return this.description;
    }

    public void Description(String description) {
        this.description = description;
    }

    public ChangeDescription(int version, String descrition) {
        this.version = version;
        this.description = descrition;
    }

}
