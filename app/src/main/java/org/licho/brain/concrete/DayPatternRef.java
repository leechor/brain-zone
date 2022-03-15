package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.annotations.Browsable;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.enu.Enum53;
import org.licho.brain.simuApi.containers.SpecialEditor;

import java.util.Objects;

/**
 *
 */
public class DayPatternRef implements INotifyPropertyChanged {

    private static String none = "(None)";

    private DayPattern dayPattern;
    private IntelligentObjectDefinition parent;
    private String dayPatternName;

    public DayPatternRef(IntelligentObjectDefinition intelligentObjectDefinition, DayPattern dayPattern) {
        this.parent = intelligentObjectDefinition;
        this.dayPattern = dayPattern;
    }

    public static DayPatternRef readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                        IntelligentObjectDefinition intelligentObjectDefinition, int[] num) {
        if (Objects.equals(xmlReader.Name(), "DayPatternRef")) {
            DayPatternRef dayPatternRef = new DayPatternRef(intelligentObjectDefinition, null);
            num[0] = dayPatternRef.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition);
            if (num[0] != -1) {
                return dayPatternRef;
            }
        }
        return null;
    }

    private int readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                        IntelligentObjectDefinition intelligentObjectDefinition) {
        int[] index = new int[]{-1};
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "DayPatternRef", (XmlReader attr) ->
        {
            SomeXmlOperator.readXmlIntAttribute(xmlReader, "DayNumber", (Integer int_0) ->
                    index[0] = int_0 - 1);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "DayPattern", this::DayPatternName);
        }, null);
        return index[0];
    }

    @SpecialEditor(Enum53.Zero)
    public String DayPatternName() {
        if (this.dayPattern == null) {
            return this.dayPatternName;
        }
        return this.dayPattern.Name();
    }

    public void DayPatternName(String value) {
        if (Objects.equals(value, DayPatternRef.none)) {
            this.dayPatternName = null;
            this.dayPattern = null;
        } else {
            this.dayPatternName = value;
            this.dayPattern =
                    this.parent.getWorkSchedulesUtils().DayPatterns().values.stream()
                            .filter(dayPattern -> dayPattern.Name().equalsIgnoreCase(this.dayPatternName))
                            .findFirst()
                            .orElse(null);
            this.triggerPropertyChange("DayPatternName");
        }
    }

    private void triggerPropertyChange(String dayPatternName) {

    }

    public IntelligentObjectDefinition Parent() {
        return this.parent;
    }

    public String getNotFoundString() {
        if (!Strings.isNullOrEmpty(this.DayPatternName()) && this.DayPattern() == null) {
            return EngineResources.ErrorTheSpecifiedNameWasNotFound;
        }
        return null;
    }

    @Browsable(false)
    public DayPattern DayPattern() {
        return this.dayPattern;
    }

    public void DayPattern(DayPattern value) {
        this.dayPattern = value;
    }
}
