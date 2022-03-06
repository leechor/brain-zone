package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

/**
 *
 */
public class ExpressionFunctions extends BindingList<ExpressionFunction> {
    private final IntelligentObjectDefinition parent;

    public ExpressionFunctions(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.parent = intelligentObjectDefinition;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ExpressionFunctions", null, (XmlReader body) ->
        ExpressionFunction.readXmlExpressionFunction(xmlReader, intelligentObjectXml, this.parent) != null);
    }
}
