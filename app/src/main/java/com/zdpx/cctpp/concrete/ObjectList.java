package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.IconIndex;
import com.zdpx.cctpp.simuApi.enu.ListType;

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
