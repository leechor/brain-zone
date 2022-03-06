package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

import java.util.List;
import java.util.Objects;

/**
 *
 */
public class ChangeStringReplacement {
    private int version;
    private String regularExpressionPattern;
    private String replacementString;


    public ChangeStringReplacement(int version, String regularExpressionPattern, String replacementString) {
        this.version = version;
        this.regularExpressionPattern = regularExpressionPattern;
        this.replacementString = replacementString;
    }

    public ChangeStringReplacement() {

    }

    public static void replaceAll(IntelligentObjectDefinition iIntelligentObjects,
                                  List<ChangeStringReplacement> replacements) {
    }

    public static ChangeStringReplacement xmlReader(XmlReader xmlReader) {
        if (Objects.equals(xmlReader.Name(), "ChangeStringReplacement")) {
            ChangeStringReplacement changeStringReplacement = new ChangeStringReplacement();
            changeStringReplacement.readXml(xmlReader);
            return changeStringReplacement;
        }
        return null;
    }

    private void readXml(XmlReader xmlReader) {
        String param = "ChangeStringReplacement";
        SomeXmlOperator.AttributeXmlDelegate attributeXmlDelegate = attribute -> {
                    SomeXmlOperator.readXmlIntAttribute(attribute, "Version", t -> this.version = t);
                    SomeXmlOperator.readXmlAttributeString(attribute, "RegularExpressionPattern",
                            t -> this.regularExpressionPattern = t);
                    SomeXmlOperator.readXmlAttributeString(attribute, "ReplacementString",
                            t -> this.replacementString = t);
                };
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, param, attributeXmlDelegate, t -> true);
    }

    public int Version() {
        return this.version;
    }

    public String RegularExpressionPattern() {
        return this.regularExpressionPattern;
    }

    public String ReplacementReplacementString() {
        return this.replacementString;
    }


}
