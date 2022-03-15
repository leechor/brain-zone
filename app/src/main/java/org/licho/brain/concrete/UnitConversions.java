package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class UnitConversions {
    public CurrencyConversions getCurrencyConversions() {
        return currencyConversions;
    }

    public void setCurrencyConversions(CurrencyConversions currencyConversions) {
        this.currencyConversions = currencyConversions;
    }

    private CurrencyConversions currencyConversions = new CurrencyConversions();

    public CurrencyConversions CurrencyConversions() {
        return this.currencyConversions;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
		return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "UnitConversions", null,
                (XmlReader body) -> this.currencyConversions != null && this.currencyConversions.method_4(body, intelligentObjectXml));
    }
}
