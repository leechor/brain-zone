package com.zdpx.cctpp.concrete.fake;

import com.zdpx.cctpp.concrete.XmlReaderSettings;
import com.zdpx.cctpp.utils.simu.system.IDisposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Field;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

import static javax.xml.stream.XMLStreamConstants.COMMENT;
import static javax.xml.stream.XMLStreamConstants.END_DOCUMENT;
import static javax.xml.stream.XMLStreamConstants.PROCESSING_INSTRUCTION;
import static javax.xml.stream.XMLStreamConstants.START_DOCUMENT;

/**
 *
 */
public abstract class XmlReader implements IDisposable {

    private static Logger logger = LoggerFactory.getLogger(XmlReader.class);

    public static XmlReader Create(InputStreamReader reader, XmlReaderSettings pure) {
        return new SubXmlReader(reader);
    }

    public abstract String Name();

    public abstract String GetAttribute(String name);

    public abstract void MoveToContent();

    public abstract int nextTag();

    public abstract int NodeType();

    public abstract boolean IsEmptyElement();

    public abstract String getText();

    public abstract String ReadOuterXml();

    public static XmlReader Create(Reader reader, XmlReaderSettings pure) {
        return new SubXmlReader(reader);
    }

    public void Skip() {
    }

    public static class SubXmlReader extends XmlReader {


        XMLInputFactory xmlInputFactory;
        XMLStreamReader reader;
        Field fEntityScanner;

        public SubXmlReader(InputStreamReader reader) {
            xmlInputFactory = XMLInputFactory.newDefaultFactory();
            try {
                this.reader = xmlInputFactory.createXMLStreamReader(reader);
            } catch (XMLStreamException e) {
                logger.error(e.toString());
            }
        }

        public SubXmlReader(Reader reader) {
            xmlInputFactory = XMLInputFactory.newDefaultFactory();
            try {
                this.reader = xmlInputFactory.createXMLStreamReader(reader);
            } catch (XMLStreamException e) {
                logger.error(e.toString());
            }
        }

        public String Name() {
            try {
                if (this.reader.isStartElement() || this.reader.isEndElement()) {
                    return this.reader.getLocalName();
                }
            } catch (IllegalStateException e) {
                logger.error("xml getName error.");
            }
            return "";
        }

        public String GetAttribute(String name) {
            return this.reader.getAttributeValue(null, name);
        }

        @Override
        public void MoveToContent() {
            boolean isNode = false;
            while (!isNode) {
                switch (this.reader.getEventType()) {
                    case PROCESSING_INSTRUCTION:
                    case START_DOCUMENT:
                    case END_DOCUMENT:
                    case COMMENT: {
                        this.nextTag();
                        break;
                    }
                    default:
                        isNode = true;
                }
            }
        }

        @Override
        public int nextTag() {
            try {
                var result = this.reader.nextTag();
                return result;
            } catch (XMLStreamException ignored) {
                return -1;
            } catch (NoSuchElementException e) {
                logger.debug("xml nextTag error. {}", e.toString());
                return 0;
            }
        }

        @Override
        public int NodeType() {
            return this.reader.getEventType();
        }

        @Override
        public boolean IsEmptyElement() {
            if (this.reader.getEventType() == XMLStreamConstants.START_ELEMENT) {
                return !this.reader.hasText();
            }
            return true;
        }

        @Override
        public String getText() {
            if (this.reader.hasText()) {
                var result = this.reader.getText();
                this.nextTag();
                return result;
            }
            return "";
        }

        @Override
        public String ReadOuterXml() {
            StringBuilder sb = new StringBuilder();
            if (this.reader.isStartElement()) {
                String elementName = this.reader.getLocalName();
                sb.append(getStartElementText());

                this.nextTag();
                int c = 0;
                do {
                    if (this.reader.isStartElement()) {
                        c++;
                        sb.append(spaces(c) + getStartElementText());
                    } else if (this.reader.isEndElement()) {
                        sb.append(spaces(c) + "</" + this.reader.getLocalName() + ">\n");
                        c--;
                    } else if (this.reader.hasText()) {
                        sb.append(spaces(c) + this.reader.getText() + "\n");
                    }
                    this.nextTag();
                } while (!(this.reader.isEndElement() && this.reader.getLocalName().equals(elementName) && c == 0));
            }
            sb.append("</" + this.reader.getLocalName() + ">\n");
            this.nextTag();
            return sb.toString();
        }

        private String spaces(int c) {
            StringBuilder space = new StringBuilder();
            Stream.generate(() -> " ").limit(c).forEach(System.out::println);
            for (int i = 0; i < c; i++) {
                space.append("  ");
            }
            return space.toString();
        }

        private String getStartElementText() {
            StringBuilder sb = new StringBuilder();
            sb.append("<").append(this.reader.getLocalName());
            for (int i = 0; i < this.reader.getAttributeCount(); i++) {
                sb.append(" " + this.reader.getAttributeName(i) + "=\"" + this.reader.getAttributeValue(i) + "\"");
            }
            sb.append(">\n");
            return sb.toString();
        }

        @Override
        public void close() {
            super.close();
        }

        @Override
        public void Dispose() {

        }
    }

    public enum XmlNodeType {
        None,
        Element,
        EndElement,
        ProcessingInstruction,
        Document,
        Comment,
        Whitespace,
        Attribute,
        Text,
        CDATA,
        EntityReference,
        Entity,
        DocumentType,
        DocumentFragment,
        Notation,
        SignificantWhitespace,
        EndEntity,
        XmlDeclaration
    }
}
