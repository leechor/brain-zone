package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;

import java.text.MessageFormat;

/**
 *
 */
public abstract class ListDefinition extends GridObjectDefinition {
    public ListDefinition(String name) {
        super(name);
        RepeatingPropertyDefinition listData = new RepeatingPropertyDefinition("ListData", this);
        listData.propertyDefinitions.add(this.instance());
        super.getPropertyDefinitions().add(listData);
        listData.RequiredValue(false);
        listData.SetLocalVisible(false, super.getPropertyDefinitions());
    }

	protected abstract StringPropertyDefinition instance();


	@Override
    public boolean IsJustAFactory() {
        return true;
    }

    public static AbsListProperty readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                          IntelligentObjectDefinition intelligentObjectDefinition) {
        AbsListProperty absListProperty = null;
        String name = xmlReader.Name();
        String a;
        if ((a = name) != null) {
            if (!("StringList".equals(a))) {
                if (!("NodeList".equals(a))) {
                    if (!("ObjectList".equals(a))) {
                        if ("TransporterList".equals(a)) {
                            absListProperty = intelligentObjectDefinition.getTransporterList(null);
                        }
                    } else {
                        absListProperty = intelligentObjectDefinition.getObjectList(null);
                    }
                } else {
                    absListProperty = intelligentObjectDefinition.getNodeList(null);
                }
            } else {
                absListProperty = intelligentObjectDefinition.getStringList(null);
            }
        }
        if (absListProperty != null) {
            absListProperty.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition);
        }
        if (absListProperty == null) {
            intelligentObjectXml.addWarning(MessageFormat.format(EngineResources.LoadWarning_CouldNotLoadListType, name));
        }
        return absListProperty;
    }
}
