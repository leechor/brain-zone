package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.utils.simu.IFileRef;
import org.licho.brain.utils.simu.IFiles;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class Files implements IFiles {

    private boolean bool_0;
    private Map<String, byte[]> fileContextMap = new HashMap<>();

    public Files(boolean param0) {
        this.bool_0 = param0;
    }

    // Token: 0x0600586B RID: 22635 RVA: 0x00046317 File Offset: 0x00044517
    public Files() {
        this(true);
    }

    @Override
    public IFileRef imethod_0(String param0) {
        return null;
    }

    @Override
    public InOutputStream OpenReadStream(String name) {
        return null;
    }

    @Override
    public Iterable<String> imethod_1() {
        return null;
    }

    @Override
    public boolean readXmlFiles(XmlReader xmlReader) {
        String[] fileName = new String[1];
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader,
                "Files",
                null,
                body -> SomeXmlOperator.xmlReaderOperator(body,
                        "File",
                        file -> {
                            fileName[0] = xmlReader.GetAttribute("Name");
                        },
                        fileBody -> SomeXmlOperator.readXmlBinaryData(xmlReader, (byte[] bs) -> {
                            if (bs != null) {
                                this.fileContextMap.put(fileName[0], bs);
                            }
                        })
                ));
    }

    @Override
    public void WriteToXml(XmlWriter param0) {

    }
}
