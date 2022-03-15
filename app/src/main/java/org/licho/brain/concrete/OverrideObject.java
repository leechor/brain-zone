package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.simioEnums.SwitchNumericConditions;
import org.licho.brain.utils.simu.IReferencedObjects;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public class OverrideObject implements IListener {
    public OverrideObject overrideObject;
    private String category;
    private String description;
    private Boolean isVisible;
    private String displayName;
    private String defaultValue;
    private PropertyDefinitionFacade parent;
    private ProductComplexityLevel complexityLevel;
    private String switchNumericPropertyName;
    private NumericDataPropertyDefinition numericDataProperty;
    private PropertyDefinitions propertyDefinitions;
    private StringPropertyDefinition stringProperty;
    private SwitchNumericConditions switchNumericCondition;
    private List<Double> switchNumericValues = new ArrayList<>();

    public OverrideObject(StringPropertyDefinition stringPropertyDefinition, PropertyDefinitions propertyDefinitions,
                          OverrideObject overrideObject) {
        this.stringProperty = stringPropertyDefinition;
        this.propertyDefinitions = propertyDefinitions;
        this.overrideObject = overrideObject;
    }

    public static OverrideObject readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                         PropertyDefinitions propertyDefinitions) {
        if (Objects.equals(xmlReader.Name(), "Override")) {
            String name = xmlReader.GetAttribute("Name");
            if (propertyDefinitions.TargetObject instanceof IntelligentObjectDefinition) {
                IntelligentObjectDefinition intelligentObjectDefinition =
                        (IntelligentObjectDefinition) propertyDefinitions.TargetObject;
                StringPropertyDefinition stringPropertyDefinition =
                        intelligentObjectDefinition.findStringPropertyDefinitionInfoByName(name, propertyDefinitions,
                                intelligentObjectXml);
                if (stringPropertyDefinition != null) {
                    int num = propertyDefinitions.values.indexOf(stringPropertyDefinition);
                    if (num != -1) {
                        OverrideObject overrideObject = propertyDefinitions.overrides.get(num);
                        overrideObject.readXmlAttribute(xmlReader, intelligentObjectXml);
                        return overrideObject;
                    }
                }
            }
        }
        return null;
    }

    void readXmlAttribute(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Override", (XmlReader attr) ->
                {
                    SomeXmlOperator.readXmlBooleanAttribute(attr, "Visible", (Boolean visible) ->
                            this.isVisible = visible);
                    this.displayName = attr.GetAttribute("DisplayName");
                    this.description = attr.GetAttribute("Description");
                    this.category = attr.GetAttribute("Category");
                    String defaultValue = attr.GetAttribute("DefaultValue");
                    if (this.stringProperty != null) {
                        this.defaultValue = this.stringProperty.LoadDefaultValue(defaultValue, intelligentObjectXml);
                    } else {
                        this.defaultValue = defaultValue;
                    }
                    SomeXmlOperator.readEnumAttribute
                            (attr, "ComplexityLevel", (ProductComplexityLevel productComplexityLevel) ->
                                            this.complexityLevel = productComplexityLevel,
                                    ProductComplexityLevel.class);
                }, xmlReaderSwitch -> SomeXmlOperator.xmlReaderElementOperator(xmlReaderSwitch, "Switch",
                        (XmlReader property) -> {
                            SomeXmlOperator.readXmlAttributeString(property, "Property",
                                    this::SwitchNumericPropertyName);
                            SomeXmlOperator.readEnumAttribute(property, "Comparison",
                                    this::SwitchNumericCondition, SwitchNumericConditions.class);
                            SomeXmlOperator.readXmlAttributeString(property, "Value", this::setValue);
                        }, null)
        );
    }

    public SwitchNumericConditions SwitchNumericCondition() {
        return this.switchNumericCondition;
    }

    public void setValue(String value) {
        // TODO: 2022/1/3
    }

    public void SwitchNumericCondition(SwitchNumericConditions value) {
        this.switchNumericCondition = value;
        this.stringProperty.propertyChangedEventHandler("SwitchNumericCondition");
    }

    public Boolean Visible() {
        return this.isVisible;
    }

    public void Visible(Boolean value) {
        this.isVisible = value;

    }

    public String Category() {
        return this.category;
    }

    public void Category(String category) {
        this.category = category;
    }


    public String DisplayName() {
        return this.displayName;
    }

    public void DisplayName(String value) {
        this.displayName = value;
    }

    public String Description() {
        return this.description;
    }

    public void Description(String description) {
        this.description = description;
    }

    public String Default() {
        return this.defaultValue;
    }

    public void Default(String value) {
        // TODO: 2021/11/16 
    }

    public PropertyDefinitionFacade Parent() {
        return this.parent;
    }

    public void Parent(PropertyDefinitionFacade value) {
        this.parent = value;
    }

    public ProductComplexityLevel ComplexityLevel() {
        return this.complexityLevel;
    }

    public void ComplexityLevel(ProductComplexityLevel value) {
        this.complexityLevel = value;
    }

    public String SwitchNumericPropertyName() {
        if (this.numericDataProperty == null) {
            return this.switchNumericPropertyName;
        }
        return this.numericDataProperty.Name();
    }

    public void SwitchNumericPropertyName(String value) {
        // TODO: 2021/11/16 
    }

    public PropertyDefinitions getPropertyDefinitions() {
        return propertyDefinitions;
    }

    public void setPropertyDefinitions(PropertyDefinitions propertyDefinitions) {
        this.propertyDefinitions = propertyDefinitions;
    }

    public OverrideObject OverrideObject() {
        return this.overrideObject;
    }

    public NumericDataPropertyDefinition SwitchNumericProperty() {

        return this.numericDataProperty;
    }

    public void SwitchNumericProperty(NumericDataPropertyDefinition value) {

        this.numericDataProperty = value;
        this.stringProperty.propertyChangedEventHandler("SwitchNumericProperty");
    }

    public String getErrorInvalidSwitchProperty() {
        if (this.numericDataProperty == null) {
            if (!Strings.isNullOrEmpty(this.switchNumericPropertyName)) {
                return EngineResources.Error_InvalidSwitchPropertyName;
            }
        } else if (!this.numericDataProperty.vmethod_0()) {
            return EngineResources.Error_InvalidSwitchPropertyType;
        }
        return null;
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

    public List<Double> getSwitchNumericValues() {
		return this.switchNumericValues;
    }
}

