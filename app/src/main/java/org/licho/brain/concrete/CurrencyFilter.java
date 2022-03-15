package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class CurrencyFilter {
    private int unitTypeIndex;
    private String description;
    private String defaultCurrency;

    public CurrencyFilter(UnitFilter unitFilter) {
    }

    public int getUnitTypeIndex() {
        return this.unitTypeIndex;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "CurrencyFilter", (XmlReader attr) ->
                SomeXmlOperator.readXmlAttributeString(xmlReader, "DefaultCurrency", this::DefaultCurrency), null);
    }

    public String DefaultCurrency() {
        return this.defaultCurrency;
    }

    public void DefaultCurrency(String value) {
        if (!Strings.isNullOrEmpty(value) && value.length() == 3) {
            // TODO: 2021/12/28
        }
    }
}
