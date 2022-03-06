package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.fake.XmlWriter;

/**
 *
 */
public class BaseViewInfo implements IViewInfo, IWriteReadXml_1 {
    @Override
    public Iterable<Object> SelectedObjects() {
        return null;
    }

    @Override
    public GridObjectDefinition SelectionContext() {
        return null;
    }

    @Override
    public String Description() {
        return null;
    }

    @Override
    public boolean OwnsObject(Object param0) {
        return false;
    }

    @Override
    public void SelectObject(Object param0, String param1) {

    }

    @Override
    public void WriteToXml(XmlWriter xmlWriter, CommonItems commonItems) {

    }

    @Override
    public boolean ReadFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Object param2) {
        return false;
    }
}
