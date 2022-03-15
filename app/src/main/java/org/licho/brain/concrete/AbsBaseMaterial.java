package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.IPropertyChangedCreateAction;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public abstract class AbsBaseMaterial implements IDisposable, IGridObject, IItemDescriptor,
        IPropertyChangedCreateAction {
    protected Guid guid = Guid.Empty;

    public AbsBaseMaterial() {
        this.guid = Guid.NewGuid();
    }

    public static boolean readXmlMaterialRef(XmlReader xmlReader, Facade facade,
                                             Action<AbsBaseMaterial> assignAction) {
        AbsBaseMaterial[] absBaseMaterial = new AbsBaseMaterial[1];
        boolean result = SomeXmlOperator.xmlReaderElementOperator(xmlReader, "MaterialRef", (XmlReader attr) -> {
            String attribute = attr.GetAttribute("Id");
            Guid guid = new Guid(attribute);
            absBaseMaterial[0] = facade.getAbsBaseMaterialByGuid(guid);
        }, null);
        if (absBaseMaterial[0] != null) {
            assignAction.apply(absBaseMaterial[0]);
        }
        return result;
    }
}
