package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.fake.XmlWriter;

/**
 *
 */
public interface IWriteReadXml_1 {
    	void WriteToXml(XmlWriter xmlWriter, CommonItems commonItems);

	boolean ReadFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Object param2);
}
