package com.zdpx.cctpp.concrete;


import com.zdpx.cctpp.concrete.fake.XmlReader;

/**
 *
 */
public class SymbolDecorator implements IObjectOperator<Symbol>{
    @Override
    public void Dispose() {

    }

    public boolean readXml(XmlReader xmlReader, Facade facade) {
		return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Symbols", null,
                (XmlReader body) -> Symbol.readXml(xmlReader, facade, null, (Symbol t) ->
                        this.method_2(t, Enum140.Zero)));    }

    public Symbol method_2(Symbol symbol, Enum140 zero) {
        // TODO: 2022/1/28
        return null;
    }

    public enum Enum140 {
        Zero
    }
}
