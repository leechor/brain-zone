package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class UnitFilter {
    private final CurrencyFilter currencyFilter;
    private final IntelligentObjectDefinition assigner;

    public UnitFilter(IntelligentObjectDefinition assigner) {
        this.currencyFilter = new CurrencyFilter(this);
        this.assigner = assigner;
    }

    public CurrencyFilter CurrencyFilter() {
        return this.currencyFilter;

    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "UnitFilter", null,
                (XmlReader body) -> this.currencyFilter.readXml(body, intelligentObjectXml));
    }
}
