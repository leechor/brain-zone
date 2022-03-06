package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.InOutputStream;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.fake.XmlWriter;

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
