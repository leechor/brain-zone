package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

/**
 *
 */
public class MaterialsWrapper implements IObjectOperator<AbsBaseMaterial> {
    private final String name;

    public MaterialsWrapper(String name) {
        this.name = name;
    }


    @Override
    public void Dispose() {

    }

    public boolean readXml(XmlReader xmlReader, Facade facade) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, this.name, null,
                (XmlReader body) -> AbsBaseMaterial.readXmlMaterialRef(xmlReader, facade, this::method_0));
    }

    public boolean method_0(AbsBaseMaterial absBaseMaterial) {
        // TODO: 2022/1/28
        return false;
    }
}
