package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

/**
 *
 */
public class PathDecorator implements IObjectOperator<Decorator>{
    @Override
    public void Dispose() {

    }

    public boolean readXml(XmlReader xmlReader, Facade facade) {
		return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "PathDecorators", null,
                (XmlReader body) -> Decorator.readXmlPathDecoratorRef(xmlReader, facade, this::method_2)
		);    }

    public void method_2(Decorator decorator) {
        // TODO: 2022/1/28
    }
}
