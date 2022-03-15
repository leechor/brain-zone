package org.licho.brain.concrete.property;

import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.AbsDefinition;
import org.licho.brain.concrete.AbsInputParameter;
import org.licho.brain.concrete.AbsIntelligentPropertyObject;
import org.licho.brain.concrete.AbsListProperty;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.BaseStatePropertyObject;
import org.licho.brain.concrete.ChangeoverMatrix;
import org.licho.brain.concrete.CommonItems;
import org.licho.brain.concrete.DayPattern;
import org.licho.brain.concrete.EventDefinition;
import org.licho.brain.concrete.ExpressionAction;
import org.licho.brain.concrete.ExpressionFunction;
import org.licho.brain.concrete.GridItemProperties;
import org.licho.brain.concrete.GridItemProperty;
import org.licho.brain.concrete.GridObjectDefinition;
import org.licho.brain.concrete.IExpression;
import org.licho.brain.concrete.IGridItemPropertyObject;
import org.licho.brain.concrete.IItemDescriptor;
import org.licho.brain.concrete.IListener;
import org.licho.brain.concrete.INotifyPropertyChanged;
import org.licho.brain.concrete.IReport;
import org.licho.brain.concrete.IRunSpace;
import org.licho.brain.concrete.IntelligentObject;
import org.licho.brain.concrete.IntelligentObjectDefinition;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.NodeClassProperty;
import org.licho.brain.concrete.NumericDataPropertyRow;
import org.licho.brain.concrete.ObjectValuePotsOperator;
import org.licho.brain.concrete.Properties;
import org.licho.brain.concrete.PropertyDefinitions;
import org.licho.brain.concrete.PropertyDescriptors;
import org.licho.brain.concrete.RateTable;
import org.licho.brain.concrete.ReferenceValue;
import org.licho.brain.concrete.RepeatingPropertyDefinition;
import org.licho.brain.concrete.RuntimeErrorFullMessageDetails;
import org.licho.brain.concrete.SomeXmlOperator;
import org.licho.brain.concrete.StringPropertyDefinition;
import org.licho.brain.concrete.Table;
import org.licho.brain.concrete.TokenDefinition;
import org.licho.brain.concrete.UserFunction;
import org.licho.brain.concrete.WorkSchedule;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.cont.ErrorString;
import org.licho.brain.concrete.exception.ExpressionResultTypeException;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.node.Node;
import org.licho.brain.enu.ElementType;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.IEnumMask;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.resource.Image;
import org.licho.brain.brainEnums.SwitchNumericConditions;
import org.licho.brain.api.IExecutionContext;
import org.licho.brain.api.IProperty;
import org.licho.brain.api.IPropertyReader;
import org.licho.brain.api.IUnitBase;
import org.licho.brain.utils.ExtensionString;
import org.licho.brain.utils.simu.IReferencedObjects;
import org.licho.brain.utils.simu.system.PropertyChangedEventHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

/**
 *
 */
