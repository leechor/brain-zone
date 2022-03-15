package org.licho.brain.utils.simu;

import org.licho.brain.concrete.InOutputStream;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;

/**
 *
 */
public interface IFiles {
    IFileRef imethod_0(String param0);

    InOutputStream OpenReadStream(String name);

    Iterable<String> imethod_1();

    boolean readXmlFiles(XmlReader param0);

    void WriteToXml(XmlWriter param0);
}
