package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.annotations.Browsable;
import org.licho.brain.annotations.StateFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.UnitType;
import org.licho.brain.resource.Image;
import org.licho.brain.simuApi.IStateDefinition;
import org.licho.brain.utils.simu.IReferencedObjects;
import org.licho.brain.utils.simu.system.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;
import java.util.function.Function;


/**
 *
 */
public class BaseStatePropertyObject implements INotifyPropertyChanged, IGridObject, IAutoComplete, IOwner, ISearch,
        IItemDescriptor, IStateDefinition, IListener, IGridItemPropertyObject {
    private final Logger logger = LoggerFactory.getLogger(BaseStatePropertyObject.class);

    private static StatePropertyTypeOperator[] statePropertyTypeOperator;
    public String name;
    private String isDisplayFormat;
    private final Object description;
    public Double value;
    public int index;
    protected NumericDataType numericDataType;
    private UnitType unitType;

    private StringPropertyDefinition stringPropertyDefinition;
    public StateDefinitions StateDefinitions;
    private int currencyIndex;

    protected boolean IsPrivate;
    protected boolean isReadOnly;
    private String displayName = "";

    private String categoryName = "";
    private String unitTypePropertyName;
    private DateTime dateTime;
    private int stateDimension;
    private int[] states;
    private Table boundTable;
    private String boundTableName;


    public BaseStatePropertyObject(String name, boolean isReadOnly, boolean IsPrivate) {
        this.name = name;
        this.isReadOnly = isReadOnly;
        this.IsPrivate = IsPrivate;
        this.numericDataType = NumericDataType.Real;
        this.isDisplayFormat = "";
        this.value = 0.0;
        this.description = "";
    }

    public BaseStatePropertyObject(String name, boolean isReadOnly, boolean IsPrivate,
                                   NumericDataType numericDataType) {
        this(name, isReadOnly, IsPrivate);
        this.numericDataType = numericDataType;
    }

    public static BaseStatePropertyObject readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                                  AbsDefinition absDefinition) {
        BaseStatePropertyObject baseStatePropertyObject = null;
        String name = xmlReader.Name();
        for (BaseStatePropertyObject.StatePropertyTypeOperator statePropertyTypeOperator :
                BaseStatePropertyObject.statePropertyTypeOperator) {
            if (Objects.equals(statePropertyTypeOperator.name, name)) {
                baseStatePropertyObject = statePropertyTypeOperator.statePropertyTypeOperator.apply("__TEMP_STATE__");
                break;
            }
        }
        if (baseStatePropertyObject != null) {
            absDefinition.getStateDefinitions().addStateProperty(baseStatePropertyObject);
            baseStatePropertyObject.readXml(xmlReader, intelligentObjectXml);
        }
        if (baseStatePropertyObject == null) {
            intelligentObjectXml.addWarning(String.format(EngineResources.LoadWarning_CouldNotLoadState, name));
        }
        return baseStatePropertyObject;

    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, xmlReader.Name(), (XmlReader attr) ->
        {
            String name = attr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(name) && !this.Name().equals(name)) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.getAbsDefinition() != null) {
                    this.Name(this.getAbsDefinition().GetUniqueName(name, false));
                } else {
                    this.Name(name);
                }
            }
            SomeXmlOperator.readXmlAttributeString(attr, "DisplayName", (String t) ->
            {
                this.displayName = t;
            });
            SomeXmlOperator.readXmlAttributeString(attr, "CategoryName", this::CategoryName);
            SomeXmlOperator.readXmlAttributeString(attr, "Description", this::Description);
            SomeXmlOperator.readXmlAttributeString(attr, "UnitTypePropertyName", this::UnitTypePropertyName);
            SomeXmlOperator.readXmlDoubleAttribute(attr, "InitialValue", this::Value);
            SomeXmlOperator.readDateTimeAttribute(attr, "InitialValue", this::setDateTime);
            if (this.SupportsDisplayFormat()) {
                SomeXmlOperator.readXmlAttributeString(attr, "DisplayFormat", this::DisplayFormat);
            }
            String readOnly = attr.GetAttribute("ReadOnly");
            if (readOnly.equalsIgnoreCase("True")) {
                this.ReadOnly(true);
            }
            String privateValue = attr.GetAttribute("Private");
            if (privateValue.equalsIgnoreCase("True")) {
                this.Private(true);
            }
            if (this.SupportsDimensions()) {
                String dimensions = attr.GetAttribute("Dimensions");
                if (!Strings.isNullOrEmpty(dimensions)) {
                    String[] dimensionsArray = dimensions.split(",");
                    if (dimensionsArray.length > 0) {
                        this.changeStateDimensions(dimensionsArray.length);
                        for (int i = 0; i < dimensionsArray.length; i++) {
                            int param2 = 0;
                            try {
                                param2 = Integer.parseInt(dimensionsArray[i]);
                                this.setValue(i, param2);
                            } catch (NumberFormatException e) {
                                logger.error("Interpger parse error");
                            }
                        }
                    }
                }
                String boundTable = attr.GetAttribute("BoundTable");
                if (!Strings.isNullOrEmpty(boundTable)) {
                    this.updateBoundTable(boundTable);
                }
            }
            if (this.SupportsUnitType()) {
                SomeXmlOperator.readEnumAttribute(attr, "UnitType", (UnitType unitType_0) ->
                {
                    this.unitType = unitType_0;
                }, UnitType.class);
                String initialValueUnits = attr.GetAttribute("InitialValueUnits");
                if (!Strings.isNullOrEmpty(initialValueUnits)) {
                    this.setUnitIndex(initialValueUnits);
                }
            }
            this.ReadXmlAttributes(attr);
        }, this::ReadXmlBody);

    }

    protected boolean ReadXmlBody(XmlReader xmlReader) {
        return false;
    }

    protected void ReadXmlAttributes(XmlReader attr) {

    }

    public void setUnitIndex(String initialValueUnits) {
        this.setUnitIndex(AboutUnit.getUnitLevel(this.UnitType(), initialValueUnits));
    }

    public void setUnitIndex(int index) {
        if (this.isNumericDataType()) {
            this.currencyIndex = index;
        }
    }

    protected boolean isNumericDataType() {
        return this.numericDataType == NumericDataType.Real;
    }


    public void updateBoundTable(String boundTable) {
        this.boundTable = null;
        this.boundTableName = boundTable;

        IntelligentObjectDefinition intelligentObjectDefinition = null;

        if (this.getAbsDefinition() instanceof IntelligentObjectDefinition) {
            intelligentObjectDefinition = (IntelligentObjectDefinition) this.getAbsDefinition();
        } else {
            if (this.getAbsDefinition() instanceof TableStatesDefinition) {
                intelligentObjectDefinition =
                        ((TableStatesDefinition) this.getAbsDefinition()).getSchema().Parent().Parent();
            }
        }

        if (intelligentObjectDefinition != null) {
            this.boundTable = intelligentObjectDefinition.Tables().getTableByName(this.boundTableName);
        }
        if (this.boundTable != null) {
            Schema schema = this.boundTable.Schema();

            if (!schema.predicateProperty(this::isBoundable)) {
                this.boundTable = null;
            }
        }
        if (this.boundTable != null) {
            this.changeStateDimensions(2);
            if (intelligentObjectDefinition != null) {
                intelligentObjectDefinition.PropertyChange(this, EngineResources.StateTableName);
            }
        } else if (this.boundTableName != null && intelligentObjectDefinition != null) {
            intelligentObjectDefinition.PropertyChangeError(this, EngineResources.StateTableName);
        }
    }

    boolean isBoundable(StringPropertyDefinition stringPropertyDefinition) {
        return stringPropertyDefinition instanceof NumericDataPropertyDefinition;
    }


    public void setValue(int index, int value) {
        if (this.BoundTable() == null) {
            if (this.states == null || index > ((long) this.states.length)) {
                throw new IllegalArgumentException("Invalid dimension given");
            }
            this.states[index] = Math.min(value, 2);
            this.propertyChanged(BaseStatePropertyObject.getDimensionPropertyName(index));
        }

    }

    private static String getDimensionPropertyName(int index) {
        String result = String.format(EngineResources.DimensionSize, index + 1);
        switch (index) {
            case 0:
                result = EngineResources.Rows;
                break;
            case 1:
                result = EngineResources.Columns;
                break;
        }
        return result;
    }

    private Table BoundTable() {
        return this.boundTable;
    }

    private void changeStateDimensions(int stateDimension) {
        int dimensions = Math.min(stateDimension, 10);
        this.flashStates(dimensions);
        this.stateDimension = dimensions;
        this.propertyChanged("StateDimensions");
        if (this.getAbsDefinition() != null) {
            this.getAbsDefinition().NotifyStateDefinitionChangedDimensions(this);
        }

    }

    private void flashStates(int count) {
        int[] array = null;
        if (count > 0) {
            array = new int[count];
            int i = 0;
            while (i < count) {
                array[i] = 2;
                i++;
            }
        }
        if (this.states != null && array != null && this.states.length != array.length) {
            int i = 0;
            while (i < this.states.length && i < array.length) {
                array[i] = this.states[i];
                i++;
            }
        }
        this.states = array;

    }

    public Boolean Private() {
        return this.IsPrivate;
    }

    public void Private(boolean value) {
        this.IsPrivate = value;
        this.propertyChanged("Private");
    }

    public Boolean ReadOnly() {
        return this.isReadOnly;
    }

    public void ReadOnly(boolean value) {
        this.isReadOnly = value;
        this.propertyChanged("ReadOnly");
    }

    public String DisplayFormat() {
        return this.isDisplayFormat;
    }

    public void DisplayFormat(String value) {
        this.isDisplayFormat = value;
        this.propertyChanged("DisplayFormat");
    }

    boolean SupportsDisplayFormat() {
        return this.numericDataType == NumericDataType.Real;
    }

    public void setDateTime(DateTime value) {
        this.dateTime = value;
    }

    public String UnitTypePropertyName() {
        if (this.stringPropertyDefinition != null) {
            return this.stringPropertyDefinition.Name();
        }
        return this.unitTypePropertyName;
    }

    public void UnitTypePropertyName(String value) {
        this.unitTypePropertyName = value;
        this.stringPropertyDefinition = null;
        if (this.StateDefinitions != null && this.StateDefinitions.parent != null && this.StateDefinitions.parent.getPropertyDefinitions() != null) {
            this.UnitTypePropertyDefinition(
                    this.StateDefinitions.parent.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(this.unitTypePropertyName));
        }
    }

    public StringPropertyDefinition UnitTypePropertyDefinition() {
        return this.stringPropertyDefinition;
    }

    public void UnitTypePropertyDefinition(StringPropertyDefinition value) {
        this.stringPropertyDefinition = value;
        if (this.getAbsDefinition() instanceof IntelligentObjectDefinition) {
            IntelligentObjectDefinition intelligentObjectDefinition =
                    (IntelligentObjectDefinition) this.getAbsDefinition();
            if (this.getUnitTypePropertyNameError() == null) {
                intelligentObjectDefinition.PropertyChange(this, EngineResources.UnitTypePropertyName);
                return;
            }
            intelligentObjectDefinition.PropertyChangeError(this, EngineResources.UnitTypePropertyName);
        }
    }

    public String getUnitTypePropertyNameError() {
        if (this.haveUnitTypePropertyName() && this.UnitTypePropertyDefinition() == null) {
            return String.format(EngineResources.Error_CouldNotFindProperty, this.UnitTypePropertyName());
        }
        return null;
    }

    boolean haveUnitTypePropertyName() {
        return !Strings.isNullOrEmpty(this.UnitTypePropertyName());
    }

    @Browsable(false)
    public String CategoryName() {
        return this.categoryName;
    }

    public void CategoryName(String value) {
        this.categoryName = value;
    }

    protected boolean SupportsDimensions() {
        return true;
    }

    protected boolean SupportsUnitType() {
        return this.numericDataType != NumericDataType.Boolean && this.numericDataType != NumericDataType.DateTime;
    }


    public UnitType UnitType() {
        if (this.numericDataType == NumericDataType.DateTime) {
            return UnitType.Time;
        }
        return this.unitType;
    }

    public void UnitType(UnitType unitType) {
        this.unitType = unitType;
//        this.currencyIndex = this.
        this.propertyChanged("UnitType");
        if (this.StateDefinitions != null) {
            if (this.StateDefinitions.getParent() instanceof TableStateDefinition) {
                TableStateDefinition tableStateDefinition =
                        (TableStateDefinition) this.StateDefinitions.getParent();
                if (tableStateDefinition.getSchema() != null && tableStateDefinition.getSchema().getParent() != null) {
                    tableStateDefinition.getSchema().getParent().method_23(this);
                }
            }
        }
    }

    public UnitType getPropertiesUnitType(Properties properties) {
        return null;
    }


    public Double Value() {
        return value;
    }

    public void Value(Double value) {
        this.value = value;
        this.propertyChanged("Value");
    }

    double getUnitValue() {
        if (this.unitType != UnitType.Unspecified) {
            UnitConversions unitConversions = null;
            if (this.StateDefinitions.getParent() instanceof IntelligentObjectDefinition) {
                IntelligentObjectDefinition intelligentObjectDefinition =
                        (IntelligentObjectDefinition) this.StateDefinitions.getParent();
                if (intelligentObjectDefinition.ActiveModel() != null) {
                    unitConversions =
                            intelligentObjectDefinition.ActiveModel().getRunSetup().getUnitConversions();
                }
                return AboutUnit.smethod_4(this.unitType, this.getCurrencyIndex(), unitConversions, this.Value());
            }
        }
        return this.Value();
    }


    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList gridItemProperties) {
        return new BaseStateIGridItemPropertyObject(this, gridItemProperties);
    }

    protected void propertyChanged(String propertyName) {
        // TODO: 2021/11/11 
    }

    protected boolean ParameterIsReadOnly(int param0) {
        return false;
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        return null;
    }

    @Override
    public void AlternateGetGridItemProperties(PropertyDefinitions definitions, GridItemProperties gridItemProperties) {

    }

    @Override
    public void UpdateGridPropertyValue(int param0, Object param1) {

    }

    @Override
    public String[] GetListValues() {
        return new String[0];
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
    public Object Item() {
        return null;
    }

    @Override
    public String Name() {
        return this.name;
    }

    public void Name(String value) {
        String name = this.name;
        this.name = value;
        this.propertyChanged("Name");
        if (this.getAbsDefinition() != null) {
            this.getAbsDefinition().NotifyStateDefinitionRenamed(this, name);
        }

    }

    private AbsDefinition getAbsDefinition() {
        if (this.StateDefinitions != null) {
            return this.StateDefinitions.parent;
        }
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String Description() {
        return null;
    }

    @Override
    public void Description(String description) {

    }

    @Override
    public String getDisplayName() {
        return null;
    }

    @Override
    public String Group() {
        return null;
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return null;
    }

    @Override
    public void setDisplayName(String displayName) {

    }

    @Override
    public boolean isAutoResetWhenStatisticsCleared() {
        return false;
    }

    @Override
    public void setAutoResetWhenStatisticsCleared(boolean autoReset) {

    }

    @Override
    public String ObjectType() {
        return null;
    }

    @Override
    public String Category() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public int StateIconIndex() {
        return 0;
    }

    @Override
    public Image Icon() {
        return null;
    }

    @Override
    public void Rename(String newName) {

    }

    @Override
    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        return false;
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }


    public int getCurrencyIndex() {
        return currencyIndex;
    }

    public void setCurrencyIndex(int currencyIndex) {
        this.currencyIndex = currencyIndex;
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

    public boolean IsOwnedBy(GridObjectDefinition gridObjectDefinition) {
        return this.StateDefinitions != null && this.StateDefinitions.parent == gridObjectDefinition && (!(gridObjectDefinition
                instanceof TokenDefinition) || this.StateDefinitions.StateProperties.values.get(0) != this);

    }

    public int getStateDimensions() {
        return this.stateDimension;
    }

    public String getBoundTableName() {
        if (this.boundTable != null) {
            return this.boundTable.Name();
        }
        return this.boundTableName;
    }

    protected int LastPropertyNumber() {
        return -9;
    }

    protected UnitType GetUnitTypeForParameter(int unitType) {
        if (unitType == -1) {
            return this.UnitType();
        }
        return UnitType.Unspecified;
    }


    public class StatePropertyTypeOperator {
        public String name;
        public Class<?> type;

        private Function<String, BaseStatePropertyObject> statePropertyTypeOperator;
    }

    public Class<?> TableType() {
        if (this.numericDataType == NumericDataType.DateTime) {
            return DateTime.class;
        }
        if (this.numericDataType == NumericDataType.Boolean) {
            return Boolean.class;
        }
        if (this.numericDataType == NumericDataType.Integer) {
            return Integer.class;
        }
        return Double.class;
    }

    protected Boolean WriteOutValueInXml() {
        return true;
    }

    public Parameter[] Parameters() {
        return null;
    }

    @StateFunction(NameOverride = "InitialValue", Description = "Returns the initial value of the state.")
    public static ExpressionValue getInitialValue(AbsBaseTrace absBaseTrace) {
        return absBaseTrace.InitialValue();
    }

}
