package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;

/**
 *
 */
public interface IWriteReadXml_1 {
    	void WriteToXml(XmlWriter xmlWriter, CommonItems commonItems);

	boolean ReadFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Object param2);
}
