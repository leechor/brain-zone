package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.IconIndex;
import com.zdpx.cctpp.simuApi.enu.ListType;

/**
 *
 */
public class TransporterList extends AbsListProperty {
    public TransporterList(GridObjectDefinition definition, String name) {
        super(definition, name);
    }

    @Override
    protected String ListTypeDescription() {
        return "Transporters";
    }

    @Override
    public ListType ListType() {
        return ListType.Transporter;
    }

    @Override
    public String ItemTypeName() {
        return "Transporter List";
    }

    @Override
    protected IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.Ten;
    }

    @Override
    public String XmlTypeName() {
        return "TransporterList";
    }
}
