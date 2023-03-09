package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.annotations.PropertyDefinitionFactory;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.UnitType;
import org.licho.brain.brainEnums.NumericFormat;

@PropertyDefinitionFactory(NumericDataPropertyFactory.class)
public class NumericDataPropertyDefinition extends StringPropertyDefinition {
    private static final TypeConverter subInt32Converter = new SubInt32Converter();
    ;
    private boolean isInfinityInList;
    private NumericDataType dataType;
    private NumericPropertyUnitType numericPropertyUnitType;
    private StringPropertyDefinition unitTypePropertyDefinition;
    private String unitTypePropertyName;
    public PropertyDefinitions owner;
    private int defaultUnitSubType;

    public NumericDataPropertyDefinition(String name, NumericDataType numericDataType) {
        super(name);
        this.dataType = numericDataType;
        this.isInfinityInList = true;
        switch (this.DataType()) {
            case Real:
                this.defaultString = "0.0";
                this.dataFormat = DataFormat.Real;
                return;
            case Integer:
                this.defaultString = "0";
                this.dataFormat = DataFormat.Integer;
                return;
            default:
                return;
        }
    }

    @Override
    protected IValueProvider ValueProvider() {
        return new BooleanValueProvider(this.isInfinityInList);
    }

    public NumericDataType DataType() {
        return this.dataType;
    }

    public void DataType(NumericDataType value) {
        this.dataType = value;
        super.propertyChangedEventHandler("DataType");
    }

    public boolean InfinityInList() {
        return this.isInfinityInList;
    }

    public void InfinityInList(boolean value) {
        this.isInfinityInList = value;
        super.propertyChangedEventHandler("InfinityInList");
    }

    public StringPropertyDefinition UnitTypePropertyDefinition() {
        return this.unitTypePropertyDefinition;
    }

    public void UnitTypePropertyDefinition(StringPropertyDefinition value) {
        if (value != null) {
            this.numericPropertyUnitType = NumericDataPropertyDefinition.NumericPropertyUnitType.PropertyReferenced;
        }
        this.unitTypePropertyDefinition = value;
        super.propertyChangedEventHandler("UnitTypeProperty");
    }

    public String UnitTypePropertyName() {
        if (this.UnitTypePropertyDefinition() != null) {
            return this.UnitTypePropertyDefinition().Name();
        }
        return this.unitTypePropertyName;
    }

    public void UnitTypePropertyName(String value) {
        this.unitTypePropertyName = value;
        StringPropertyDefinition unitTypePropertyDefinition = null;
        if (this.owner != null) {
            unitTypePropertyDefinition = this.owner.findStringPropertyDefinitionInfoByName(this.unitTypePropertyName);
        }
        this.UnitTypePropertyDefinition(unitTypePropertyDefinition);
    }

    public int DefaultUnitSubType() {
        return this.defaultUnitSubType;
    }

    public void DefaultUnitSubType(int value) {
        int type = this.defaultUnitSubType;
        this.defaultUnitSubType = value;
        super.propertyChangedEventHandler("DefaultUnitSubType");
        if (this.owner != null) {
            this.owner.NumericPropertyDefaultUnitSubTypeChanged(this, this.owner, type, this.defaultUnitSubType);
        }
    }

    public NumericPropertyUnitType UnitType() {
        return this.numericPropertyUnitType;
    }

    public void UnitType(NumericPropertyUnitType value) {
        if (value != NumericDataPropertyDefinition.NumericPropertyUnitType.PropertyReferenced) {
            this.UnitTypePropertyName(null);
        }
        this.numericPropertyUnitType = value;
        this.DefaultUnitSubType(0);
        super.propertyChangedEventHandler("UnitType");
    }


    public NumericDataPropertyDefinition(String name, NumericDataType dataType, String s) {
        super(name, s);
        this.defaultString = s;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new NumericDataPropertyRow(this, properties);
    }

    @Override
    public String getObjectClassName() {
        return EngineResources.NumericProperty_ClassName;
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.NumericProperty_ClassDescription;
    }

    @Override
    public GridItemProperty GetGridItemProperties(PropertyDefinitions propertyDefinitions) {
        return null;
    }

    protected void method_40(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
    }

    public boolean vmethod_0() {
        return true;
    }

    public NumericFormat NumericDataFormat() {
        if (this.DataType() == NumericDataType.Integer) {
            return NumericFormat.Integer;
        }
        return NumericFormat.Real;
    }

    public void NumericDataFormat(NumericFormat value) {
        if (value == NumericFormat.Integer) {
            this.DataType(NumericDataType.Integer);
        } else {
            this.DataType(NumericDataType.Real);
        }
        super.propertyChangedEventHandler("NumericDataFormat");
    }

    public enum NumericPropertyUnitType {
        Unspecified,
        Time,
        TravelRate,
        TravelAcceleration,
        Length,
        Currency,
        CurrencyPerTimeUnit,
        Volume,
        VolumeFlowRate,
        Weight,
        WeightFlowRate,
        PropertyReferenced
    }

