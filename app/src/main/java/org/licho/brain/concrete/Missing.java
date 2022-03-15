package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.brainEnums.ElementScope;

import javax.xml.stream.XMLStreamConstants;
import java.util.Objects;

/**
 *
 */
public class Missing extends AbsIntelligentPropertyObject {
    private String outXml;

    protected Missing(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new MissingElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }


    @Override
    protected AbsDefinition DefaultDefinition() {
        return MissingDefinition.Instance;
    }

    @Override
    public int IconIndex() {
        return -1;
    }

    protected boolean ReadWholeBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                           String name) {
        if (Objects.equals(xmlReader.Name(), name) && xmlReader.NodeType() == XMLStreamConstants.END_ELEMENT) {
            return false;
        }
        String outerXml = xmlReader.ReadOuterXml();
        if (this.outXml == null) {
            this.outXml = outerXml;
        } else {
            this.outXml += outerXml;
        }
        return true;
    }


    @Override
    protected void WriteWholeBodyToXml(XmlWriter xmlWriter, CommonItems commonItems) {
        this.properties.writeXmlProperties(xmlWriter, commonItems);
        this.WriteBodyToXml(xmlWriter);
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
}
