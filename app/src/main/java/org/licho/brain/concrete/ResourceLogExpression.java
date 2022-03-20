package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.UnitType;
import org.licho.brain.event.EventHandler;
import org.licho.brain.event.IEvent;
import org.licho.brain.utils.simu.IReferencedObjects;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;
import org.licho.brain.brainEnums.ExpressionDataFormat;

import java.util.Objects;

/**
 *
 */
public class ResourceLogExpression implements IListener, IOwner, IGridObject, INotifyPropertyChanged {
    private UnitType unitType;
    private IntelligentObjectDefinition parent;
    private String name;
    private String description;
    private ExpressionDataFormat dataFormat;
    private int unitLevel;
    private ExpressionEvaluationType evaluationType;
    private ExpressionGanttDisplayType ganttDisplayType;
    private String expression;
    private String string_4;
    private IExpression expressionFormula;
    private EventHandler<PropertyChangedEventArgs> propertyChangedEventHandler;


    public static ResourceLogExpression readXml(XmlReader xmlReader,
                                                IntelligentObjectDefinition intelligentObjectDefinition) {
        if (Objects.equals(xmlReader.Name(), "ResourceLogExpression")) {
            ResourceLogExpression resourceLogExpression = intelligentObjectDefinition.addResourceLogExpression();
            resourceLogExpression.readXml(xmlReader);
            return resourceLogExpression;
        }
        return null;
    }

        public void addPropertyChangedEventHandler(IEvent<PropertyChangedEventArgs> value) {
        EventHandler.subscribe(this.propertyChangedEventHandler, value);
    }

    public void removePropertyChangedEventHandler(IEvent<PropertyChangedEventArgs> value) {
        EventHandler.unSubscribe(this.propertyChangedEventHandler, value);
    }

