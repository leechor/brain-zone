package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class CompatibilitySettings {
    private final RunSetup runSetup;
    private Boolean changeNaNToZeroInExpressions;
    private Boolean dontUnitConvertStateParameters;
    private Boolean ensureForeignNodesCastToNonNodeTypesAreAssociatedNodes;
    private Integer maxExpressionStackSize;
    private Boolean alwaysAllowInfinityAsLinkSelectionWeightValue;
    private Boolean assumeInputNodeEntriesAreToAssociatedObjectIfNoDrawnOutboundLinks;
    private Boolean ignoreShortCircuitEvaluationInExpressions;
    private Boolean ignorePrimaryForeignKeyRelationshipsWhenEvaluatingTablePropertyRandomRowFunction;

    public CompatibilitySettings(RunSetup runSetup) {
        this.runSetup = runSetup;
    }

    public void init(IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 74) {
            this.changeNaNToZeroInExpressions = true;
        } else {
            this.changeNaNToZeroInExpressions = null;
        }
        if (intelligentObjectXml.FileVersion() < 81) {
            this.dontUnitConvertStateParameters = true;
        } else {
            this.dontUnitConvertStateParameters = null;
        }
        if (intelligentObjectXml.FileVersion() < 82) {
            this.ensureForeignNodesCastToNonNodeTypesAreAssociatedNodes = true;
        } else {
            this.ensureForeignNodesCastToNonNodeTypesAreAssociatedNodes = null;
        }
        this.maxExpressionStackSize = null;
        if (intelligentObjectXml.FileVersion() < 84) {
            this.alwaysAllowInfinityAsLinkSelectionWeightValue = true;
        } else {
            this.alwaysAllowInfinityAsLinkSelectionWeightValue = null;
        }
        if (intelligentObjectXml.FileVersion() < 94) {
            this.assumeInputNodeEntriesAreToAssociatedObjectIfNoDrawnOutboundLinks = false;
        } else {
            this.assumeInputNodeEntriesAreToAssociatedObjectIfNoDrawnOutboundLinks = null;
        }
        if (intelligentObjectXml.FileVersion() < 95) {
            this.ignoreShortCircuitEvaluationInExpressions = true;
        } else {
            this.ignoreShortCircuitEvaluationInExpressions = null;
        }
        if (intelligentObjectXml.FileVersion() < 96) {
            this.ignorePrimaryForeignKeyRelationshipsWhenEvaluatingTablePropertyRandomRowFunction = true;
            return;
        }
        this.ignorePrimaryForeignKeyRelationshipsWhenEvaluatingTablePropertyRandomRowFunction = null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "CompatibilitySettings", (XmlReader attr) ->
        {
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "ChangeNaNToZeroInExpressions",
                    b -> this.changeNaNToZeroInExpressions = b);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "DontUnitConvertStateParameters", b ->
                    this.dontUnitConvertStateParameters = b);
            SomeXmlOperator.readXmlIntAttribute(xmlReader, "MaxExpressionStackSize", i ->
                    this.maxExpressionStackSize = i);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader,
                    "EnsureForeignNodesCastToNonNodeTypesAreAssociatedNodes", b ->
                            this.ensureForeignNodesCastToNonNodeTypesAreAssociatedNodes = b);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "AlwaysAllowInfinityAsLinkSelectionWeightValue", b ->
                    this.alwaysAllowInfinityAsLinkSelectionWeightValue = b);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader,
                    "AssumeInputNodeEntriesAreToAssociatedObjectIfNoDrawnOutboundLinks", b ->
                            this.assumeInputNodeEntriesAreToAssociatedObjectIfNoDrawnOutboundLinks = b);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "IgnoreShortCircuitEvaluationInExpressions", b ->
                    this.ignoreShortCircuitEvaluationInExpressions = b);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader,
                    "IgnorePrimaryForeignKeyRelationshipsWhenEvaluatingTablePropertyRandomRowFunction", b ->
                            this.ignorePrimaryForeignKeyRelationshipsWhenEvaluatingTablePropertyRandomRowFunction = b);
        }, null);
    }
}
