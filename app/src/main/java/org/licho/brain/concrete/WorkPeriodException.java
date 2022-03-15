package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.utils.simu.system.DateTime;
import org.licho.brain.utils.simu.system.IDataErrorInfo;
import org.licho.brain.simuApi.IWorkPeriodException;

import java.util.Objects;

/**
 *
 */
public class WorkPeriodException implements INotifyPropertyChanged, IGridObject, IOwner, IDataErrorInfo,
        IWorkPeriodException {
    private IntelligentObjectDefinition parent;
    private IExpression valueExpression;
    private String value;
    private IExpression costMultiplierExpression;
    private String costMultiplier;

    public WorkPeriodException(IntelligentObjectDefinition intelligentObjectDefinition, IGridObject gridObject,
                               boolean b) {

    }

    public static WorkPeriodException readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                              IntelligentObjectDefinition intelligentObjectDefinition,
                                              IGridObject gridObject) {
        if (Objects.equals(xmlReader.Name(), "Exception")) {
            WorkPeriodException workPeriodException = new WorkPeriodException(intelligentObjectDefinition, gridObject
                    , true);
            workPeriodException.readXml(xmlReader, intelligentObjectXml);
            return workPeriodException;
        }
        return null;
    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {

        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Exception", (XmlReader attr) ->
        {
            SomeXmlOperator.readDateTimeAttribute(xmlReader, "Start", this::Start);
            SomeXmlOperator.readDateTimeAttribute(xmlReader, "End", this::End);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Value", this::LocalValue);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "CostMultiplier", this::CostMultiplier);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
        }, null);

    }

    public String CostMultiplier() {
        if (this.costMultiplierExpression != null) {
            return this.costMultiplierExpression.toString();
        }
        return this.costMultiplier;
    }

    public void CostMultiplier(String value) {

    }

    public String LocalValue() {
        if (this.valueExpression != null) {
            return this.valueExpression.toString();
        }
        return this.value;
    }

    public void LocalValue(String value) {

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
    public String getErrorByIndex(String propertyName) {
        return null;
    }

    @Override
    public String getError() {
        return null;
    }

    @Override
    public DateTime Start() {
        return null;
    }

    @Override
    public void Start(DateTime value) {

    }

    @Override
    public DateTime End() {
        return null;
    }

    @Override
    public void End(DateTime value) {

    }

    @Override
    public double Value() {
        return 0;
    }

    @Override
    public void Value(double value) {

    }

    @Override
    public String Description() {
        return null;
    }

    @Override
    public void Description(String value) {

    }

    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return this.parent == parent;
    }

    public Iterable<? extends IListener> createExpressionActionListener() {
        // TODO: 2021/12/20
        return null;
    }
}
