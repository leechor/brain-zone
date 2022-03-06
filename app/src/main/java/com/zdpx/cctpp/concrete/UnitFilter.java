package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

/**
 *
 */
public class UnitFilter {
    private final CurrencyFilter currencyFilter;
    private final IntelligentObjectDefinition intelligentObjectDefinition;

    public UnitFilter(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.currencyFilter = new CurrencyFilter(this);
        this.intelligentObjectDefinition = intelligentObjectDefinition;
    }

    public CurrencyFilter CurrencyFilter() {
        return this.currencyFilter;

    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "UnitFilter", null,
                (XmlReader body) -> this.currencyFilter.readXml(body, intelligentObjectXml));
    }
}
