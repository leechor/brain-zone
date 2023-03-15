package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

import java.util.function.Predicate;

/**
 *
 */
public class ChangeoverMatrices extends BindingList<ChangeoverMatrix> {
    private final IntelligentObjectDefinition assigner;

    public ChangeoverMatrices(IntelligentObjectDefinition assigner) {
        this.assigner = assigner;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ChangeoverMatrices", null,
                (XmlReader body) -> ChangeoverMatrix.readXml(xmlReader, intelligentObjectXml,
						this.assigner) != null);
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
