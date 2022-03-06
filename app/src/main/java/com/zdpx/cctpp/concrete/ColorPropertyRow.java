package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.enu.ElementType;
import com.zdpx.cctpp.enu.PropertyGridFeel;
import com.zdpx.cctpp.utils.simu.system.Color;

/**
 *
 */
public class ColorPropertyRow extends NumericDataPropertyRow {
    private Color color;

    public ColorPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
    }

    @Override
    public Object GetObjectValue() {
        return this.color;
    }

    @Override
    public String CompileValue() {
        super.clear();
        this.color = Color.Empty;
        String name = super.formatName(super.getObjectNameMaybe());
        if (super.isInvalidObjectNameValue(null)) {
            if (super.getStringPropertyDefinitionInfo().RequiredValue()) {
                return EngineResources.ErrorRequiredValueNotSpecified;
            }
            return null;
        } else {
            if (super.method_31(name, ColorPropertyDefinition.class, 255)) {
                return null;
            }
            this.color = ColorOperator.smethod_0(name);
            if (this.color != Color.Empty) {
                return null;
            }
            return EngineResources.ErrorInvalidSyntax;
        }
    }

    public Color getColor(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (super.getReference() == null || super.getElementType() == ElementType.nullElement) {
            return this.color;
        }
        ColorPropertyRow colorPropertyRow = (ColorPropertyRow) super.getIntelligentObjectProperty(runSpace,
                intelligentObjectRunSpace,
                -1);
        if (colorPropertyRow != null) {
            return colorPropertyRow.color;
        }
        return this.color;
    }

    @Override
    public String StringValue() {
        if (this.color.IsKnownColor()) {
            return this.color.Name();
        }
        if (!this.color.IsEmpty()) {
            return String.format("{}, {}, {}", this.color.R, this.color.G, this.color.B);
        }
        return super.StringValue();
    }

    @Override
    public void StringValue(String value) {
        super.StringValue(value);
    }

    @Override
    protected ExpressionValue GetValueCore(IRunSpace runSpace,
                                           IntelligentObjectRunSpace intelligentObjectRunSpace,
                                           AbsBaseRunSpace absBaseRunSpace,
                                           IntelligentObjectRunSpace objectRunSpace, boolean param4) {
        return ExpressionValue.from((double) this.color.ToArgb());
    }

    @Override
    public GridItemProperty GetGridItemProperty(PropertyDefinitions definitions) {
        return new GridItemProperty(super.getStringPropertyDefinitionInfo().Name(),
                super.getStringPropertyDefinitionInfo().GetCategoryName(definitions),
                super.getStringPropertyDefinitionInfo().overRidePropertyIndex + 1000, this.StringValue(),
                super.getDefaultName(definitions), PropertyGridFeel.editlist,
                super.getStringPropertyDefinitionInfo().GetDisplayName(definitions),
                super.getStringPropertyDefinitionInfo().GetDescription(definitions),
                new SubPropertyOperator_0<>(String.class, this, this::StringValue,
                        this::StringValue, null, this::GetCandidatePropertyReferences));
    }

    @Override
    protected PropertyGridFeel Feel() {
        return PropertyGridFeel.color;
    }

}
