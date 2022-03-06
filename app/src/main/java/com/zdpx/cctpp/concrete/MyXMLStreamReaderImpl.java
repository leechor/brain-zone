package com.zdpx.cctpp.concrete;
 import com.sun.org.apache.xerces.internal.impl.PropertyManager;
 import com.sun.org.apache.xerces.internal.impl.XMLStreamReaderImpl;

 import javax.xml.stream.XMLStreamException;
 import java.io.InputStream;

/**
 *
 */
public class MyXMLStreamReaderImpl extends XMLStreamReaderImpl{

    public MyXMLStreamReaderImpl(InputStream inputStream, PropertyManager props) throws XMLStreamException {
        super(inputStream, props);
//        this.fEntityScanner.registerListener();
    }


}