    private void readXml(XmlReader xmlReader) {

        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ResourceLogExpression", (XmlReader attr) ->
        {
            String name = attr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(name)) {
                this.Name(name);
            }
            String description = attr.GetAttribute("Description");
            if (!Strings.isNullOrEmpty(description)) {
                this.Description(description);
            }
            SomeXmlOperator.readEnumAttribute(xmlReader, "DataFormat",
                    this::DataFormat, ExpressionDataFormat.class);
            SomeXmlOperator.readEnumAttribute(xmlReader, "UnitType", this::UnitType, UnitType.class);
            SomeXmlOperator.readEnumAttribute(xmlReader,
                    "EvaluationType", this::EvaluationType, ResourceLogExpression.ExpressionEvaluationType.class);
            SomeXmlOperator.readEnumAttribute(xmlReader,
                    "GanttDisplayType",
                    this::GanttDisplayType, ResourceLogExpression.ExpressionGanttDisplayType.class);
        }, t -> SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Expression",
                a -> {
                    String attribute = a.GetAttribute("Units");
                    if (!Strings.isNullOrEmpty(attribute)) {
                        this.setUnitLevelByName(attribute);
                    }
                },
                b -> SomeXmlOperator.readXMLText(b, this::Expression)));

    }

    private void Expression(String value) {
        this.expression = value;
        this.string_4 = null;
        StringBuilder tmp = new StringBuilder();
        this.expressionFormula = ExpressionCalculate.createExpression(this.expression, this.parent, true, true, true,
                tmp);
        this.string_4 = tmp.toString();
        if (this.expression == null) {
            this.parent.PropertyChangeError(this, EngineResources.ResourceUsageLog_PropertyDisplayName_Expression);
        } else {
            this.parent.PropertyChange(this, EngineResources.ResourceUsageLog_PropertyDisplayName_Expression);
        }
        this.propertyDisplayNameChange(EngineResources.ResourceUsageLog_PropertyDisplayName_Expression);
    }

    private void setUnitLevelByName(String attribute) {
        this.setUnitLevel(AboutUnit.getUnitLevel(this.unitType, attribute));
    }

    private void setUnitLevel(int unitLevel) {
        this.unitLevel = unitLevel;
    }

    public ResourceLogExpression.ExpressionGanttDisplayType GanttDisplayType() {
        return this.ganttDisplayType;
    }

    public void GanttDisplayType(ExpressionGanttDisplayType value) {
        this.ganttDisplayType = value;
        this.propertyDisplayNameChange("GanttDisplayType");
    }

    public ResourceLogExpression.ExpressionEvaluationType EvaluationType() {
        return this.evaluationType;
    }

    public void EvaluationType(ExpressionEvaluationType value) {
        this.evaluationType = value;
    }

    public UnitType UnitType() {
        return this.unitType;
    }

    public void UnitType(UnitType value) {
        this.unitType = value;
        this.unitLevel = 0;
        this.propertyDisplayNameChange(EngineResources.UnitTypeProp);
    }

    public ExpressionDataFormat DataFormat() {
        return this.dataFormat;
    }

    private void DataFormat(ExpressionDataFormat value) {
        this.dataFormat = value;
        this.propertyDisplayNameChange(EngineResources.ResourceUsageLog_PropertyDisplayName_DataFormat);
    }

    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
    }

    public String Name() {
        return this.name;
    }

    public void Name(String value) {
        this.name = value;
        this.propertyDisplayNameChange(EngineResources.DisplayNameProp);
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
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
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
    public void RefreshIfInError() {

    }

    @Override
    public void UpdateForParentObjectPropertyChange(StringPropertyDefinition definitionInfo, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectStateChange(BaseStatePropertyObject baseStatePropertyObject, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectEventChange(EventDefinition eventDefinition, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectMemberElementChange(AbsIntelligentPropertyObject absIntelligentPropertyObject,
                                                         Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectListChange(AbsListProperty absListProperty, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectListTupleChange(AbsListProperty absListProperty, Properties properties,
                                                     Enum38 param2) {

    }

    @Override
    public void UpdateForParentObjectTableChange(Table table, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectTableColumnChange(Table table, StringPropertyDefinition stringPropertyDefinition
            , Enum38 param2) {

    }

    @Override
    public void UpdateForParentObjectTableKeyChange(Table table, Properties properties,
                                                    IntelligentObjectProperty intelligentObjectProperty,
                                                    Enum38 param3) {

    }

    @Override
    public void UpdateForParentObjectWorkScheduleChange(WorkSchedule workSchedule, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectDayPatternChange(DayPattern dayPattern, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectChangeoverMatrixChange(ChangeoverMatrix changeoverMatrix, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectLookupTableChange(UserFunction userFunction, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectRateTableChange(RateTable rateTable, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectTokenDefinitionChange(TokenDefinition token, Enum38 param1) {

    }


    @Override
    public void UpdateForParentObjectTransferPointChange(NodeClassProperty classProperty, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectExpressionFunctionChange(ExpressionFunction expressionFunction, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectInputParameterChange(AbsInputParameter param0, Enum38 param1) {

    }

    @Override
    public void UpdateForParentObjectLibraryDefinitionChange(IntelligentObjectDefinition intelligentObjectDefinition,
                                                             Enum38 enu) {

    }

    @Override
    public void CollectReferencedObjects(IReferencedObjects param0) {

    }

    public void propertyDisplayNameChange() {
        if (this.unitType != UnitType.Unspecified) {
            this.propertyDisplayNameChange(EngineResources.DisplayNameProp);
        }
    }

    private void propertyDisplayNameChange(String DisplayNameProp) {
		 EventHandler.fire(this.propertyChangedEventHandler,this, new PropertyChangedEventArgs(DisplayNameProp));
		}

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return this.parent == parent;
    }

    public enum ExpressionEvaluationType {
        StartTimeValue,
        EndTimeValue,
        ValueChange
    }

    public enum ExpressionGanttDisplayType {
        None,
        ResourceGantt,
        EntityGantt
    }
}
