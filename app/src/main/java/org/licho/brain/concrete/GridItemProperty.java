package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.utils.simu.IPropertyOperator;

/**
 *
 */
public class GridItemProperty {

    public int indexVersion;
    public Class<?> type;
    private Object object_0;
    private IPropertyOperator propertyOperator;
    private String categoryName;
    private Object interface24_0;
    private int propertyIndex;
    private PropertyGridFeel propertyGridFeel;
    Object object_1;
    public String name;
    private String displayName;
    private GridItemProperty parent;
    private ProductComplexityLevel productComplexityLevel;

    private String description;
    private boolean initiallyExpanded;
    private boolean isRepeatingProperty;
    private boolean bool_4;
    Object property;
    private boolean readOnly;

    public GridItemProperty() {
    }

    public GridItemProperty(String name, String displayName, GridItemProperty parent, String description,
                            boolean initiallyExpanded) {
        this.name = name;
        this.displayName = displayName;
        this.parent = parent;
        this.description = description;
        this.initiallyExpanded = initiallyExpanded;
    }

    public GridItemProperty(String name, GridItemProperty param1, int propertyIndex, Object param3, Object param4,
                            PropertyGridFeel param5, String param6, IPropertyOperator param7) {
        this(name, null, propertyIndex, param3, param4, param5, null, param6, param7);
        this.parent = param1;
    }


    public GridItemProperty(String name, String displayName, int propertyIndex, Object param3, Object param4,
                            PropertyGridFeel param5, String param6, String param7, IPropertyOperator propertyOperator) {
        this.name = name;
        if (propertyOperator != null) {
            this.type = propertyOperator.PropertyType();
        }
        this.object_0 = param3;
        this.object_1 = param4;
        this.propertyGridFeel = param5;
        this.propertyIndex = propertyIndex;
        this.description = ((param7 != null) ? param7 : "");
        this.displayName = param6;
        this.interface24_0 = null;
        this.categoryName = displayName;
        this.propertyOperator = propertyOperator;
    }


    public String CategoryName() {
        return this.categoryName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public GridItemProperty Parent() {
        return parent;
    }


    public void Parent(GridItemProperty parent) {
        this.parent = parent;
    }

    public ProductComplexityLevel ComplexityLevel() {

        return this.productComplexityLevel;
    }

    public void ComplexityLevel(ProductComplexityLevel value) {
        this.productComplexityLevel = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getInitiallyExpanded() {
        return initiallyExpanded;
    }

    public void setInitiallyExpanded(boolean initiallyExpanded) {
        this.initiallyExpanded = initiallyExpanded;
    }

    public void setRepeatingProperty(boolean value) {
        this.isRepeatingProperty = value;

    }

    public IPropertyOperator getPropertyOperator() {
        return this.propertyOperator;
    }

    public String DisplayName() {
        if (Strings.isNullOrEmpty(this.displayName)) {
            return this.name;
        }
        return this.displayName;
    }

    public void DisplayName(String value) {
        this.displayName = value;
    }

    public String getErrorText() {
        if (this.propertyOperator != null) {
            return this.propertyOperator.ErrorText();
        }
        return null;
    }

    public boolean method_6() {
        return this.bool_4;
    }

    public void method_7(boolean value) {
        this.bool_4 = value;
    }

    public boolean method_13() {
        return (this.object_1 == null && this.property == null) || (this.object_1 != null && this.object_1.equals(this.property));
    }

    public boolean ReadOnly() {
        return this.readOnly;
    }

    public void ReadOnly(boolean value) {
        this.readOnly = value;
    }

    public PropertyGridFeel getPropertyGridFeel() {
        return this.propertyGridFeel;
    }

    public boolean getIsRepeatingProperty() {
        return this.isRepeatingProperty;
    }

    public void setIsRepeatingProperty(boolean param0) {
        this.isRepeatingProperty = param0;
    }
}
