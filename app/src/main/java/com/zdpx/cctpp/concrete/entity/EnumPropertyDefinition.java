package com.zdpx.cctpp.concrete.entity;

import com.google.common.base.Strings;
import com.zdpx.cctpp.concrete.GridItemProperty;
import com.zdpx.cctpp.concrete.IIdentityName;
import com.zdpx.cctpp.concrete.IValueProvider;
import com.zdpx.cctpp.concrete.IntelligentObjectXml;
import com.zdpx.cctpp.concrete.NumericDataPropertyDefinition;
import com.zdpx.cctpp.concrete.Properties;
import com.zdpx.cctpp.concrete.PropertyDefinitionName;
import com.zdpx.cctpp.concrete.PropertyDefinitions;
import com.zdpx.cctpp.concrete.SomeXmlOperator;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;
import com.zdpx.cctpp.enu.NumericDataType;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.simioEnums.PropertyEnumTypes;
import com.zdpx.cctpp.simioEnums.QueueRanking;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Set;

@PropertyDefinitionName("EnumProperty")
public class EnumPropertyDefinition extends NumericDataPropertyDefinition {

    private Class<? extends Enum<?>> type;

    public Enum<?> enumValue;
    public String[] stringValues;
    public boolean[] visibles;
    public PropertyEnumTypes propertyEnumTypes;

    public EnumPropertyDefinition(String name) {
        this(name, QueueRanking.class);
    }

    public EnumPropertyDefinition(String name, Class<? extends Enum<?>> type) {
        super(name, NumericDataType.None);
        this.type = type;
        var e = type.getEnumConstants();
        if (e != null && e.length > 0) {
            this.defaultString = e[0].name();
            this.enumValue = (e[0]);
        }

        if (type.isAssignableFrom(PropertyEnumTypes.class)) {
            this.propertyEnumTypes = PropertyEnumTypes.valueOf(type.getSimpleName());
        }
        int typeLength = this.type.getEnumConstants().length;
        this.stringValues = Arrays.stream(this.type.getEnumConstants()).map(Enum::name).toArray(String[]::new);
        this.visibles = new boolean[typeLength];
        Arrays.fill(this.visibles, true);
        this.dataFormat = DataFormat.List;
    }

    @Override
    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new EnumPropertyRow(this, properties);
    }

    @Override
    public GridItemProperty GetGridItemProperties(PropertyDefinitions propertyDefinitions) {
        return new GridItemProperty(this.Name(), super.CategoryName(), this.overRidePropertyIndex + 1000,
                this.enumValue,
                null, PropertyGridFeel.list, super.GetDisplayName(propertyDefinitions), super.Description(),
                new EnumPropertyOperator(Enum.class, () -> this.enumValue,
                        t -> {
                            this.enumValue = t;
                            super.DefaultString(this.enumValue.toString());
                        }, null, this.type));
    }

    @Override
    public void UpdateGridPropertyValue(int param0, Object param1) {
        this.enumValue = (Enum) param1;
        super.DefaultString(this.enumValue.toString());
    }

    @Override
    protected IValueProvider ValueProvider() {
        return new EnumPropertyValueProvider(this);
    }

    public PropertyEnumTypes EnumTypeName() {
        return this.propertyEnumTypes;
    }

    public void EnumTypeName(PropertyEnumTypes value) {
        this.propertyEnumTypes = value;
        String name = this.propertyEnumTypes.getDeclaringClass().getSimpleName();

        Reflections reflections = new Reflections("com.zdpx.cctpp.simioEnums");
        Set<Class<? extends Enum>> types = reflections.getSubTypesOf(Enum.class);

        for (var e : types) {
            if (name.equalsIgnoreCase(e.getSimpleName())) {
                this.type = (Class<Enum<?>>) e;
                this.defaultString = "";
                this.defaultString = this.type.getEnumConstants()[0].name();
                this.enumValue = this.type.getEnumConstants()[0];
                int num = this.type.getEnumConstants().length;
                this.stringValues = Arrays.stream(this.type.getEnumConstants())
                        .map(Enum::name).toArray(String[]::new);
                this.visibles = new boolean[num];
                Arrays.fill(this.visibles, true);

                if (this.owner != null) {
                    this.owner.UpdateForNewPropertyDefinition(this);
                }
                break;
            }
        }
        super.propertyChangedEventHandler("EnumTypeName");
    }

    @Override
    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {
        super.ReadXmlAttributes(xmlReader, propertyDefinitions);
        SomeXmlOperator.readEnumAttribute(xmlReader, "EnumType", this::EnumTypeName, PropertyEnumTypes.class);
    }

    @Override
    protected boolean ReadXmlBody(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                  IIdentityName identityName) {
        return super.ReadXmlBody(xmlReader, intelligentObjectXml, identityName) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Captions", null, (t) -> {
                    return SomeXmlOperator.xmlReaderElementOperator(t, "Caption",
                            r -> {

                                String value = r.GetAttribute("Value");
                                String display = r.GetAttribute("Display");
                                String visible = r.GetAttribute("Visible");
                                if (!Strings.isNullOrEmpty(value) &&
                                        Arrays.stream(this.type.getEnumConstants()).anyMatch(v -> v.name().equals(value))) {
                                    String[] names =
                                            Arrays.stream(this.type.getEnumConstants()).map(Enum::name).toArray(String[]::new);
                                    int num = -1;
                                    for (int i = 0; i < names.length; i++) {
                                        if (names[i].equalsIgnoreCase(value)) {
                                            num = i;
                                            break;
                                        }
                                    }
                                    if (display != null && num >= 0) {
                                        this.stringValues[num] = display;
                                    }
                                    if (visible.equalsIgnoreCase("False")) {
                                        this.visibles[num] = false;
                                    }
                                }

                            }, null);

                });
    }

}
