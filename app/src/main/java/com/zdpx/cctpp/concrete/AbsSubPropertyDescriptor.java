package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.utils.simu.system.PropertyDescriptor;

import javax.management.Attribute;
import java.beans.IntrospectionException;
import java.lang.annotation.Annotation;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbsSubPropertyDescriptor extends PropertyDescriptor {

    protected StringPropertyDefinition StringPropertyDefinition;

    private boolean isReadOnly;

    private final TypeConverter typeConverter;

    public AbsSubPropertyDescriptor(StringPropertyDefinition StringPropertyDefinition, Class<?> cl) throws IntrospectionException {
        this(StringPropertyDefinition, StringPropertyDefinition.Name(), false, true, false, cl);
    }

    public AbsSubPropertyDescriptor(StringPropertyDefinition stringPropertyDefinition, String param1, boolean bool_0,
                                    boolean bool_1, boolean bool_2, Class<?> cl) throws IntrospectionException {
        super(param1, AbsSubPropertyDescriptor.smethod_0(stringPropertyDefinition, bool_0, bool_1, bool_2), cl);

        this.StringPropertyDefinition = stringPropertyDefinition;
        this.typeConverter = this.StringPropertyDefinition.NativeObjectTypeConverter();
    }

    private static Annotation[] smethod_0(StringPropertyDefinition stringPropertyDefinition, boolean param1,
                                         boolean param2, boolean param3) {
        List<Annotation> displayNameAttributes = new ArrayList<>();
//		displayNameAttributes.add(new DisplayNameAttribute(param2 ? stringPropertyDefinition.FormattedDisplayName :
//		stringPropertyDefinition.DisplayName));
//		DataFormat df = stringPropertyDefinition.dataFormat;
//		if (df != DataFormat.List)
//		{
//			if (df == DataFormat.DateTime)
//			{
//				displayNameAttributes.add(new SpecialEditorAttribute((Enum53)3));
//			}
//		}
//		else
//		{
//			displayNameAttributes.add(new SpecialEditorAttribute((Enum53)0));
//		}
//		if (stringPropertyDefinition is ForeignKeyPropertyDefinition)
//		{
//			displayNameAttributes.add(new ClassificationAttribute((Enum54)1));
//		}
//		if (stringPropertyDefinition.owner.TargetObject is TableDefinition)
//		{
//			TableDefinition tableDefinition = stringPropertyDefinition.owner.TargetObject as TableDefinition;
//			if (tableDefinition.Parent().contains(stringPropertyDefinition))
//			{
//				displayNameAttributes.add(new ClassificationAttribute((Enum54)0));
//			}
//		}
//		if (stringPropertyDefinition.NativeObjectTypeConverter != null)
//		{
//			displayNameAttributes.add(new UseTypeDescriptorForStringDisplayAttribute());
//		}
//		if (param1)
//		{
////			Class1133.smethod_0(stringPropertyDefinition, displayNameAttributes, param3);
//		}
        return displayNameAttributes.toArray(new Annotation[0]);
    }


    @Override
    public String Name() {
        return this.StringPropertyDefinition.Name();
    }

    protected abstract Properties GetPropertyGroup(Object target);


    @Override
    public Class<?> PropertyType() {
        return this.StringPropertyDefinition.NativeObjectType();
    }

    @Override
    public boolean IsReadOnly() {
        return this.isReadOnly;
    }

    @Override
    public boolean CanResetValue(Object param0) {
        return false;
    }

    @Override
    public void ResetValue(Object param0) {
        throw new IllegalArgumentException();
    }

    @Override
    public boolean ShouldSerializeValue(Object param0) {
        return false;
    }

    @Override
    public Object GetValue(Object target) {
        Properties propertyGroup = this.GetPropertyGroup(target);
        if (propertyGroup == null || this.StringPropertyDefinition.overRidePropertyIndex >= propertyGroup.size()) {
            return null;
        }
        IntelligentObjectProperty intelligentObjectProperty =
                propertyGroup.get(this.StringPropertyDefinition.overRidePropertyIndex);
        if (intelligentObjectProperty.IsAReference() && !(intelligentObjectProperty instanceof RepeatStringPropertyRow)) {
            return intelligentObjectProperty.StringValue();
        }
        return intelligentObjectProperty.GetNativeObject();
    }

    @Override
    public void SetValue(Object param0, Object param1) {
        Properties propertyGroup = this.GetPropertyGroup(param0);
        if (propertyGroup != null && this.StringPropertyDefinition.overRidePropertyIndex < propertyGroup.size()) {
            IntelligentObjectProperty intelligentObjectProperty =
                    propertyGroup.get(this.StringPropertyDefinition.overRidePropertyIndex);
            if (intelligentObjectProperty.SetNativeObject(param1)) {
                return;
            }

            if (param1 instanceof String) {
                String text = (String) param0;
                intelligentObjectProperty.StringValue(text);
                return;
            }
            intelligentObjectProperty.SetCultureInvariantStringValue((param1 == null) ? "" :
                    MessageFormat.format("{0}", param1), IntelligentObjectProperty.ValueVersion.initValueVersion());
        }
    }

}
