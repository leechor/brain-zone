package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

import java.util.Objects;

/**
 *
 */
public class ExpressionParameter extends AbsInputParameter {
    public ExpressionParameter(IntelligentObjectDefinition parent) {
        super(parent);
    }

    public static ExpressionParameter readXmlExpressionParameter(XmlReader xmlReader,
                                                                 IntelligentObjectXml intelligentObjectXml,
                                                                 IntelligentObjectDefinition intelligentObjectDefinition
    ) {
        if (Objects.equals(xmlReader.Name(), "InputParameterExpression")) {
            ExpressionParameter expressionParameter = new ExpressionParameter(intelligentObjectDefinition);
            intelligentObjectDefinition.InputParameters().add(expressionParameter);
            expressionParameter.readXml(xmlReader, intelligentObjectXml);
            return expressionParameter;
        }
        return null;

    }

    @Override
    public IListener ChildObjectChangeListener() {
        return null;
    }

    @Override
    protected String XmlElementName() {
        return null;
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return false;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }
}
