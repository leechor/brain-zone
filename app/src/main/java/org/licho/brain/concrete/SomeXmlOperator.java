package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.fake.TimeSpan;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.enu.UnitType;
import org.licho.brain.resource.Image;
import org.licho.brain.simuApi.NodeShapeType;
import org.licho.brain.utils.simu.system.Color;
import org.licho.brain.utils.simu.system.DateTime;
import org.licho.brain.utils.simu.system.IDisposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;
import java.util.Base64;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntConsumer;
import java.util.function.Predicate;


/**
 *
 */
public class SomeXmlOperator {

    private static Logger logger = LoggerFactory.getLogger(SomeXmlOperator.class);

    public static boolean xmlReaderOperator(XmlReader xmlReader, String nodeName, Consumer<XmlReader> processXmlDeletate
            , Predicate<XmlReader> param3) {
        return true;
    }

    public static void readXml(XmlReader xmlReader, String attributeName, IntConsumer assignAction) {

    }

    public static void readXml2(XmlReader param0, String param1, Action<String> assignAction) {

    }

    public static boolean xmlReaderElement(XmlReader xmlReader,
                                           String nodeName,
                                           AttributeXmlDelegate attributeXmlDelegate,
                                           ElementStartDelegate elementStartDelegate,
                                           ElementEndDelegate elementEndDelegate,
                                           BodyXmlDelegate bodyXmlDelegate,
                                           AfterElementEndDelegate afterElementEndDelegate) {
        try {
            if (Objects.equals(xmlReader.Name(), nodeName) && xmlReader.NodeType() == XMLStreamConstants.START_ELEMENT) {
                if (attributeXmlDelegate != null) {
                    attributeXmlDelegate.apply(xmlReader);
                }

                xmlReader.nextTag();
                IDisposable disposable = null;
                if (elementStartDelegate != null) {
                    disposable = elementStartDelegate.apply(xmlReader);
                }

                try (var tmp = disposable) {
                    if (Objects.equals(xmlReader.Name(), nodeName) && xmlReader.NodeType() == XMLStreamConstants.END_ELEMENT) {
                        if (elementEndDelegate != null) {
                            elementEndDelegate.apply(xmlReader);
                        }
                    } else {
                        while (xmlReader.NodeType() != XMLStreamConstants.END_DOCUMENT &&
                                (!Objects.equals(xmlReader.Name(), nodeName) || xmlReader.NodeType() != XMLStreamConstants.END_ELEMENT)) {
                            boolean sameElement = false;
                            if (bodyXmlDelegate != null) {
                                sameElement = bodyXmlDelegate.apply(xmlReader);
                            }

                            if (!sameElement) {
                                xmlReader.nextTag();
                            }
                        }
                    }
                    // endElement
                    xmlReader.nextTag();
                }
                if (afterElementEndDelegate != null) {
                    afterElementEndDelegate.apply(xmlReader);
                }
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error(e.toString());
        }
        return false;
    }

    public static boolean xmlReaderElementAll(XmlReader xmlReader, String nodeName,
                                              SomeXmlOperator.AttributeXmlDelegate attributeXmlDelegate,
                                              SomeXmlOperator.ElementStartDelegate elementStartDelegate,
                                              SomeXmlOperator.BodyXmlDelegate bodyXmlDelegate,
                                              SomeXmlOperator.AfterElementEndDelegate afterElementEndDelegate) {
        return SomeXmlOperator.xmlReaderElement(xmlReader, nodeName, attributeXmlDelegate, elementStartDelegate, null
                , bodyXmlDelegate, afterElementEndDelegate);
    }

    public static void writeXmlBooleanAttribute(XmlWriter xmlWriter, String name, boolean value) {
        xmlWriter.WriteAttributeString(name, value ? "True" : "False");
    }

    public static void readXmlBooleanAttribute(XmlReader xmlReader, String name, Action<Boolean> assignAction) {
        String attribute = xmlReader.GetAttribute(name);
        if ("True".equalsIgnoreCase(attribute)) {
            assignAction.apply(true);
            return;
        }
        if ("False".equalsIgnoreCase(attribute)) {
            assignAction.apply(false);
        }
    }

    public static boolean xmlReaderElementOperator(XmlReader xmlReader, String nodeName,
                                                   AttributeXmlDelegate attributeXmlDelegate,
                                                   BodyXmlDelegate bodyXmlDelegate) {
        return SomeXmlOperator.xmlReaderElementAll(xmlReader, nodeName, attributeXmlDelegate, null, bodyXmlDelegate,
                null);
    }

    public static boolean readXmlIcon(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                      Action<Image> assignAction) {
        return false;
    }

    public static boolean readXMLText(XmlReader xmlReader, Action<String> assignAction) {
        if (xmlReader.NodeType() == XMLStreamConstants.CHARACTERS) {
            String text = xmlReader.getText();
            if (text != null) {
                assignAction.apply(text);
            }
            return true;
        }
        return false;
    }

    public static void readXmlAttributeString(XmlReader xmlReader, String name, Action<String> assignAction) {
        String attribute = xmlReader.GetAttribute(name);
        if (!Strings.isNullOrEmpty(attribute)) {
            assignAction.apply(attribute);
        }
    }

    public static void readAttributesFromXml(XmlReader xmlReader, String name, Action<Guid> assignAction) {
        String attribute = xmlReader.GetAttribute(name);
        if (!Strings.isNullOrEmpty(attribute)) {
            Guid obj = Guid.Empty;
            try {
                obj = new Guid(attribute);
            } catch (Exception ignored) {
                return;
            }
            assignAction.apply(obj);
        }
    }

    public static void readXmlIntAttribute(XmlReader xmlReader, String attributeName, Action<Integer> assignAction) {
        String attribute = xmlReader.GetAttribute(attributeName);
        if (!Strings.isNullOrEmpty(attribute)) {
            int obj = Integer.parseInt(attribute);
            assignAction.apply(obj);
        }
    }

    public static boolean readXmlBinaryData(XmlReader xmlReader, Action<byte[]> assignAction) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader,
                "BinaryData",
                null,
                body -> SomeXmlOperator.readXMLText(xmlReader, s -> {
                    byte[] data = Base64.getDecoder().decode(s);
                    assignAction.apply(data);
                }));
    }

    public static void readXmlDoubleAttribute(XmlReader xmlReader, String name, Action<Double> assignAction) {
        String attribute = xmlReader.GetAttribute(name);
        if (!Strings.isNullOrEmpty(attribute)) {
            double value = Double.parseDouble(attribute);
            assignAction.apply(value);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Enum<T>> void readEnumAttribute(XmlReader xmlReader, String name, Action<T> assignAction
            , Class<T> t) {
        String attribute = xmlReader.GetAttribute(name);
        if (!Strings.isNullOrEmpty(attribute)) {
            try {
                T.valueOf(t, attribute);
                return;
            } catch (NullPointerException e) {
                logger.info(e.toString());
            }

            if (t == UnitType.class) {
                if (attribute.equalsIgnoreCase("Mass")) {
                    assignAction.apply((T) UnitType.Weight);
                    return;
                }
                if (attribute.equalsIgnoreCase("MassFlowRate")) {
                    assignAction.apply((T) UnitType.WeightFlowRate);
                }
            } else if (t == NumericDataPropertyDefinition.NumericPropertyUnitType.class) {
                if (attribute.equalsIgnoreCase("Mass")) {
                    assignAction.apply((T) NumericDataPropertyDefinition.NumericPropertyUnitType.VolumeFlowRate);
                    return;
                }
                if (attribute.equalsIgnoreCase("MassFlowRate")) {
                    assignAction.apply((T) NumericDataPropertyDefinition.NumericPropertyUnitType.Weight);
                }
            }
        } else if (t == NodeShapeType.class) {
            if (attribute.equalsIgnoreCase("Value")) {
                assignAction.apply((T) NodeShapeType.FlowRegulator);
            }
        }
    }

    public static void readAttributeColorOperator(XmlReader xmlReader, String highRiskColor,
                                                  Action<Color> assignAction) {

    }

    public static void readDateTimeAttribute(XmlReader xmlReader, String name, Action<DateTime> assignAction) {
        String attribute = xmlReader.GetAttribute(name);
        if (!Strings.isNullOrEmpty(attribute)) {
            DateTime minValue = DateTime.TryParse(attribute);
            if (minValue != null) {
                assignAction.apply(minValue);
            }
        }
    }

    public static void readXmlAttributeTimeSpan(XmlReader xmlReader, String name, Action<TimeSpan> assignAction) {
        String attribute = xmlReader.GetAttribute(name);
        if (!Strings.isNullOrEmpty(attribute)) {
            TimeSpan zero = TimeSpan.parse(attribute);
            assignAction.apply(zero);
        }
    }

    public static void readXmlLocationAttribute(XmlReader xmlReader, String name, Action<Location> assignAction) {
        String attribute = xmlReader.GetAttribute(name);
        if (!Strings.isNullOrEmpty(attribute)) {
            String[] array = attribute.split(" ");
            if (array.length == 3) {
                Location location = Location.DefaultLocation;
                location.x = Double.parseDouble(array[0]);
                location.y = Double.parseDouble(array[1]);
                location.z = Double.parseDouble(array[2]);
                assignAction.apply(location);
            }
        }
    }

    public interface AttributeXmlDeletate {
        void AttributeXmlDeletate(XmlReader reader);
    }

    public interface BodyXmlDelegate {
        boolean apply(XmlReader reader);
    }

    public interface ElementStartDelegate {
        IDisposable apply(XmlReader reader);
    }

    public interface ElementEndDelegate {
        void apply(XmlReader reader);
    }

    public interface AttributeXmlDelegate {
        void apply(XmlReader reader);
    }

    public interface AfterElementEndDelegate {
        void apply(XmlReader reader);

    }
}
