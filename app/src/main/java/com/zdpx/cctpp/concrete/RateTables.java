package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.simuApi.IRateTable;
import com.zdpx.cctpp.simuApi.IRateTables;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 */
public class RateTables extends BindingList<RateTable> implements IRateTables {
    private final IntelligentObjectDefinition intelligentObjectFacility;

    public RateTables(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectFacility = intelligentObjectDefinition;
    }

    @Override
    public IRateTable getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IRateTable getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IRateTable> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "RateTables", null, (XmlReader body) ->
                RateTable.readXmlRateTable(xmlReader, intelligentObjectXml, this.intelligentObjectFacility) != null);

    }

    public RateTable getRateTableByName(String value) {
        for (RateTable rateTable : this.values) {
            if (Objects.equals(rateTable.Name(), value)) {
                return rateTable;
            }
        }
        return null;
    }
}
