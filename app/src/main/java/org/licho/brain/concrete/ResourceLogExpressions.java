package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class ResourceLogExpressions extends BindingList<ResourceLogExpression> {
    private final IntelligentObjectDefinition intelligentObjectFacility;

    public ResourceLogExpressions(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectFacility = intelligentObjectDefinition;
    }

    public boolean readXml(XmlReader xmlReader) {
        String[] expressionName = new String[1];
        return SomeXmlOperator.xmlReaderElementAll(xmlReader, "ResourceLogExpressions", (XmlReader attr) ->
                        SomeXmlOperator.readXmlAttributeString(attr, "OwnerGanttGroupingExpression",
								(String string_0) -> expressionName[0] = string_0), null,
				(XmlReader body) -> ResourceLogExpression.readXml(xmlReader,
                        this.intelligentObjectFacility) != null
                , (XmlReader afterBody) ->
						this.intelligentObjectFacility.ProcessResourceLogExpressionAction(
								this.intelligentObjectFacility.getResourceLogExpressions().values.stream()
										.filter((ResourceLogExpression resourceLogExpression) ->
												StringHelper.equalsLocal(resourceLogExpression.Name(), expressionName[0]))
										.findFirst()
										.orElse(null)));
    }

}
