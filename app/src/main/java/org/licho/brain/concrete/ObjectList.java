package org.licho.brain.concrete;

import org.licho.brain.enu.IconIndex;
import org.licho.brain.simuApi.enu.ListType;

/**
 *
 */
public class ObjectList extends AbsListProperty {
    public ObjectList(ObjectListDefinition definition, String name) {
        super(definition, name);
    }

    @Override
    protected String ListTypeDescription() {
        return "Objects";
    }

    @Override
    public ListType ListType() {
        return ListType.Object;
    }

    @Override
    public String ItemTypeName() {
        return "Object List";
    }

    @Override
    protected IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.SEVEN;
    }

    @Override
    public String XmlTypeName() {
        return "ObjectList";
    }

}