public class IntelligentObjectProperty implements INotifyPropertyChanged, IItemDescriptor, IProperty,
        IPropertyReader, IListener, IGridItemPropertyObject, IReport {

    protected String error;
    private String stringValue;
    private StringPropertyDefinition propertyDefinition;
    private Properties properties;
    private String objectValue;
    private boolean nullable;
    private AbsDefinition absDefinition;
    private Boolean invalidObjectName;
    private AbsPropertyObject absPropertyObject;
    private RepeatingPropertyDefinition repeatingProperty;
    private StringPropertyDefinition reference;
    private Table table;
    private TableWrapper tableWrapper;
    private RepeatingPropertyDefinition repeatingPropertyDefinition;
    private ElementType elementType;
    private static final Pattern regex = Pattern.compile("\\$\\((\\w+)\\)");

    private PropertyChangedEventHandler propertyChangedEventHandler;

    public IntelligentObjectProperty(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        this.propertyDefinition = propertyDefinitionInfo;
        this.error = null;
        this.properties = properties;
        if (this.CopyInDefaultValue()) {
            this.setObjectValue(this.getDefaultName(this.getStringPropertyDefinitionInfo().GetDefaultStringBy(properties.PropertyDefinitions)));
        }
        this.clear();
    }

    public static IntelligentObjectProperty readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                                    Properties properties) {
        if (Objects.equals(xmlReader.Name(), "Property")) {
            String attribute = xmlReader.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(attribute)) {
                IntelligentObjectProperty intelligentObjectProperty =
                        properties.getIntelligentObjectProperty(attribute, intelligentObjectXml);
                if (intelligentObjectProperty != null) {
                    intelligentObjectProperty.ReadFromXml(xmlReader, intelligentObjectXml);
                    return intelligentObjectProperty;
                }
            }
        }
        return null;
    }

    private void ReadFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {

        IntelligentObjectProperty.IItemXml bodyHandler = this.CreateXMLBodyHandler();
        SomeXmlOperator.xmlReaderElement(xmlReader, "Property", (XmlReader attr) ->
        {
            this.ReadAttributesFromXML(xmlReader);
        }, null, (XmlReader emptybody) ->
        {
            this.SetCultureInvariantStringValue(this.processValue(intelligentObjectXml, ""),
                    IntelligentObjectProperty.ValueVersion.userVersion(intelligentObjectXml.FileVersion()));
        }, (XmlReader body) ->
        {
            if (bodyHandler == null) {
                return SomeXmlOperator.readXMLText(body, (String String_0) ->
                        this.SetCultureInvariantStringValue(this.processValue(intelligentObjectXml, String_0),
                                ValueVersion.userVersion(intelligentObjectXml.FileVersion())));
            }
            return SomeXmlOperator.xmlReaderElement(body, "Value", null, null, (XmlReader emptyvalbody) ->
                            this.SetCultureInvariantStringValue(this.processValue(intelligentObjectXml, ""),
                                    ValueVersion.userVersion(intelligentObjectXml.FileVersion())),
                    (XmlReader valbody) -> SomeXmlOperator.readXMLText(valbody, (String string_0) ->
                            this.SetCultureInvariantStringValue(this.processValue(intelligentObjectXml, string_0),
                                    ValueVersion.userVersion(intelligentObjectXml.FileVersion()))), null) || bodyHandler.readXml(xmlReader, intelligentObjectXml);
        }, null);


    }

    public void SetCultureInvariantStringValue(String value, ValueVersion userVersion) {
        this.SetStringValue(value, null, userVersion);
    }

    private String processValue(IntelligentObjectXml intelligentObjectXml, String value) {
        value = this.getStringPropertyDefinitionInfo().GetPropertyValueFixup(intelligentObjectXml, value);
        if (this.getExperimentConstraintsIntelligentObjectDefinition() != null) {
            return this.getExperimentConstraintsIntelligentObjectDefinition().GetPropertyValueFixup(intelligentObjectXml, value);
        }
        return value;
    }

    private void ReadAttributesFromXML(XmlReader xmlReader) {
    }

    protected IItemXml CreateXMLBodyHandler() {
        return null;
    }

    public StringPropertyDefinition getReference() {
        return reference;
    }

    public void setReference(StringPropertyDefinition reference) {
        this.reference = reference;
    }

    public Object GetObjectValue() {
        return this.getObjectName();
    }

    public void UpdateForNewPropertyDefinition() {
    }

    public String StringValue() {
        if (this.IsAReference()) {
            switch (this.elementType) {
                case Object:
                    return this.getReference().Name();
                case ActiveToken:
                case AssociatedObject:
                    return this.absDefinition.Name() + "." + this.getReference().Name();
                case DataTable:
                    if (this.getTable() != null) {
                        return this.getTable().Name() + "." + this.getReference().Name();
                    }

                    if (this.tableWrapper.table != null) {
                        return this.tableWrapper.toString();
                    }
                    return this.getObjectName();
                case RepeatGroup:
                    if (this.getRepeatingPropertyDefinitionInfo() != null) {
                        return this.getObjectName();
                    }
                    return this.getRepeatingPropertyDefinitionInfo().Name() + "." + this.getReference().Name();
            }
        }
        return this.getObjectName();
    }

    protected boolean CopyInDefaultValue() {
        return true;
    }

    protected boolean CanChangeValueDueToDefaultChange() {
        return false;
    }

    protected boolean ShouldRecompileWhenGivenNewDefinition() {
        return false;
    }

    protected PropertyGridFeel DefaultFeelName() {
        return PropertyGridFeel.none;
    }

    @Override
    public String Group() {
        return null;
    }


    public StringPropertyDefinition getStringPropertyDefinitionInfo() {
        return this.propertyDefinition;
    }


    public void setStringValue(String value) {
        this.StringValue(value);
    }

    public void StringValue(String value) {
        this.SetStringValue(value, null, IntelligentObjectProperty.initValueVersion());
    }

    public Object SetStringValue(String value, Object o, IntelligentObjectProperty.ValueVersion version) {
        this.setObjectValue(value);
        this.getProperties().PropertyUpdating(this);
        boolean flag = this.processPropertyChange();
        return this.getProperties().PropertyUpdated(this, !flag, o, version);
    }

    public void PropertyUpdating(IntelligentObjectProperty intelligentObjectProperty) {
        this.absPropertyObject.PropertyUpdating(intelligentObjectProperty);
    }

    protected void setObjectValue(String value) {
        this.objectValue = value;
        this.invalidObjectName = null;
    }

    private static IntelligentObjectProperty.ValueVersion initValueVersion() {
        return new IntelligentObjectProperty.ValueVersion(VersionEnum.InitVersion, 0);
    }


    private RepeatingPropertyDefinition getRepeatingPropertyDefinitionInfo() {
        return this.repeatingPropertyDefinition;
    }

    public String getObjectName() {
        return this.objectValue;
    }

    protected Table getTable() {
        return this.table;
    }

    protected void clear() {
        this.absDefinition = null;
        this.elementType = ElementType.nullElement;
        this.reference = null;
        this.table = null;
        this.tableWrapper = TableWrapper.TableWrapper;
        this.repeatingProperty = null;
    }

    public String CompileValue() {
        this.clear();
        String name = this.formatName(this.getObjectName());
        this.method_31(name, StringPropertyDefinition.class, 255);
        return null;
    }

    public String DefinitionName() {
        return this.getStringPropertyDefinitionInfo().Name();
    }

    public Object ListData() {
        return null;
    }

    public PropertyDescriptors Tuples() {
        return null;
    }


    protected boolean IsSwitchedInCore() {
        return true;
    }

    protected boolean SupportsDynamicObjectPropertyReferences() {
        return true;
    }

    @Override
    public String Name() {
        return this.getStringPropertyDefinitionInfo().Name();
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

    public String TooltipText() {
        return String.format("{0}: <b>{1}</b>", this.DisplayName(), this.StringValue());
    }

    protected boolean WriteOutStringValueToXml() {
        return true;
    }

    @Override
    public String Value() {
        return this.StringValue();
    }


    @Override
    public IUnitBase Unit() {
        return null;
    }

    protected boolean HasDynamicReferencesCore() {
        return true;
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

    public boolean processPropertyChange() {
        return this.processPropertyChange(this.getError(), null, () -> {
            this.error = this.CompileValue();
            if (this.getPropertyObject() != null) {
                StringBuilder sb = new StringBuilder();
                this.getPropertyObject().PostCompileCheck(this, sb);
                this.error = sb.toString();
            }
            return this.getError();
        });
    }

    protected boolean processPropertyChange(String error, String name, GetError getError) {
        boolean haveError = error != null;
        if (getError.apply() == null) {
            if (haveError) {
                this.processPropertyChange(Enum19.One, name);
            }
            return true;
        }
        this.processPropertyChange(Enum19.Zero, name);
        return false;
    }

    private void processPropertyChange(IntelligentObjectProperty.Enum19 enum19, String name) {
        if (this.getPropertyObject() != null && !this.getPropertyObject().NotifyParentOfErrors()) {
            return;
        }
        IntelligentObjectDefinition intelligentObjectDefinition =
                this.getExperimentConstraintsIntelligentObjectDefinition();
        if (intelligentObjectDefinition != null) {
            switch (enum19) {
                case Zero:
                    if (name != null) {
                        intelligentObjectDefinition.PropertyChangeError(this.getProperties().AbsPropertyObject, name);
                        return;
                    }
                    intelligentObjectDefinition.NotifyIntelligentObjectPropertyErrorEvent(this);
                    return;
                case One:
                    if (name != null) {
                        intelligentObjectDefinition.PropertyChange(this.getProperties().AbsPropertyObject, name);
                        return;
                    }
                    intelligentObjectDefinition.NotifyIntelligentObjectPropertyChangeEvent(this);
                    break;
                default:
                    return;
            }
        }
    }


    public Object GetNativeObject() {
        return this.StringValue();
    }

    public ReferenceValue[] GetCandidatePropertyReferences() {
        // TODO: 2021/12/8
        return null;
    }

    protected IntelligentObjectDefinition getExperimentConstraintsIntelligentObjectDefinition() {
        if (this.getIntelligentObjectFacility() != null) {
            return this.getIntelligentObjectFacility();
        }
        if (this.getProperties() == null || this.getProperties().AbsPropertyObject == null) {
            return null;
        }
        GridObjectDefinition gridObjectDefinition = this.getProperties().AbsPropertyObject.objectDefinition;
        if (gridObjectDefinition instanceof ExperimentConstraintsDefinition) {
            ExperimentConstraintsDefinition experimentConstraintsDefinition =
                    (ExperimentConstraintsDefinition) gridObjectDefinition;
            return experimentConstraintsDefinition.getActiveModel().getIntelligentObjectDefinition();
        }
        return (IntelligentObjectDefinition) gridObjectDefinition;
    }

    protected boolean isInvalidObjectValue(String name) {
        if (name == null) {
            if (this.invalidObjectName == null) {
                this.invalidObjectName = this.invalidObjectValue(this.objectValue);
            }
            return this.invalidObjectName;
        }
        return this.invalidObjectValue(name);
    }

    private Boolean invalidObjectValue(String objectName) {
        String text = ExtensionString.removeSpace(objectName);
        return Strings.isNullOrEmpty(text) || text.equals(ExtensionString.removeSpace(this.getStringPropertyDefinitionInfo().NullNullString())) || text.equals("null");
    }

    public void expressionListerHandler(Action<IListener> action) {
        action.apply(this.createExpressionActionListener(this.tableWrapper.expression));
    }

    private IListener createExpressionActionListener(IExpression expression) {
        return ExpressionAction.createExpressionActionListener(expression, enum38 -> {
            if (enum38 == Enum38.Zero || enum38 == Enum38.Two) {
                return;
            }

            if (enum38 == Enum38.One) {
                this.setObjectNameMaybe(this.StringValue());
                this.PropertyUpdated();
            }
        }, null);
    }

    protected void PropertyUpdated() {
        if (this.getProperties() != null) {
            this.getProperties().PropertyUpdated(this, false, null,
                    IntelligentObjectProperty.ValueVersion.innerVersion());
        }
    }

    private void setObjectNameMaybe(String name) {
        this.objectValue = name;
        this.invalidObjectName = null;
    }

    public void NotifySiblingPropertyUpdated(IntelligentObjectProperty intelligentObjectProperty,
                                             ValueVersion valueVersion) {

    }

    public IntelligentObjectProperty.ExpressionResult getExpressionResult(IRunSpace runSpace,
                                                                          IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.getExpressionResult(runSpace, intelligentObjectRunSpace, null, null,
                intelligentObjectRunSpace != null && intelligentObjectRunSpace.getMayApplication().DisableRandomness(), -1);
    }

    public IntelligentObjectProperty.ExpressionResult getExpressionResult(IRunSpace runSpace,
                                                                          IntelligentObjectRunSpace intelligentObjectRunSpace, IntelligentObjectRunSpace dataSourceIntelligentObjectRunSpace) {
        return this.getExpressionResult(runSpace, intelligentObjectRunSpace, null,
                dataSourceIntelligentObjectRunSpace,
                intelligentObjectRunSpace != null && intelligentObjectRunSpace.getMayApplication().DisableRandomness(),
                -1);
    }

    public IntelligentObjectProperty.ExpressionResult getExpressionResult(IRunSpace runSpace,
                                                                          IntelligentObjectRunSpace intelligentObjectRunSpace, AbsBaseRunSpace absBaseRunSpace, IntelligentObjectRunSpace param3, boolean param4, int param5) {
        if (this.getReference() == null) {
            return new IntelligentObjectProperty.ExpressionResult(this.GetValueCore(runSpace,
                    intelligentObjectRunSpace, absBaseRunSpace, param3, param4), runSpace, intelligentObjectRunSpace,
                    this);
        }
        IntelligentObjectRunSpace objectRunSpace = null;
        boolean[] flag = new boolean[]{true};
        IntelligentObjectProperty intelligentObjectProperty = this.getIntelligentObjectProperty(runSpace,
                intelligentObjectRunSpace, objectRunSpace, param5, absBaseRunSpace, flag);
        if (intelligentObjectProperty != null) {
            return new IntelligentObjectProperty.ExpressionResult(intelligentObjectProperty.GetValueCore(runSpace,
                    objectRunSpace, absBaseRunSpace, param3, param4), runSpace, intelligentObjectRunSpace, this);
        }
        return new IntelligentObjectProperty.ExpressionResult(ExpressionValue.Instance, runSpace,
                intelligentObjectRunSpace, this);
    }

    public IntelligentObjectProperty getIntelligentObjectProperty(IRunSpace runSpace,
                                                                  IntelligentObjectRunSpace intelligentObjectRunSpace
            , IntelligentObjectRunSpace objectRunSpace, int param3, AbsBaseRunSpace absBaseRunSpace,
                                                                  boolean[] param5) {
        if (this.getReference() == null || this.elementType == ElementType.nullElement) {
            return this;
        }
        boolean[] flag = new boolean[]{false};
        Properties properties = this.method_33(runSpace, intelligentObjectRunSpace, absBaseRunSpace, param3,
                objectRunSpace, flag);
        if (properties == null) {
            this.ReportError(String.format(EngineResources.Error_UnableToGetReferencedPropertyValue,
                    this.getReference().Name()), runSpace, intelligentObjectRunSpace);
            return null;
        }
        if (!flag[0]) {
            param5[0] = false;
        }
        absBaseRunSpace = intelligentObjectRunSpace;
        if (objectRunSpace == null) {
            objectRunSpace = intelligentObjectRunSpace.getMayApplication().getFixedRunSpace();
        }
        return properties.values.get(this.getReference().overRidePropertyIndex).getIntelligentObjectProperty(runSpace,
                objectRunSpace, objectRunSpace, param3, absBaseRunSpace, param5);
    }

    private Properties method_33(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                 AbsBaseRunSpace absBaseRunSpace, int param3,
                                 IntelligentObjectRunSpace objectRunSpace, boolean[] flag) {
        // TODO: 2021/12/22
        return null;
    }


    protected ExpressionValue GetValueCore(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                           AbsBaseRunSpace absBaseRunSpace, IntelligentObjectRunSpace objectRunSpace,
                                           boolean param4) {
        return new ExpressionValue(this.StringValue());
    }

    public void DestroyInstance() {

    }

    public double isInvalidObjectNameValue(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.isInvalidObjectNameValue(runSpace, intelligentObjectRunSpace, -1);
    }

    private double isInvalidObjectNameValue(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                            int index) {
        IntelligentObjectProperty intelligentObjectProperty = this.getIntelligentObjectProperty(runSpace,
                intelligentObjectRunSpace, index);
        if (intelligentObjectProperty != null && intelligentObjectProperty.isInvalidObjectNameValue(null)) {
            return 0.0;
        }
        return 1.0;
    }

    protected Boolean isInvalidObjectNameValue(String name) {
        if (name == null) {
            if (this.invalidObjectName == null) {
                this.invalidObjectName = this.invalidObjectValue(this.objectValue);
            }
            return this.invalidObjectName;
        }
        return this.invalidObjectValue(name);
    }

    public IntelligentObjectProperty getIntelligentObjectProperty(IRunSpace runSpace,
                                                                  IntelligentObjectRunSpace intelligentObjectRunSpace
            , int index) {
        IntelligentObjectRunSpace objectRunSpace = null;
        AbsBaseRunSpace absBaseRunSpace = null;
        boolean[] flag = new boolean[]{false};
        return this.getIntelligentObjectProperty(runSpace, intelligentObjectRunSpace, objectRunSpace, index,
                absBaseRunSpace, flag);
    }

    public String getIntelligentObjectPropertyValue(IRunSpace runSpace,
                                                    IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.getIntelligentObjectPropertyValue(runSpace, intelligentObjectRunSpace, -1);
    }

    public String getIntelligentObjectPropertyValue(IRunSpace runSpace,
                                                    IntelligentObjectRunSpace intelligentObjectRunSpace, int index) {
        if (this.getReference() == null || this.elementType == ElementType.nullElement) {
            return this.StringValue();
        }
        IntelligentObjectProperty intelligentObjectProperty = this.getIntelligentObjectProperty(runSpace,
                intelligentObjectRunSpace, index);
        if (intelligentObjectProperty != null) {
            return intelligentObjectProperty.StringValue();
        }
        return null;
    }

    public Object getObjectValuePure(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        IntelligentObjectRunSpace objectRunSpace = null;
        return this.getObjectValue(runSpace, intelligentObjectRunSpace, null, objectRunSpace, -1);
    }

    protected Object getObjectValue(IRunSpace runSpace, IntelligentObjectRunSpace objectRunSpace, int index) {
        IntelligentObjectRunSpace result = null;
        return this.getObjectValue(runSpace, objectRunSpace, null, result, index);
    }

    public Object getObjectValue(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                 AbsBaseRunSpace absBaseRunSpace, int param3) {
        IntelligentObjectRunSpace result = null;
        return this.getObjectValue(runSpace, intelligentObjectRunSpace, absBaseRunSpace, result, param3);
    }

    private Object getObjectValue(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                  AbsBaseRunSpace absBaseRunSpace, IntelligentObjectRunSpace result, int index) {
        if (this.getReference() == null || this.elementType == ElementType.nullElement) {
            return this.GetObjectValue();
        }
        boolean[] flag = new boolean[]{true};
        IntelligentObjectProperty intelligentObjectProperty = this.getIntelligentObjectProperty(runSpace,
                intelligentObjectRunSpace, result, index, absBaseRunSpace, flag);
        if (intelligentObjectProperty != null) {
            return intelligentObjectProperty.GetObjectValue();
        }
        return null;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public void NotifyGroupInserted() {

    }

    public String getObjectNameMaybe() {
        return this.objectValue;
    }

    protected ElementType getElementType() {
        return this.elementType;
    }

    public boolean validProperty() {

        if (!this.IsSwitchedInCore()) {
            return false;
        }
        IntelligentObjectDefinition intelligentObjectDefinition = null;
        if (this.getPropertyObject() != null) {
            intelligentObjectDefinition = (IntelligentObjectDefinition) (this.getPropertyObject().objectDefinition);
        }
        if (intelligentObjectDefinition != null && intelligentObjectDefinition.IntelligentObject == this.getPropertyObject() && intelligentObjectDefinition.activeModel != null && !intelligentObjectDefinition.activeModel.Runnable()) {
            return false;
        }
        if (this.getPropertyObject() != null && !this.getPropertyObject().IsVisible(this)) {
            return false;
        }
        PropertyDefinitions propertyDefinitions = (this.getProperties() != null) ?
                this.getProperties().PropertyDefinitions : null;
        if (this.getStringPropertyDefinitionInfo().GetSwitchNumericProperty(propertyDefinitions) == null) {
            return true;
        }
        NumericDataPropertyRow numericDataPropertyRow = (this.getProperties() != null) ?
                (NumericDataPropertyRow) (this.getProperties().search((IntelligentObjectProperty inst) -> inst.getStringPropertyDefinitionInfo() == this.getStringPropertyDefinitionInfo().GetSwitchNumericProperty(propertyDefinitions))) : null;
        return numericDataPropertyRow != null && numericDataPropertyRow.validProperty() && (numericDataPropertyRow.IsAReference() || this.predicate(numericDataPropertyRow.getValue(), this.getStringPropertyDefinitionInfo().GetSwitchNumericCondition(propertyDefinitions), this.getStringPropertyDefinitionInfo().GetSwitchNumericValues(propertyDefinitions)));
    }

    private boolean predicate(Double value, SwitchNumericConditions switchNumericConditions,
                              List<Double> switchValues) {
        switch (switchNumericConditions) {
            case Equal:
                return switchValues.stream().anyMatch((Double double_0) -> value.compareTo(double_0) == 0);
            case NotEqual:
                return switchValues.stream().anyMatch((Double double_0) -> value.compareTo(double_0) != 0);
            case LessThan:
                return switchValues.stream().anyMatch((Double double_0) -> value.compareTo(double_0) < 0);
            case GreaterThan:
                return switchValues.stream().anyMatch((Double double_0) -> value.compareTo(double_0) > 0);
            default:
                return false;
        }
    }

    public boolean SetNativeObject(Object value) {
        return false;
    }

    public void setObjectName(String name) {
        this.objectValue = name;
        this.invalidObjectName = null;
    }


    public enum Enum20 implements IEnumMask {
        None,
        One;


        Enum20() {
            this.mask = (1 << ordinal());
        }

        private final int mask;

        @Override
        public int mask() {
            return this.mask;
        }
    }

    public boolean method_31(String ObjectValue, Class<?> type, int enum20) {
        String[] valueSplits = objectValue.split("\\.");
        if (valueSplits.length == 0 || !this.getStringPropertyDefinitionInfo().CanReferenceParent()) {
            return false;
        }
        IntelligentObjectDefinition intelligentObjectDefinition = this.getIntelligentObjectDefinition();
        if (intelligentObjectDefinition == null) {
            return false;
        }
        String result;
        if (valueSplits.length == 1) {
            this.absDefinition = intelligentObjectDefinition;
            this.elementType = ElementType.Object;
            result = valueSplits[0];
        } else {
            if (valueSplits.length == 2) {
                result = valueSplits[1];
                String parentName = valueSplits[0];
                Table parentTable = intelligentObjectDefinition.Tables().getTableByName(parentName);
                if (parentTable != null) {
                    StringPropertyDefinition parentDefinition =
                            parentTable.Schema().getPropertiesStringPropertyDefinition(result);
                    if (parentDefinition != null && type.isAssignableFrom(parentDefinition.getClass())) {
                        if (parentDefinition == this.getStringPropertyDefinitionInfo()) {
                            return false;
                        }
                        this.reference = parentDefinition;
                        this.table = parentTable;
                        this.elementType = ElementType.DataTable;
                        return true;
                    }
                }
            }

            if (IEnumMask.contains(enum20, Enum20.One)) {
                String valueWithPots = String.join(".", valueSplits);
                ObjectValuePotsOperator.ObjectValueWrapper objectValueWrapper =
                        ObjectValuePotsOperator.smethod_0(valueWithPots);
                if (objectValueWrapper.error == null) {
                    // TODO: 2022/1/29
                }
            }
        }
        return false;
    }

    private IntelligentObjectDefinition getIntelligentObjectDefinition() {
        if (this.getProperties() != null && this.getProperties().AbsPropertyObject != null) {
            if (this.getProperties().AbsPropertyObject.Parent() != null) {
                return this.getProperties().AbsPropertyObject.Parent();
            }
            if (!this.getProperties().PropertyDefinitions.notHaveRepeatingProperty()) {
                return (IntelligentObjectDefinition) this.getProperties().AbsPropertyObject.objectDefinition;
            }
        }
        return null;
    }


    public String formatName(String name) {
        return NameValidOperator.smethod_0(name);
    }

    public enum VersionEnum {
        //        NumericDataProperty,
//        AboutElementProperty,
        InitVersion,
        UserVersion,
        InnerVersion
    }

    public void ReportError(String error, IRunSpace runSpace,
                            IntelligentObjectRunSpace statisticsDataSourceIntelligentObject) {
        IRunSpace _runSpace = runSpace != null ? runSpace : statisticsDataSourceIntelligentObject;
        RuntimeErrorFullMessageDetails.reportError(_runSpace, this.getProperties().AbsPropertyObject, this,
                String.format(ErrorString.RUNTIME_ERROR_UNABLE_TO_GET_VALUE_OF_PROPERTY, this.Name()) + "\n\n" + error);

    }

    public String getDefaultName(PropertyDefinitions propertyDefinitions) {
        String name = null;
        if (this.getPropertyObject() != null) {
            name = this.getPropertyObject().getDefaultValueOverrideFor(this);
        }

        if (name == null) {
            name = this.getStringPropertyDefinitionInfo().GetDefaultStringBy(propertyDefinitions);
        }
        return this.getDefaultName(name);
    }

    public String getDefaultName(String defaultString) {
        if (IntelligentObjectProperty.regex.matcher(defaultString).find()) {
            String instanceName = "";
            String definitionName = "";
            var propertyObject = this.getPropertyObject();
            if (propertyObject instanceof Node) {
                Node node = (Node) propertyObject;
                IntelligentObject intelligentObject = node.IntelligentObject;
                if (intelligentObject != null) {
                    instanceName = intelligentObject.InstanceName();
                    definitionName = intelligentObject.DefinitionName();
                    if (node.Parent() != null) {
                        definitionName =
                                node.Parent().getInternalReference().Name((IntelligentObjectDefinition) intelligentObject.objectDefinition);
                    }
                }
            }

            String name = this.getPropertyObject().objectDefinition.Name();
            if (propertyObject instanceof IntelligentObject && propertyObject.Parent() != null) {
                IntelligentObject intelligentObject = (IntelligentObject) propertyObject;
                name = intelligentObject.Parent().getInternalReference().Name((IntelligentObjectDefinition) intelligentObject.objectDefinition);
            }

            var macros = new HashMap<String, String>();
            macros.put("InstanceName", this.getPropertyObject().InstanceName());
            macros.put("DefinitionName", name);
            macros.put("AssociatedObjectInstanceName", instanceName);
            macros.put("AssociatedObjectDefinitionName", definitionName);

            return IntelligentObjectProperty.regex.matcher(defaultString).replaceAll(m -> {
                String mr = m.group(1);
                var result = macros.get(mr);
                if (result != null) {
                    return result;
                }
                return m.group();
            });
        }
        return defaultString;
    }


    public String getDisplay() {
        if (this.getPropertyObject() == null) {
            return this.Name();
        }
        if (this.getPropertyObject() instanceof AbsBaseStepProperty) {
            return this.getPropertyObject().getDefinitionName() + "." + this.Name();
        }
        return this.getPropertyObject().InstanceName() + "." + this.Name();
    }

    public Object getListData() {
        return null;
    }

    public IntelligentObjectDefinition getIntelligentObjectFacility() {
        if (this.getProperties() != null && this.getProperties().getAbsPropertyObject() != null) {
            if (this.getProperties().getAbsPropertyObject().getIntelligentObjectFacility() != null) {
                return this.getProperties().getAbsPropertyObject().getIntelligentObjectFacility();
            }
            if (!this.getProperties().getPropertyDefinitions().notHaveRepeatingProperty()) {
                return (IntelligentObjectDefinition) this.getProperties().getPropertyDefinitions().getTargetObject();
            }
        }
        return null;
    }

    public IntelligentObjectDefinition getExperimentConstraintsIntelligentObjectFacility() {
        if (this.getIntelligentObjectFacility() != null) {
            return this.getIntelligentObjectFacility();
        }

        if (this.getProperties() == null || this.getProperties().getAbsPropertyObject() == null) {
            return null;
        }

        GridObjectDefinition gridObjectDefinition = this.getProperties().getAbsPropertyObject().getObjectDefinition();
        ExperimentConstraintsDefinition experimentConstraintsDefinition =
                (ExperimentConstraintsDefinition) gridObjectDefinition;
        if (!Objects.isNull(experimentConstraintsDefinition)) {
            return experimentConstraintsDefinition.getProjectAndApplicationContext().getIntelligentObjectFacility();
        }
        return (IntelligentObjectDefinition) gridObjectDefinition;
    }

    public AbsPropertyObject getPropertyObject() {
        return this.getProperties() == null ? null : this.getProperties().getAbsPropertyObject();
    }

    public Properties getProperties() {
        return this.properties;
    }

    public enum Enum19 {
        Zero,
        One
    }


    public static class ValueVersion {
        public final VersionEnum type;

        public final int version;

        private ValueVersion(VersionEnum type, int version) {
            this.type = type;
            this.version = version;
        }

        public static ValueVersion initValue() {
            return new ValueVersion(VersionEnum.values()[0], 0);
        }

        public static ValueVersion setValueByUserVersion(int version) {
            return new ValueVersion(VersionEnum.values()[1], version);
        }

        public static ValueVersion setValueByInternalVersion() {
            return new ValueVersion(VersionEnum.values()[2], 0);
        }

        public static ValueVersion innerVersion() {
            return new IntelligentObjectProperty.ValueVersion(VersionEnum.UserVersion, 0);

        }

        public static IntelligentObjectProperty.ValueVersion initValueVersion() {
            return new IntelligentObjectProperty.ValueVersion(IntelligentObjectProperty.VersionEnum.InitVersion, 0);
        }

        public static ValueVersion userVersion(int version) {
            return new IntelligentObjectProperty.ValueVersion(IntelligentObjectProperty.VersionEnum.UserVersion,
                    version);
        }
    }

    protected interface IItemXml {
        void writeItemsXml(XmlWriter xmlWriter, CommonItems commonItems);

        boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml);
    }

    public static class ExpressionResult {
        private final ExpressionValue expressionValue;
        private final IRunSpace runSpace;
        private final IntelligentObjectRunSpace intelligentObject;
        private final IntelligentObjectProperty instance;


        public ExpressionResult(ExpressionValue expressionValue, IRunSpace runSpace,
                                IntelligentObjectRunSpace intelligentObject,
                                IntelligentObjectProperty instance) {
            this.expressionValue = expressionValue;
            this.runSpace = runSpace;
            this.intelligentObject = intelligentObject;
            this.instance = instance;
        }

        public static ExpressionValue getValue(ExpressionResult expressionResult) {
            return expressionResult.expressionValue;
        }

        public double toDouble() {
            try {
                return this.expressionValue.getExpressionResult();
            } catch (ExpressionResultTypeException exception) {
                this.instance.ReportError(exception.toString(), this.runSpace, this.intelligentObject);
            }
            return Double.NaN;
        }

        public static double toDouble(ExpressionResult expressionResult) {
            return expressionResult.toDouble();
        }

        public String getResultString() {
            try {
                return this.expressionValue.getExpressionResultString();
            } catch (ExpressionResultTypeException exception) {
                this.instance.ReportError(exception.toString(), this.runSpace, this.intelligentObject);
            }
            return null;
        }

        public String toResultString(ExpressionResult expressionResult) {
            return expressionResult.getResultString();
        }

        public ExpressionValue toExpressionValue() {
            return this.expressionValue;
        }

        public AbsBaseRunSpace getAbsBaseRunSpace() {
            try {
                return this.expressionValue.getAbsBaseRunSpace();
            } catch (ExpressionResultTypeException exception) {
                this.instance.ReportError(exception.toString(), this.runSpace, this.intelligentObject);
            }
            return null;
        }

        public AbsBaseRunSpace toAbsBaseRunSpace(ExpressionResult expressionResult) {
            return expressionResult.getAbsBaseRunSpace();
        }
    }

    protected StringPropertyDefinition getPropertyDefinition() {
        return this.propertyDefinition;
    }

    public boolean IsAReference() {
        return this.getReference() != null;
    }

    public void NotifyObjectParentChanged(IntelligentObjectDefinition objectFacility) {

    }

    public void destroyInstance() {

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
    public Object Item() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void Value(String value) {

    }

    @Override
    public String getGetStringValue(IExecutionContext context) {
        return null;
    }

    @Override
    public double GetDoubleValue(IExecutionContext context) {
        return 0;
    }


    public String getStringValue() {
        return this.stringValue;
    }

    public void setRow(Properties properties) {
        this.properties = properties;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }


    @FunctionalInterface
    protected interface GetError {
        String apply();
    }

}