    @Override
    protected void WriteXmlAttributes(XmlWriter xmlWriter, PropertyDefinitions propertyDefinitions) {
//		super.WriteXmlAttributes(xmlWriter, propertyDefinitions);
//		if (this.UnitType != NumericDataPropertyDefinition.NumericPropertyUnitType.Unspecified && this.UnitType !=
//		NumericDataPropertyDefinition.NumericPropertyUnitType.PropertyReferenced)
//		{
//			xmlWriter.WriteAttributeString("UnitType", this.UnitType.ToString());
//		}
//		if (this.UnitTypePropertyDefinition != null)
//		{
//			xmlWriter.WriteAttributeString("UnitTypeProperty", this.UnitTypePropertyDefinition.Name);
//		}
//		if (this.DefaultUnitSubType != 0)
//		{
//			xmlWriter.WriteAttributeString("DefaultUnits", AboutUnit.getUnitPerDescription(this.method_39(), this
//			.DefaultUnitSubType));
//		}
    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {
        super.ReadXmlAttributes(xmlReader, propertyDefinitions);
        SomeXmlOperator.readEnumAttribute(xmlReader, "UnitType", this::UnitType,
                NumericDataPropertyDefinition.NumericPropertyUnitType.class);
        String unitTypeProperty = xmlReader.GetAttribute("UnitTypeProperty");
        if (!Strings.isNullOrEmpty(unitTypeProperty)) {
            this.UnitTypePropertyName(unitTypeProperty);
        }
        String defaultUnits = xmlReader.GetAttribute("DefaultUnits");
        if (!Strings.isNullOrEmpty(defaultUnits)) {
            this.DefaultUnitSubType(AboutUnit.getUnitLevel(this.getUnitType(), defaultUnits));
        }
    }

    private UnitType getUnitType() {
        switch (this.numericPropertyUnitType) {
            case Unspecified:
                return UnitType.Unspecified;
            case Time:
                return UnitType.Time;
            case TravelRate:
                return UnitType.TravelRate;
            case TravelAcceleration:
                return UnitType.TravelAcceleration;
            case Length:
                return UnitType.Length;
            case Currency:
                return UnitType.Currency;
            case CurrencyPerTimeUnit:
                return UnitType.CurrencyPerTimeUnit;
            case Volume:
                return UnitType.Volume;
            case VolumeFlowRate:
                return UnitType.VolumeFlowRate;
            case Weight:
                return UnitType.Weight;
            case WeightFlowRate:
                return UnitType.WeightFlowRate;
            default:
                return UnitType.Unspecified;
        }
    }

    @Override
    public void RefreshIfInError() {
        if (this.validUnitTypeProperty()) {
            this.UnitTypePropertyName(this.UnitTypePropertyName());
        }
        super.RefreshIfInError();
    }

    private boolean validUnitTypeProperty() {
        return this.unitTypePropertyDefinition == null && !Strings.isNullOrEmpty(this.unitTypePropertyName);
    }

    @Override
    public void UpdateForParentObjectPropertyChange(StringPropertyDefinition stringPropertyDefinition, Enum38 enum38) {
        if (enum38 == Enum38.Zero && stringPropertyDefinition == this.unitTypePropertyDefinition) {
            this.UnitTypePropertyName(this.UnitTypePropertyName());
        }
        super.UpdateForParentObjectPropertyChange(stringPropertyDefinition, enum38);
    }

    @Override
    public boolean IsSamePropertyType(StringPropertyDefinition stringPropertyDefinition) {
        if (stringPropertyDefinition instanceof NumericDataPropertyDefinition) {
            NumericDataPropertyDefinition numericDataPropertyDefinition =
                    (NumericDataPropertyDefinition) stringPropertyDefinition;
            return this.dataFormat == numericDataPropertyDefinition.dataFormat;
        }
        return super.IsSamePropertyType(stringPropertyDefinition);
    }

    @Override
    public Class<?> NativeObjectType() {
        switch (this.DataType()) {
            case Real:
                return Double.class;
            case Integer:
                return Integer.class;
            default:
                return super.NativeObjectType();
        }
    }

    @Override
    public TypeConverter NativeObjectTypeConverter() {
        if (this.DataType() == NumericDataType.Integer) {
            return NumericDataPropertyDefinition.subInt32Converter;
        }
        return super.NativeObjectTypeConverter();
    }

    @Override
    public void CopyFrom(StringPropertyDefinition stringPropertyDefinition) {
        if (stringPropertyDefinition instanceof NumericDataPropertyDefinition) {
            NumericDataPropertyDefinition numericDataPropertyDefinition =
                    (NumericDataPropertyDefinition) stringPropertyDefinition;
            this.UnitType(numericDataPropertyDefinition.UnitType());
            this.DefaultUnitSubType(numericDataPropertyDefinition.DefaultUnitSubType());
        }
        super.CopyFrom(stringPropertyDefinition);
    }


    @Override
    public void CopyFrom(IntelligentObjectProperty intelligentObjectProperty) {
        if (intelligentObjectProperty instanceof NumericDataPropertyRow) {
            NumericDataPropertyRow numericDataPropertyRow =
                    (NumericDataPropertyRow) intelligentObjectProperty;
            super.DefaultString(numericDataPropertyRow.Value());
        }
        super.CopyFrom(intelligentObjectProperty);
    }
}
