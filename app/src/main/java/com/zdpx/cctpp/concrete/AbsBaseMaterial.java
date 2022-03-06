package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.IPropertyChangedCreateAction;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.utils.simu.system.IDisposable;

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
        boolean result = SomeXmlOperator.xmlReaderElementOperator(xmlReader, "MaterialRef", (XmlReader attr) ->
        {
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
