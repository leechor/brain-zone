package org.licho.brain.concrete;

import org.licho.brain.enu.IconIndex;
import org.licho.brain.brainApi.enu.ListType;

/**
 *
 */
public class NodeList extends AbsListProperty {
    public NodeList(NodeListDefinition definition, String name) {
        super(definition, name);
    }

    @Override
    protected String ListTypeDescription() {
        return "Nodes";
    }

    @Override
    public ListType ListType() {
        return ListType.Node;
    }

    @Override
    public String ItemTypeName() {
        return "Node List";
    }

    @Override
    protected IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.EIGHT;
    }

    @Override
    public String XmlTypeName() {
        return "NodeList";
    }

}
