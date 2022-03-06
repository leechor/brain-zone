package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.annotations.Browsable;
import com.zdpx.cctpp.concrete.fake.TimeSpan;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.Enum38;
import com.zdpx.cctpp.simuApi.IWorkPeriod;
import com.zdpx.cctpp.utils.simu.system.DateTime;
import com.zdpx.cctpp.utils.simu.system.IDataErrorInfo;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class WorkPeriod implements INotifyPropertyChanged, IGridObject, IOwner, IDataErrorInfo, IWorkPeriod {
    private IExpression valueExpression;
    private String value;
    private TimeSpan startTimeSpan;
    private TimeSpan endTimeSpan;
    private IExpression costMultiplierExpression;
    private String costMultiplier;
    private String description;
    private WorkPeriods workPeriods;

    public WorkPeriod() {

    }

    public WorkPeriod(IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject, boolean b) {

    }

    WorkPeriod(IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject, TimeSpan timeSpan,
               TimeSpan plus) {
        this(intelligentObjectDefinition, gridObject, false);
        this.startTimeSpan = timeSpan;
        this.endTimeSpan = plus;
    }

    public static WorkPeriod readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                     IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject) {
        if (Objects.equals(xmlReader.Name(), "Period") || Objects.equals(xmlReader.Name(), "CycleItem")) {
            WorkPeriod workPeriod = new WorkPeriod(intelligentObjectDefinition, gridObject, true);
            workPeriod.readXml(xmlReader, intelligentObjectXml);
            return workPeriod;
        }
        return null;
    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {

        if (intelligentObjectXml.FileVersion() < 41) {
            this.Value("0.0");
        }
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, xmlReader.Name(), (XmlReader attr) -> {
            SomeXmlOperator.readXmlAttributeTimeSpan(xmlReader, "Start", (TimeSpan timeSpan_0) -> {
                if (timeSpan_0.Days() >= 0) {
                    this.startTimeSpan = timeSpan_0;
                }
            });
            SomeXmlOperator.readXmlAttributeTimeSpan(xmlReader, "End", (TimeSpan timeSpan_0) ->
                    this.endTimeSpan = timeSpan_0);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Value", this::Value);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "CostMultiplier", this::CostMultiplier);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
        }, null);

    }

    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
        this.triggerPropertyChange("Description", false);
    }

    private void triggerPropertyChange(String description, boolean b) {
    }

    public String CostMultiplier() {
        if (this.costMultiplierExpression != null) {
            return this.costMultiplierExpression.toString();
        }
        return this.costMultiplier;
    }

    public void CostMultiplier(String value) {

    }

    public String Value() {
        if (this.valueExpression != null) {
            return this.valueExpression.toString();
        }
        return this.value;
    }

    public void Value(String s) {
    }

    @Override
    public String GetGridObjectClassName() {
        return null;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties param0, GridObjectDefinition param1) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
        return new String[0];
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return false;
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
    public Duration getStart() {
        return null;
    }

    @Override
    public void setStart(Duration start) {

    }

    @Override
    public Duration getEnd() {
        return null;
    }

    @Override
    public void setEnd(Duration end) {

    }

    @Override
    public double getValue() {
        return 0;
    }

    @Override
    public void setValue(double value) {

    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public void setDescription(String description) {

    }

    public Iterable<? extends IListener> createExpressionActionListener() {
        var e1 = ExpressionAction.createExpressionActionListener(this.valueExpression,
                (Enum38 enum38) -> {
                    if (enum38 == Enum38.Zero || enum38 == Enum38.Two) {
                        this.Value(this.Value());
                        return;
                    }
                    if (enum38 == Enum38.One) {
                        this.value = this.Value();
                        this.triggerPropertyChange("Value", false);
                    }
                }, () -> this.Value(this.Value()));

        var e2 = ExpressionAction.createExpressionActionListener(this.costMultiplierExpression,
                (Enum38 enum38) -> {
                    if (enum38 == Enum38.Zero || enum38 == Enum38.Two) {
                        this.CostMultiplier(this.CostMultiplier());
                        return;
                    }
                    if (enum38 == Enum38.One) {
                        this.costMultiplier = this.CostMultiplier();
                        this.triggerPropertyChange("Value", false);
                    }
                }, () -> this.CostMultiplier(this.CostMultiplier()));

        return List.of(e1, e2);
    }

    public void EndTime(DateTime dateTime) {

    }

    public void StartTime(DateTime dateTime) {

    }

    @Browsable(false)
    public TimeSpan EndTimeSpan() {
        return this.endTimeSpan;
    }

    public void EndTimeSpan(TimeSpan value) {
        if (value == TimeSpan.FromDays(1.0)) {
            this.endTimeSpan = value;
        } else if (value == TimeSpan.Zero) {
            this.endTimeSpan = TimeSpan.FromDays(1.0);
        } else {
            this.endTimeSpan = new TimeSpan(value.Hours(), value.Minutes(), value.Seconds());
        }
        this.triggerPropertyChange("StartTime", true);
        this.triggerPropertyChange("EndTime", true);
        this.triggerPropertyChange("Duration", true);
    }

    public void setWorkPeriods(WorkPeriods workPeriods) {
        this.workPeriods = workPeriods;
    }
}
