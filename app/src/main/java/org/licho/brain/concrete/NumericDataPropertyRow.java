package org.licho.brain.concrete;

import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.PropertyGridFeel;

/**
 *
 */
public class NumericDataPropertyRow extends IntelligentObjectProperty {
    protected double value;

    public NumericDataPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    @Override
    public Object GetNativeObject() {
        var definitionInfo = super.getStringPropertyDefinitionInfo();
        if (definitionInfo instanceof NumericDataPropertyDefinition &&
                ((NumericDataPropertyDefinition) definitionInfo).DataType() != NumericDataType.Integer) {
            return this.value;
        }

        if (Double.isInfinite(this.value)) {
            return Integer.MAX_VALUE;
        }
        return (int) this.value;
    }

    protected PropertyGridFeel Feel() {
        return PropertyGridFeel.editlist;
    }

    public double getValue() {
        return this.value;
    }


//    @Override
//    	public  boolean SetNativeObject(Object value)
//	{
//		if ((super.getStringPropertyDefinitionInfo() as NumericDataPropertyDefinition).DataType == NumericDataType
//		.Integer && value is int && (int)value == 2147483647)
//		{
//			super.clear();
//			super.setError(null);
//			this.value = double.PositiveInfinity;
//			super.setObjectNameMaybe("Infinity");
//			super.getProperties().method_26(this, false, null,
//                    IntelligentObjectProperty.ValueVersion.initValueVersion());
//			return true;
//		}
//		return super.SetNativeObject(value);
//	}
}
