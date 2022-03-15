package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

import java.util.Objects;

/**
 *
 */
public abstract class MaybeReadXmlStepProperty<T> extends AbsStepProperty<T> {
    private String typeName;
    private String outerXml;

    public MaybeReadXmlStepProperty(Class<T> cl, String name) {
        super( cl, name);
    }

    @Override
    protected String TypeName() {
        return this.typeName;
    }

    @Override
    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        if (Objects.equals(xmlReader.Name(), "Properties")) {
            this.outerXml = xmlReader.ReadOuterXml();
            return true;
        }
        return false;
    }

    @Override
    protected void ReadAttributesFromXml(XmlReader xmlReader) {
        SomeXmlOperator.readXmlAttributeString(xmlReader, "Type", t -> this.typeName = t);
        super.ReadAttributesFromXml(xmlReader);
    }
}
