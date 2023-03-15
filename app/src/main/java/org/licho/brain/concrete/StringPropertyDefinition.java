package org.licho.brain.concrete;

import com.google.common.base.Strings;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.Modifier;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.licho.brain.annotations.PropertyDefinitionFactory;
import org.licho.brain.api.IPropertyDefinition;
import org.licho.brain.brainEnums.SwitchNumericConditions;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;
import org.licho.brain.enu.Enum13;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.resource.Image;
import org.licho.brain.utils.simu.IReferencedObjects;
import org.licho.brain.utils.simu.NameDefinitionFunctionOperator;

import java.lang.reflect.InvocationTargetException;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

@Builder
@Slf4j
@AllArgsConstructor
@NoArgsConstructor
public class StringPropertyDefinition implements INotifyPropertyChanged,
        IGridObject,
        IAutoComplete,
        IOwner,
        ISearch,
        IItemDescriptor,
        IPropertyDefinition,
        IPropertyDefinitionFacade,
        IListener {

    private static Map<String, Function<String, StringPropertyDefinition>> nameDefinitionFuncMap;
    private static Map<Class<?>, String> typeToName = new HashMap<>();

    private String name;
    private String description;
    private boolean canDeleted;
    private SwitchNumericConditions switchNumericConditions;
    public PropertyDefinitions owner;
    private boolean isTableProperty;

    protected String defaultString;
    public DataFormat dataFormat;
    private String categoryName;
    private String nullString;
    private StringPropertyDefinition parentProperty;
    private String deafultDarentPropertyName;
    private NumericDataPropertyDefinition switchNumericProperty;
    private String switchNumericPropertyName;
    private final List<Double> switchNumericValue = new ArrayList<>();
    private boolean canReferenceParent;
    private String displayName;
    private boolean isVisible;
    private boolean mayIsChanging;
    private boolean isRequiredValue;
    private boolean isCategoryExpanded;
    private boolean isInitiallyExpanded;
    private ProductComplexityLevel productComplexityLevel;
    private boolean isObsoleteProperty;
    public int overRidePropertyIndex;

    @PropertyDefinitionName("ObjectInstanceProperty")
    public StringPropertyDefinition(String name) {
        this.name = name;
        this.defaultString = "";
        this.canDeleted = true;
        this.isTableProperty = false;
        this.dataFormat = DataFormat.String;
        this.switchNumericConditions = SwitchNumericConditions.Equal;
        this.description = "";
    }

    public StringPropertyDefinition(String name, String defaultDefaultString) {
        this(name);
        this.defaultString = defaultDefaultString;
    }

    public StringPropertyDefinition(String name, String defaultDefaultString, String description) {
        this(name, defaultDefaultString);
        this.Description(description);
    }

    public static StringPropertyDefinition getStringPropertyDefinition(String name, IIdentityName identityName) {

        var func = StringPropertyDefinition.getNameDefinitionFuncMap().get(name);
        if (func != null) {
            return func.apply(identityName.GetUniqueName("Property"));
        }
        return null;
    }

    public static synchronized Map<String, Function<String, StringPropertyDefinition>> getNameDefinitionFuncMap() {
        Map<String, Function<String, StringPropertyDefinition>> result;
        if (StringPropertyDefinition.nameDefinitionFuncMap == null) {
            StringPropertyDefinition.nameDefinitionFuncMap = new ConcurrentHashMap<>();
            StringPropertyDefinition.processNameDefinitionFunctions(StringPropertyDefinition.class);
            AdditionFunctionOperator.process((Class<?> t) -> {
                if (t != Missing.class) {
                    StringPropertyDefinition.processNameDefinitionFunctions(t);
                }
            });
        }
        result = StringPropertyDefinition.nameDefinitionFuncMap;
        return result;
    }

    @SuppressWarnings("NonRuntimeAnnotation")
    private static void processNameDefinitionFunctions(Class<?> cl) {
        NameDefinitionFunctionOperator nameDefinitionFunctionOperator = null;
        var propertyDefinitionFactoryAttribute = cl.getAnnotation(PropertyDefinitionFactory.class);
        if (propertyDefinitionFactoryAttribute != null) {
            try {
                nameDefinitionFunctionOperator =
                        (NameDefinitionFunctionOperator) propertyDefinitionFactoryAttribute.value().getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                log.error("annotation type error");
            }
        }

        if (nameDefinitionFunctionOperator == null) {
            nameDefinitionFunctionOperator = new StringPropertyDefinition.InnerNameDefinitionFunctionOperator(cl);
        }


    }

    public String NullNullString() {
        return this.nullString;
    }

    public void NullNullString(String value) {
        this.nullString = value;
        this.propertyChangedEventHandler("NullString");
    }

    public String setParentPropertyName() {
        if (this.parentProperty != null) {
            return this.deafultDarentPropertyName;
        }
        return this.parentProperty.Name();
    }

    public void setParentPropertyName(String value) {
        // TODO: 2021/11/16
    }

    public StringPropertyDefinition ParentPropertyDefinition() {
        return this.parentProperty;
    }

    public void ParentPropertyDefinition(StringPropertyDefinition value) {
        if (value == null || this.predicateAboutParent(value)) {
            this.parentProperty = value;
            this.deafultDarentPropertyName = ((this.parentProperty != null) ? this.parentProperty.Name() : "");
            this.propertyChangedEventHandler("ParentProperty");
            this.propertyChangedEventHandler("ParentPropertyName");
        }
    }

    public String SwitchNumericPropertyName() {
        if (this.switchNumericProperty == null) {
            return this.switchNumericPropertyName;
        }
        return this.switchNumericProperty.Name();
    }

    public void SwitchNumericPropertyName(String name) {

    }

    public NumericDataPropertyDefinition SwitchNumericProperty() {
        return this.switchNumericProperty;
    }

    public void SwitchNumericProperty(NumericDataPropertyDefinition value) {
        this.switchNumericProperty = value;
        this.propertyChangedEventHandler("SwitchNumericProperty");
    }

    public double SwitchNumericValue() {
        if (!this.switchNumericValue.isEmpty()) {
            return this.switchNumericValue.get(0);
        }
        return -1.0;
    }

    public void SwitchNumericValue(double value) {
        if (this.switchNumericValue.isEmpty()) {
            this.switchNumericValue.add(value);
        } else {
            this.switchNumericValue.set(0, value);
        }
        this.propertyChangedEventHandler("SwitchNumericValue");
    }

    public SwitchNumericConditions SwitchNumericCondition() {
        return this.switchNumericConditions;
    }

    public void SwitchNumericCondition(SwitchNumericConditions value) {
        this.switchNumericConditions = value;
        this.propertyChangedEventHandler("SwitchNumericCondition");
    }

    public boolean CanBeDeleted() {
        return this.canDeleted;
    }

    public void CanBeDeleted(boolean value) {
        this.canDeleted = value;
        this.propertyChangedEventHandler("CanBeDeleted");
    }

    public boolean CanReferenceParent() {
        return this.canReferenceParent;
    }

    public void CanReferenceParent(boolean value) {
        this.canReferenceParent = value;
        this.propertyChangedEventHandler("CanReferenceParent");
    }


    public String CategoryName() {
        return this.categoryName;
    }

    public void CategoryName(String value) {
        this.categoryName = value;
        this.propertyChangedEventHandler("CategoryName");
    }

    public boolean CanReferenceInputParameter() {
        return false;
    }

    public boolean CanDisplayInPropertyGrid() {
        return true;
    }

    public boolean IsTableProperty() {
        return this.isTableProperty;
    }

    public void IsTableProperty(boolean value) {
        this.isTableProperty = value;
        this.propertyChangedEventHandler("IsTableProperty");
    }

    public String DefaultString() {
        return this.defaultString;
    }

    public void DefaultString(String value) {
        String param = this.defaultString;
        this.defaultString = value;
        this.propertyChangedEventHandler("DefaultString");
        if (this.owner != null) {
            this.owner.PropertyDefaultChanged(this, this.owner, param, this.defaultString);
        }
    }

    private boolean predicateAboutParent(StringPropertyDefinition value) {
        // TODO: 2021/11/16
        return false;
    }

    public int getOverRidePropertyIndex() {
        return overRidePropertyIndex;
    }

    public void setOverRidePropertyIndex(int overRidePropertyIndex) {
        this.overRidePropertyIndex = overRidePropertyIndex;
    }

    public boolean QualifyAsValidReference(StringPropertyDefinition stringPropertyDefinition) {
        return true;
    }

    public IntelligentObjectProperty CreateInstance(Properties properties) {
        return new IntelligentObjectProperty(this, properties);

    }

    public boolean isTableProperty() {
        return this.isTableProperty;
    }

    public void setTableProperty(boolean isTableProperty) {
        this.isTableProperty = isTableProperty;
    }

    public void setDefaultString(String defaultString) {
        String value = this.defaultString;
        this.propertyChangedEventHandler("DefaultString");
        if (this.owner != null) {
            this.owner.PropertyDefaultChanged(this, owner, value, this.defaultString);
        }
    }

    @Override
    public String Description() {
        return this.description;
    }

    @Override
    public void Description(String value) {
        this.description = value;
        this.propertyChangedEventHandler("Description");
    }

    @Override
    public String Name() {
        return this.name;
    }

    public void Name(String value) {
        String param = this.name;
        this.name = value;
        this.propertyChangedEventHandler("Name");
        if (this.getOwnerGridObjectDefinition() != null) {
            this.getOwnerGridObjectDefinition().NotifyPropertyDefinitionRenamed(this, param);
        }
    }

    @Override
    public String DisplayName() {
        if (!Strings.isNullOrEmpty(this.displayName)) {
            return this.displayName;
        }
        return this.name;
    }

    @Override
    public void DisplayName(String value) {
        this.displayName = value;
        this.propertyChangedEventHandler("DisplayName");
    }

    public String FormattedDisplayName() {
        return this.DisplayName();
    }

    public boolean Visible() {
        return this.isVisible;
    }

    public void Visible(boolean value) {
        this.isVisible = value;
        this.mayIsChanging = true;
        try {
            this.propertyChangedEventHandler("Visible");
        } finally {
            this.mayIsChanging = false;
        }
    }

    public boolean isMayChanging() {
        return this.mayIsChanging;
    }

    public boolean RequiredValue() {
        return this.isRequiredValue;
    }

    public void RequiredValue(boolean value) {
        this.isRequiredValue = value;
        this.propertyChangedEventHandler("RequiredValue");
        if (this.owner != null) {
            // TODO: 2021/11/16
//				this.owner.method_18(this.owner, this);
        }
    }


    public boolean CategoryExpanded() {
        return this.isCategoryExpanded;
    }

    public void CategoryExpanded(boolean value) {
        this.isCategoryExpanded = value;
        this.propertyChangedEventHandler("CategoryExpanded");
    }

    public boolean InitiallyExpanded() {
        return this.isInitiallyExpanded;
    }

    public void InitiallyExpanded(boolean value) {
        this.isInitiallyExpanded = value;
        this.propertyChangedEventHandler("InitiallyExpanded");
    }

    public ProductComplexityLevel ComplexityLevel() {
        return this.productComplexityLevel;
    }

    public void ComplexityLevel(ProductComplexityLevel value) {
        this.productComplexityLevel = value;
        // TODO: 2021/11/16
//        this.propertyChangedEventHandler(MemberExpressionUtils<StringPropertyDefinition>
//        .getMemberExpressionMemberName<ProductComplexityLevel> ((StringPropertyDefinition
//        p) =>p.ComplexityLevel));

    }

    public void setException(String param0) {

    }


    @Override
    public Object Item() {
        return this;
    }

    @Override
    public String Group() {
        StringBuilder text = new StringBuilder("Properties");
        StringPropertyDefinition stringPropertyDefinition = this;
        while (stringPropertyDefinition.owner != null && stringPropertyDefinition.owner.RepeatingPropertyDefinition != null) {
            text.insert(0, stringPropertyDefinition.owner.RepeatingPropertyDefinition.Name() + ".");
            stringPropertyDefinition = stringPropertyDefinition.owner.RepeatingPropertyDefinition;
        }
        return text.toString();
    }

    @Override
    public int GroupImportance() {
        return 0;
    }


    @Override
    public String ObjectType() {
        return this.getObjectClassName();
    }

    @Override
    public String Category() {
        return this.CategoryName();
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public int StateIconIndex() {
        return 0;
    }

    public String getCategory() {
        if (this.Category() != null) {
            return this.Category();
        }
        if (this.ParentPropertyDefinition() != null) {
            for (StringPropertyDefinition parentPropertyDefinition = this.ParentPropertyDefinition();
                 parentPropertyDefinition != null;
                 parentPropertyDefinition = parentPropertyDefinition.ParentPropertyDefinition()) {
                if (parentPropertyDefinition.Category() != null) {
                    return parentPropertyDefinition.Category();
                }
            }
        }
        return null;
    }

    public void GetAutoCompleteChoices(List<CompleteChoice> results, Enum13 enum13,
                                       IntelligentObjectDefinition intelligentObjectDefinition, boolean param3) {
        CompleteChoice hasValue = new CompleteChoice(TableAttribute.HasValue, 2,
                EngineResources.DescriptionFor_PropertyFunction_HasValue);
        hasValue.method_4(true);
        results.add(hasValue);
        if (this.IsTableProperty()) {
            CompleteChoice timeIndexedValue = new CompleteChoice(TableAttribute.TimeIndexedValue, 2,
                    EngineResources.DescriptionFor_TablePropertyFunction_TimeIndexedValue);
            timeIndexedValue.method_4(true);
            results.add(timeIndexedValue);
            CompleteChoice randomValue = new CompleteChoice(TableAttribute.RandomValue, 2,
                    EngineResources.DescriptionFor_TablePropertyFunction_RandomValue);
            randomValue.method_4(true);
            results.add(randomValue);
        }
    }

    public IAutoComplete GetAutoCompleteObject(String name, IntelligentObjectDefinition intelligentObjectDefinition) {
        return null;
    }


    IValueProvider ValueProvider() {
        return null;
    }

    ;

    public AbsDefinition ElementReferenceValueType() {
        return null;
    }

    public boolean ElementReferenceValueIsAnyElement() {
        return false;
    }

    public boolean ElementReferenceValueIsIndexed() {
        return false;
    }

    public Class<?> NativeObjectType() {
        return String.class;
    }

    public TypeConverter NativeObjectTypeConverter() {
        return null;
    }

    public void CopyFrom(StringPropertyDefinition stringPropertyDefinition) {
        this.NullNullString(stringPropertyDefinition.NullNullString());
        this.DefaultString(stringPropertyDefinition.DefaultString());
        this.RequiredValue(stringPropertyDefinition.RequiredValue());
    }

    public void CopyFrom(IntelligentObjectProperty intelligentObjectProperty) {
    }


    public boolean isObsoleteProperty() {
        return this.isObsoleteProperty;
    }

    public void setIsObsoleteProperty(boolean isObsoleteProperty) {
        this.isObsoleteProperty = isObsoleteProperty;
    }

    private GridObjectDefinition getOwnerGridObjectDefinition() {
        if (this.owner != null) {
            return this.owner.TargetObject;
        }
        return null;
    }

    protected void propertyChangedEventHandler(String value) {
        // TODO: 2021/11/11
    }

    public void SetLocalVisible(Boolean visible, PropertyDefinitions propertyGroup) {
        if (propertyGroup == this.owner && visible != null) {
            this.Visible(visible);
            propertyGroup.overrides.get(this.overRidePropertyIndex).Visible(null);
        } else {
            propertyGroup.overrides.get(this.overRidePropertyIndex).Visible(visible);
        }
        this.propertyChangedEventHandler("Visible");
    }

    public String GetDefaultStringBy(PropertyDefinitions propertyGroup) {
        return this.GetDefaultStringBy(propertyGroup, ReplacementInfo.replacementInfo);
    }

    public String GetDefaultStringBy(PropertyDefinitions propertyGroup,
                                     ReplacementInfo replacementInfo) {
        OverrideObject overrideObject = this.getOverride(this.overRidePropertyIndex, propertyGroup);
        while (overrideObject != null) {
            if (overrideObject.Default() != null) {
                return overrideObject.Default();
            }
            if (replacementInfo.prePropertyDefinitions == null ||
                    overrideObject.overrideObject == null ||
                    replacementInfo.prePropertyDefinitions != overrideObject.overrideObject.getPropertyDefinitions()) {
                overrideObject = overrideObject.overrideObject;
            } else {
                overrideObject = this.getOverride(this.overRidePropertyIndex, replacementInfo.afterPropertyDefinitions);
            }
        }
        return this.DefaultString();
    }

    public GridItemProperty GetGridItemProperties(PropertyDefinitions propertyDefinitions) {
        return new GridItemProperty(this.Name(), this.GetCategoryName(propertyDefinitions), this.overRidePropertyIndex,
                this.GetDefaultStringBy(propertyDefinitions), "", PropertyGridFeel.none,
                this.GetDisplayName(propertyDefinitions), this.GetDescription(propertyDefinitions),
                new PropertyOperator_0<>(String.class, this::DefaultString,
                        this::setDefaultString));
    }

    public String GetDisplayName(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverride(this.overRidePropertyIndex, propertyGroup); overrideObject != null;
             overrideObject = overrideObject.overrideObject) {
            if (overrideObject.DisplayName() != null) {
                return overrideObject.DisplayName();
            }
        }
        return this.DisplayName();
    }

    public void UpdateGridPropertyValue(int param0, Object param1) {
    }

    private OverrideObject getOverride(int index, PropertyDefinitions propertyDefinitions) {
        if (index >= 0 && propertyDefinitions != null && index < propertyDefinitions.overrides.size()) {
            return propertyDefinitions.overrides.get(index);
        }
        return null;
    }

    public boolean IsVisible(PropertyDefinitions propertyGroup) {
        for (OverrideObject classOverrideObject = this.getOverride(this.overRidePropertyIndex, propertyGroup);
             classOverrideObject != null; classOverrideObject = classOverrideObject.OverrideObject()) {
            if (classOverrideObject.Visible() != null) {
                return classOverrideObject.Visible();
            }
        }
        return this.Visible();
    }

    public void SetLocalDescription(String localString, PropertyDefinitions propertyGroup) {
        if (propertyGroup == this.owner) {
            this.Description(localString);
            propertyGroup.overrides.get(this.overRidePropertyIndex).Description(null);
            return;
        }
        propertyGroup.overrides.get(this.overRidePropertyIndex).Description(localString);
    }


    boolean isRepeatingPropertyDefinition() {
        return this.owner.RepeatingPropertyDefinition != null;
    }

    protected GridItemProperties method_2(GridItemProperties gridItemProperties,
                                          GridObjectDefinition gridObjectDefinition) {
        GridItemProperty general = new GridItemProperty(EngineResources.CategoryName_General, null, null, null, true);
        GridItemProperty value = new GridItemProperty(EngineResources.CategoryName_Value, null, null, null, true);
        gridItemProperties.add(general);
        gridItemProperties.add(value);
        gridItemProperties.add(new GridItemProperty(EngineResources.Property_NameName,
                general,
                501,
                this.Name(),
                null,
                PropertyGridFeel.none,
                EngineResources.Property_NameDescription,
                new PropertyOperator_0<>(String.class, this::Name, this::Name, this::CanRenameTo)));

        PropertyDefinitions groupDefinition = null;
        if (gridObjectDefinition != null) {
            groupDefinition = gridObjectDefinition.GetPropertyDefinitions(this.owner.propertyGroupClass);
        }
        if (this.owner.RepeatingPropertyDefinition != null) {
            groupDefinition = this.owner;
        }


        var tmp = groupDefinition;


        GridItemProperty descriptionName = new GridItemProperty(EngineResources.Property_DefaultValueName,
                value,
                -3,
                this.GetDefaultStringBy(groupDefinition),
                "",
                this.ValueProvider() != null ? PropertyGridFeel.editlist : PropertyGridFeel.none,
                EngineResources.Property_DefaultValueDescription,
                new PropertyOperator_0<String>(String.class, () ->
                {
                    if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                        return this.DefaultString();
                    }
                    return this.GetDefaultStringBy(tmp);
                },
                        newValue -> {
                            if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                                this.DefaultString(newValue);
                                return;
                            }
                            this.SetLocalDefaultString(newValue, tmp);
                        },
                        () -> {
                            if (this.ValueProvider() != null) {
                                return this.ValueProvider().getValue(null);
                            }
                            return null;
                        }));

        if (!this.isRepeatingPropertyDefinition()) {
            descriptionName.setRepeatingProperty(true);
        }

        gridItemProperties.add(descriptionName);
        GridItemProperty appearance = new GridItemProperty(EngineResources.CategoryName_Appearance, null, null, null,
                true);
        gridItemProperties.add(appearance);

        descriptionName = new GridItemProperty(EngineResources.Property_DisplayNameName,
                appearance,
                517,
                this.GetDisplayName(groupDefinition),
                "",
                PropertyGridFeel.none,
                EngineResources.Property_DisplayNameDescription,
                new PropertyOperator_0<>(
                        String.class,
                        () -> {
                            if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                                return this.DisplayName();
                            }
                            return this.GetDisplayName(tmp);
                        }, newValue ->
                {
                    if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                        this.DisplayName(newValue);
                        return;
                    }
                    this.SetLocalDisplayName(newValue, tmp);
                }));

        if (!this.isRepeatingPropertyDefinition()) {
            descriptionName.setRepeatingProperty(true);
        }

        gridItemProperties.add(descriptionName);
        gridItemProperties.add(new GridItemProperty(EngineResources.Property_RequiredValueName, general, -4,
                this.RequiredValue(), true, PropertyGridFeel.none, EngineResources.Property_RequiredValueDescription,
                new PropertyOperator_0<>(Boolean.class,
                        this::RequiredValue,
                        this::RequiredValue)));

        if (!this.IsTableProperty()) {
            descriptionName = new GridItemProperty(EngineResources.Property_CategoryNameName,
                    appearance,
                    -5,
                    this.GetCategoryName(groupDefinition),
                    EngineResources.CategoryName_General,
                    PropertyGridFeel.editlist,
                    EngineResources.Property_CategoryNameDescription,
                    new PropertyOperator_0<>(String.class,
                            () -> {
                                if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                                    return this.CategoryName();
                                }
                                return this.GetCategoryName(tmp);
                            },
                            newValue -> {
                                if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                                    this.CategoryName(newValue);
                                    return;
                                }
                                this.SetLocalCategory(newValue, tmp);
                            }, tmp::getCategoriesArray));

            if (!this.isRepeatingPropertyDefinition()) {
                descriptionName.setRepeatingProperty(true);
            }

            gridItemProperties.add(descriptionName);
            String expandedName = EngineResources.Property_CategoryExpandedName;
            GridItemProperty gridItemProperty = appearance;
            int indexVersion = -6;
            Object categoryExpanded = this.CategoryExpanded();
            Object param5 = false;
            PropertyGridFeel propertyGridFeel = PropertyGridFeel.none;
            String expandedDescription = EngineResources.Property_CategoryExpandedDescription;

            gridItemProperties.add(new GridItemProperty(expandedName,
                    gridItemProperty,
                    indexVersion,
                    categoryExpanded,
                    param5,
                    propertyGridFeel,
                    expandedDescription,
                    new PropertyOperator_0<>(Boolean.class,
                            this::CategoryExpanded,
                            this::CategoryExpanded)));

            boolean isVisible = this.Visible();
            if (gridObjectDefinition != null) {
                isVisible = this.IsVisible(groupDefinition);
            }
            descriptionName = new GridItemProperty("Visible",
                    general,
                    -8,
                    isVisible,

                    true, PropertyGridFeel.none,
                    "",
                    new PropertyOperator_0<>(Boolean.class,
                            () -> {
                                if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                                    return this.Visible();
                                }
                                return this.IsVisible(tmp);
                            }, newValue ->
                    {
                        if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                            this.Visible(newValue);
                            return;
                        }
                        this.SetLocalVisible(newValue, tmp);
                    }));

            if (!this.isRepeatingPropertyDefinition()) {
                descriptionName.setRepeatingProperty(true);
            }
            gridItemProperties.add(descriptionName);

            descriptionName = new GridItemProperty(EngineResources.Property_SwitchPropertyNameName,
                    value,
                    510,
                    this.GetSwitchNumericPropertyName(groupDefinition),
                    "",
                    PropertyGridFeel.list, EngineResources.Property_SwitchPropertyNameDescription,
                    new PropertyOperator_0<>(String.class, () ->
                    {
                        if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                            return this.SwitchNumericPropertyName();
                        }
                        return this.GetSwitchNumericPropertyName(tmp);
                    }, newValue ->
                    {
                        if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                            this.SwitchNumericPropertyName(newValue);
                            return;
                        }
                        this.SetLocalSwitchNumericPropertyName(newValue, tmp);
                    }, null, () -> this.method_14(tmp),
                            () -> this.GetSwitchNumericPropertyNameError(tmp), null, null));

            if (!this.isRepeatingPropertyDefinition()) {
                descriptionName.setRepeatingProperty(true);
            }
            gridItemProperties.add(descriptionName);
            descriptionName = new GridItemProperty(EngineResources.Property_ComplexityLevelName,
                    general,
                    -9,
                    this.ComplexityLevel() == ProductComplexityLevel.Basic,
                    true,
                    PropertyGridFeel.list,
                    EngineResources.Property_ComplexityLevelDescription,
                    new PropertyOperator_0<>(Boolean.class, () ->
                    {
                        if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                            return this.ComplexityLevel() == ProductComplexityLevel.Basic;
                        }
                        return this.GetComplexityLevel(tmp) == ProductComplexityLevel.Basic;
                    }, newValue ->
                    {
                        ProductComplexityLevel productComplexityLevel = newValue ? ProductComplexityLevel.Basic :
                                ProductComplexityLevel.Advanced;
                        if (gridObjectDefinition == null || this.IsOwnedBy(gridObjectDefinition) || this.isRepeatingPropertyDefinition()) {
                            this.ComplexityLevel(productComplexityLevel);
                            return;
                        }
                        this.SetLocalComplexityLevel(productComplexityLevel, tmp);
                    }));
            if (!this.isRepeatingPropertyDefinition()) {
                descriptionName.setRepeatingProperty(true);
            }
            gridItemProperties.add(descriptionName);
        }
        return gridItemProperties;
    }

    public void SetLocalComplexityLevel(ProductComplexityLevel localComplexityLevel,
                                        PropertyDefinitions propertyGroup) {
        if (propertyGroup == this.owner && localComplexityLevel != null) {
            this.ComplexityLevel(localComplexityLevel);
            propertyGroup.overrides.get(this.overRidePropertyIndex).ComplexityLevel(null);
            return;
        }
        propertyGroup.overrides.get(this.overRidePropertyIndex).ComplexityLevel(localComplexityLevel);
    }


    public String GetSwitchNumericPropertyNameError(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverrideObject(this.overRidePropertyIndex, propertyGroup);
             overrideObject != null;
             overrideObject = overrideObject.overrideObject) {
            String text = overrideObject.getErrorInvalidSwitchProperty();
            if (text != null) {
                return text;
            }
        }
        return this.getErrorInvalidSwitchProperty();
    }

    private String getErrorInvalidSwitchProperty() {
        if (this.switchNumericProperty == null) {
            if (!Strings.isNullOrEmpty(this.switchNumericPropertyName)) {
                return EngineResources.Error_InvalidSwitchPropertyName;
            }
        } else if (!this.switchNumericProperty.vmethod_0()) {
            return EngineResources.Error_InvalidSwitchPropertyType;
        }
        return null;
    }

    private String[] method_14(PropertyDefinitions propertyDefinitions) {
        // TODO: 2021/12/9
        return new String[0];
    }


    public void SetLocalSwitchNumericPropertyName(String localNumericPropertyName, PropertyDefinitions propertyGroup) {
        if (propertyGroup == this.owner) {
            this.SwitchNumericPropertyName(localNumericPropertyName);
            propertyGroup.overrides.get(this.overRidePropertyIndex).SwitchNumericProperty(null);
            return;
        }
        propertyGroup.overrides.get(this.overRidePropertyIndex).SwitchNumericPropertyName(localNumericPropertyName);
    }

    public String GetSwitchNumericPropertyName(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverrideObject(this.overRidePropertyIndex, propertyGroup); overrideObject != null; overrideObject = overrideObject.OverrideObject()) {
            if (overrideObject.SwitchNumericPropertyName() != null) {
                return overrideObject.SwitchNumericPropertyName();
            }
        }
        return this.SwitchNumericPropertyName();
    }

    public void SetLocalCategory(String localString, PropertyDefinitions propertyGroup) {
        if (propertyGroup == this.owner) {
            this.CategoryName(localString);
            propertyGroup.overrides.get(this.overRidePropertyIndex).Category(null);
            return;
        }
        propertyGroup.overrides.get(this.overRidePropertyIndex).Category(localString);
    }

    private OverrideObject getOverrideObject(int index, PropertyDefinitions propertyDefinitions) {
        if (index >= 0 && propertyDefinitions != null && index < propertyDefinitions.overrides.size()) {
            return propertyDefinitions.overrides.get(index);
        }
        return null;
    }


    public void SetLocalDisplayName(String localString, PropertyDefinitions propertyGroup) {
        if (propertyGroup == this.owner) {
            this.DisplayName(localString);
            propertyGroup.overrides.get(this.overRidePropertyIndex).DisplayName(null);
        } else {
            propertyGroup.overrides.get(this.overRidePropertyIndex).DisplayName(localString);
        }
        this.propertyChangedEventHandler("DisplayName");
    }

    void SetLocalDefaultString(String localString, PropertyDefinitions propertyGroup) {
        if (propertyGroup == this.owner) {
            this.DefaultString(localString);
            propertyGroup.overrides.get(this.overRidePropertyIndex).Default(null);
            return;
        }
        propertyGroup.overrides.get(this.overRidePropertyIndex).Default(localString);
    }

    public String GetPropertyValueFixup(IntelligentObjectXml intelligentObjectXml, String value) {
        return value;
    }

    protected GridItemProperties getGridPropertyItemList(GridItemProperties gridItemProperties,
                                                         GridObjectDefinition gridObjectDefinition) {
        // TODO: 2022/1/28 
        return null;
    }

    protected void triggerChangeName(String value) {
        this.propertyChangedEventHandler("Group");
    }

    public StringPropertyDefinition GetSwitchNumericProperty(PropertyDefinitions propertyGroup) {
        for (OverrideObject classOverrideObject = this.getOverrideObject(this.overRidePropertyIndex, propertyGroup); classOverrideObject != null; classOverrideObject = classOverrideObject.overrideObject) {
            if (classOverrideObject.SwitchNumericProperty() != null) {
                return classOverrideObject.SwitchNumericProperty();
            }
        }
        return this.SwitchNumericProperty();
    }

    public SwitchNumericConditions GetSwitchNumericCondition(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverrideObject(this.overRidePropertyIndex, propertyGroup); overrideObject != null; overrideObject = overrideObject.overrideObject) {
            if (overrideObject.SwitchNumericCondition() != null) {
                return overrideObject.SwitchNumericCondition();
            }
        }
        return this.SwitchNumericCondition();
    }

    public List<Double> GetSwitchNumericValues(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverrideObject(this.overRidePropertyIndex, propertyGroup); overrideObject != null; overrideObject = overrideObject.overrideObject) {
            List<Double> enumerable = overrideObject.getSwitchNumericValues();
            if (!enumerable.isEmpty()) {
                return enumerable;
            }
        }
        return this.getSwitchNumericValue();
    }

    private List<Double> getSwitchNumericValue() {
        return this.switchNumericValue;
    }

    protected boolean IsSamePropertyType(StringPropertyDefinition stringPropertyDefinition) {
        return stringPropertyDefinition.getClass() == super.getClass();
    }

    public enum Enum17 {
        Zero,
        One
    }

    public static class ReplacementInfo {
        public ReplacementInfo() {
        }

        public ReplacementInfo(PropertyDefinitions prePropertyDefinitions,
                               PropertyDefinitions afterPropertyDefinitions) {
            this.prePropertyDefinitions = prePropertyDefinitions;
            this.afterPropertyDefinitions = afterPropertyDefinitions;
        }

        public PropertyDefinitions prePropertyDefinitions;

        public PropertyDefinitions afterPropertyDefinitions;

        public static final StringPropertyDefinition.ReplacementInfo replacementInfo = new ReplacementInfo();
    }

    @Override
    public String getObjectClassName() {
        return EngineResources.Property_ClassName;
    }

    @Override
    public String getObjectDescription() {
        return EngineResources.Property_ClassDescription;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return this.Name();
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        return this.method_2(gridItemProperties, gridObjectDefinition);
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        return new String[0];
    }


    @Override
    public boolean Required() {
        return this.isRequiredValue;
    }

    @Override
    public void Required(boolean value) {
        this.isRequiredValue = value;
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

    public boolean IsOwnedBy(GridObjectDefinition gridObjectDefinition) {
        return this.owner.getTargetObject() == gridObjectDefinition;
    }

    public PropertyDefinitions getOwner() {
        return owner;
    }

    public void setOwner(PropertyDefinitions owner) {
        this.owner = owner;
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
        if (param0 == ItemEditPolicy.name) {
            return "Name";
        }
        if (param0 == ItemEditPolicy.description) {
            return "Description";
        }
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {
// TODO: 2021/12/20
    }

    public String GetCategoryName(PropertyDefinitions propertyGroup) {
        String categoryCore = this.GetCategoryCore(propertyGroup);
        if (Strings.isNullOrEmpty(categoryCore)) {
            return EngineResources.CategoryName_General;
        }
        return categoryCore;
    }

    public String GetCategoryCore(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverride(this.overRidePropertyIndex, propertyGroup);
             overrideObject != null;
             overrideObject = overrideObject.overrideObject) {
            if (overrideObject.Category() != null) {
                return overrideObject.Category();
            }
        }
        return this.CategoryName();
    }

    public String GetDescription(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverride(this.overRidePropertyIndex, propertyGroup);
             overrideObject != null;
             overrideObject = overrideObject.overrideObject) {
            if (overrideObject.Description() != null) {
                return overrideObject.Description();
            }
        }
        return this.Description();
    }

    protected void WriteXmlAttributes(XmlWriter xmlWriter, PropertyDefinitions propertyDefinitions) {
    }

    protected void ReadXmlAttributes(XmlReader xmlReader, PropertyDefinitions propertyDefinitions) {

    }

    public static StringPropertyDefinition readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                                   PropertyDefinitions propertyDefinitions,
                                                   IIdentityName identityName, StringPropertyDefinition.Enum17 enum17) {
        StringPropertyDefinition stringPropertyDefinition;
        if (Objects.equals(xmlReader.Name(), "RepeatingProperty")) {
            stringPropertyDefinition = new RepeatingPropertyDefinition(identityName.GetUniqueName("Property"),
                    propertyDefinitions.TargetObject);
        } else {
            stringPropertyDefinition = StringPropertyDefinition.getStringPropertyDefinition(xmlReader.Name(),
                    identityName);
        }

        if (stringPropertyDefinition == null && !PropertyDefinitions.isValidNodeName(xmlReader.Name()) && enum17 == StringPropertyDefinition.Enum17.One) {
            intelligentObjectXml.addWarning(MessageFormat.format(EngineResources.LoadWarning_CouldNotFindProperty,
                    xmlReader.Name()));
            stringPropertyDefinition = new MissingPropertyDefinition(identityName.GetUniqueName("Property"));
        }
        if (stringPropertyDefinition != null) {
            propertyDefinitions.add(stringPropertyDefinition);
            stringPropertyDefinition.ReadFromXml(xmlReader, intelligentObjectXml, propertyDefinitions, identityName);
            return stringPropertyDefinition;
        }
        return null;
    }

    protected void ReadFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                               PropertyDefinitions propertyDefinitions, IIdentityName identityName) {
        String[] defaultValue = new String[1];
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, xmlReader.Name(), (XmlReader attr) ->
        {
            defaultValue[0] = this.readXmlAttribute(attr, intelligentObjectXml, propertyDefinitions, identityName);
            this.ReadXmlAttributes(attr, propertyDefinitions);
        }, (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "Switch", (XmlReader swattr) ->
        {
            SomeXmlOperator.readXmlAttributeString(swattr, "Property", this::SwitchNumericPropertyName);
            SomeXmlOperator.readEnumAttribute(swattr, "Comparison",
                    this::SwitchNumericCondition, SwitchNumericConditions.class);
            SomeXmlOperator.readXmlAttributeString(swattr, "Value", this::setException);
        }, null) ||
                this.ReadXmlBody(body, intelligentObjectXml, identityName));
        defaultValue[0] = this.LoadDefaultValue(defaultValue[0], intelligentObjectXml);
        if (!Strings.isNullOrEmpty(defaultValue[0])) {
            this.SetLocalDefaultString(defaultValue[0], propertyDefinitions);
            return;
        }
        this.SetLocalDefaultString("", propertyDefinitions);

    }

    public String LoadDefaultValue(String defaultValue, IntelligentObjectXml intelligentObjectXml) {
        return defaultValue;
    }

    protected boolean ReadXmlBody(XmlReader body, IntelligentObjectXml intelligentObjectXml,
                                  IIdentityName identityName) {
        return false;
    }


    protected String readXmlAttribute(XmlReader xmlReader
            , IntelligentObjectXml intelligentObjectXml, PropertyDefinitions propertyDefinitions,
                                      IIdentityName identityName) {
        String name = xmlReader.GetAttribute("Name");
        if (!Strings.isNullOrEmpty(name) && !this.Name().equals(name)) {
            if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.getOwnerIntelligentObjectFacility() != null) {
                this.Name(this.getOwnerIntelligentObjectFacility().GetUniqueName(name, false));
            } else {
                this.Name(name);
            }
        }
        String displayName = xmlReader.GetAttribute("DisplayName");
        if (!Strings.isNullOrEmpty(displayName)) {
            this.SetLocalDisplayName(displayName, propertyDefinitions);
        }
        String description = xmlReader.GetAttribute("Description");
        if (!Strings.isNullOrEmpty(description)) {
            this.SetLocalDescription(description, propertyDefinitions);
        }
        String visible = xmlReader.GetAttribute("Visible");
        if (visible.equalsIgnoreCase("False")) {
            this.SetLocalVisible(false, propertyDefinitions);
        }
        String obsolete = xmlReader.GetAttribute("Obsolete");
        if (obsolete.equalsIgnoreCase("True")) {
            this.setIsObsoleteProperty(true);
        }
        SomeXmlOperator.readEnumAttribute
                (xmlReader, "ComplexityLevel", this::ComplexityLevel, ProductComplexityLevel.class);
        String category = xmlReader.GetAttribute("Category");
        if (!Strings.isNullOrEmpty(category)) {
            this.SetLocalCategory(category, propertyDefinitions);
        }
        String categoryExpanded = xmlReader.GetAttribute("CategoryExpanded");
        if (categoryExpanded.equalsIgnoreCase("True")) {
            this.CategoryExpanded(true);
        }
        String nullString = xmlReader.GetAttribute("NullString");
        if (!Strings.isNullOrEmpty(nullString)) {
            this.NullNullString(nullString);
        }
        String defaultValue = xmlReader.GetAttribute("DefaultValue");
        String required = xmlReader.GetAttribute("Required");
        if (required.equalsIgnoreCase("False")) {
            this.RequiredValue(false);
        }
        String parent = xmlReader.GetAttribute("Parent");
        if (!Strings.isNullOrEmpty(parent)) {
            this.setParentPropertyName(parent);
        }
        return defaultValue;

    }


    private IntelligentObjectDefinition getOwnerIntelligentObjectFacility() {
        return this.owner == null ? null : (IntelligentObjectDefinition) this.owner.TargetObject;
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

    public ProductComplexityLevel GetComplexityLevel(PropertyDefinitions propertyGroup) {
        for (OverrideObject overrideObject = this.getOverride(this.overRidePropertyIndex, propertyGroup); overrideObject != null; overrideObject = overrideObject.OverrideObject()) {
            if (overrideObject.ComplexityLevel() != null) {
                return overrideObject.ComplexityLevel();
            }
        }
        return this.ComplexityLevel();
    }


    public static class Class36 {
        public final PropertyDefinitions prePropertyDefinitions;
        public final PropertyDefinitions afterPropertyDefinitions;

        protected Class36(PropertyDefinitions prePropertyDefinitions, PropertyDefinitions afterPropertyDefinitions) {
            this.prePropertyDefinitions = prePropertyDefinitions;
            this.afterPropertyDefinitions = afterPropertyDefinitions;
        }

        public static final Class36 instance = new Class36(null, null);
    }

    private static class InnerNameDefinitionFunctionOperator implements NameDefinitionFunctionOperator {
        private Class<?> type;
        private List<NameDefinitionFunction> nameDefinitionFunctions;


        public InnerNameDefinitionFunctionOperator(Class<?> cl) {
            this.type = cl;
        }

        @Override
        public List<NameDefinitionFunction> getNameDefinitionFunctions() {
            if (this.nameDefinitionFunctions == null) {
                String typeName = StringPropertyDefinition.getTypeName(this.type);

                // TODO: 2021/12/31 here maybe wrong

                this.nameDefinitionFunctions = new ArrayList<>();
                ClassPool pool = ClassPool.getDefault();
                try {
                    CtClass ctClass = pool.get(StringPropertyDefinition.class.getTypeName());
                    CtField ctFieldNew = new CtField(CtClass.voidType, typeName + "Factory", ctClass);
                    ctFieldNew.setModifiers(Modifier.PUBLIC);
                    ctClass.addField(ctFieldNew);
                    Class<StringPropertyDefinition> newClass = (Class<StringPropertyDefinition>) ctClass.toClass();
                    var tmp = new NameDefinitionFunction();
                    tmp.name = typeName;
                    tmp.func = t -> {
                        try {
                            return newClass.getConstructor().newInstance();
                        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                                 NoSuchMethodException e) {
                            e.printStackTrace();
                        }
                        return null;
                    };
                    this.nameDefinitionFunctions.clear();
                    this.nameDefinitionFunctions.add(tmp);
                } catch (NotFoundException | CannotCompileException e) {
                    e.printStackTrace();
                }
            }
            return this.nameDefinitionFunctions;

        }
    }

    @SuppressWarnings("NonRuntimeAnnotation")
    private static synchronized String getTypeName(Class<?> type) {
        String name = StringPropertyDefinition.typeToName.get(type);

        if (name == null) {
            PropertyDefinitionName propertyDefinitionNameAttribute = type.getAnnotation(PropertyDefinitionName.class);

            if (propertyDefinitionNameAttribute != null) {
                name = propertyDefinitionNameAttribute.value();
                StringPropertyDefinition.typeToName.put(type, name);
            }
        }

        if (name != null) {
            return name;
        }

        throw new IllegalArgumentException("Type does not have PropertyDefinitionName attribute" + "t");
    }
}

