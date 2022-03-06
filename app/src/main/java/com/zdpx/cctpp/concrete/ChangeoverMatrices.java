package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

import java.util.function.Predicate;

/**
 *
 */
public class ChangeoverMatrices extends BindingList<ChangeoverMatrix> {
    private final IntelligentObjectDefinition intelligentObjectFacility;

    public ChangeoverMatrices(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectFacility = intelligentObjectDefinition;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ChangeoverMatrices", null,
                (XmlReader body) -> ChangeoverMatrix.readXml(xmlReader, intelligentObjectXml,
						this.intelligentObjectFacility) != null);
    }

    public ChangeoverMatrix getChangeoverMatrix(Predicate<ChangeoverMatrix> action) {
        for (ChangeoverMatrix changeoverMatrix : this.values) {
            if (action.test(changeoverMatrix)) {
                return changeoverMatrix;
            }
        }
        return null;
    }
}
