package org.licho.brain.concrete;


import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum53;
import org.licho.brain.utils.simu.system.DateTime;
import org.licho.brain.utils.simu.system.IDataErrorInfo;
import org.licho.brain.api.IDayPattern;
import org.licho.brain.api.IWorkDayException;
import org.licho.brain.api.containers.SpecialEditor;

import java.util.Objects;

/**
 *
 */
public class WorkDayException implements INotifyPropertyChanged, IGridObject, IOwner, IDataErrorInfo,
        IWorkDayException {

    private DayPatternRef dayPatternRef;
    private DateTime date;
    private String description;

    public WorkDayException(IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject) {

    }

    public static WorkDayException readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                           IntelligentObjectDefinition intelligentObjectDefinition,
                                           IGridObject gridObject) {
        if (Objects.equals(xmlReader.Name(), "DayException")) {
            WorkDayException workDayException = new WorkDayException(intelligentObjectDefinition, gridObject);
            workDayException.readXml(xmlReader, intelligentObjectXml);
            return workDayException;
        }
        return null;
    }

    void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {

        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "DayException", (XmlReader attr) ->
        {
            SomeXmlOperator.readDateTimeAttribute(xmlReader, "Date", (DateTime d) ->
                    this.Date(d.Date()));
            SomeXmlOperator.readXmlAttributeString(xmlReader, "DayPattern", this::DayPatternName);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
        }, null);
    }

    @Override
    public String Description() {
        return this.description;
    }

    @Override
    public void Description(String value) {
        this.description = value;
        this.triggerPropertyChange("Description", false);
    }

    @SpecialEditor(Enum53.Zero)
    public String DayPatternName() {
        return this.dayPatternRef.DayPatternName();
    }

    void DayPatternName(String value) {
        this.dayPatternRef.DayPatternName(value);
        this.triggerPropertyChange("DayPatternName", true);
    }

    @Override
    @SpecialEditor(Enum53.One)
    public DateTime Date() {
        return this.date;
    }

    @Override
    public void Date(DateTime value) {
        this.date = value.Date();
        this.triggerPropertyChange("Date", false);
    }

    @Override
    public IDayPattern DayPattern() {
        return null;
    }

    @Override
    public void DayPattern(IDayPattern dayPattern) {

    }

    private void triggerPropertyChange(String date, boolean b) {

    }

    @Override
    public String getObjectClassName() {
        return null;
    }

    @Override
    public String getObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        return new String[0];
    }

    @Override
    public String getErrorByIndex(String propertyName) {
        return null;
    }

    @Override
    public String getError() {
        return null;
    }


    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return parent == this.dayPatternRef.Parent();
    }
}
