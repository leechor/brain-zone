package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.IconIndex;
import com.zdpx.cctpp.simuApi.enu.ListType;

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
