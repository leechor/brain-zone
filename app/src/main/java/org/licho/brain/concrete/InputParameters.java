package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class InputParameters extends BindingList<AbsInputParameter> {
    private final IntelligentObjectDefinition parent;

    public InputParameters(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.parent = intelligentObjectDefinition;
    }

    public boolean xmlReaderInputParameters(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "InputParameters", null,
                (XmlReader body) -> AbsInputParameter.readXml(xmlReader, intelligentObjectXml, this.parent) != null);
    }
}
