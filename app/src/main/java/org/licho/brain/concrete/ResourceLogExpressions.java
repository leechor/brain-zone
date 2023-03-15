package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class ResourceLogExpressions extends BindingList<ResourceLogExpression> {
    private final IntelligentObjectDefinition assigner;

    public ResourceLogExpressions(IntelligentObjectDefinition assigner) {
        this.assigner = assigner;
    }

    public boolean readXml(XmlReader xmlReader) {
        String[] expressionName = new String[1];
        return SomeXmlOperator.xmlReaderElementAll(xmlReader, "ResourceLogExpressions", (XmlReader attr) ->
                        SomeXmlOperator.readXmlAttributeString(attr, "OwnerGanttGroupingExpression",
								(String string_0) -> expressionName[0] = string_0), null,
				(XmlReader body) -> ResourceLogExpression.readXml(xmlReader,
                        this.assigner) != null
                , (XmlReader afterBody) ->
						this.assigner.ProcessResourceLogExpressionAction(
								this.assigner.getResourceLogExpressions().values.stream()
										.filter((ResourceLogExpression resourceLogExpression) ->
												StringHelper.equalsLocal(resourceLogExpression.Name(), expressionName[0]))
										.findFirst()
										.orElse(null)));
    }

}
