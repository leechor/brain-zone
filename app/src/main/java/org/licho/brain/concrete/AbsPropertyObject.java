package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.annotations.Browsable;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.PropertyGroupClass;
import org.licho.brain.api.IPropertyObject;

import java.util.Objects;

/**
 *
 */
public abstract class AbsPropertyObject implements INotifyPropertyChanged, IPropertyObject, IGridObject,
        IAutoComplete, IOwner, ISearch, IPropertyDefaultValue, IReferenceProperty {

    private String description;
    private String name;

    public GridObjectDefinition objectDefinition;

    public IntelligentObjectDefinition parent;

    public Properties properties;

    public AbsPropertyObject(GridObjectDefinition definition, String name) {
        if (name == null) {
            definition.setCount(definition.getCount() + 1);
            name = definition.Name() + definition.getCount();
        }
        this.name = name;
        this.description = "";
        this.objectDefinition = definition;
        this.properties = new Properties(definition.getPropertyDefinitions(), this, null);
        if (!definition.IsJustAFactory()) {
            definition.getAssociatedInstances().add(this);
        }
    }


    public String ProjectItemName() {
        if (this.Parent() == null) {
            return null;
        }

        if (this.Parent().activeModel != null) {
            return this.Parent().activeModel.Name();
        }

        return this.Parent().Name();
    }

    public String ItemName() {
        return this.InstanceName();
    }

    public String ItemTypeName() {
        return this.GetGridObjectClassName();
    }

    public String GetNameForKey(Object target) {
        if (target == ItemEditPolicy.name) {
            return "Name";
        }

        if (target == ItemEditPolicy.description) {
            return "Description";
        }

        IntelligentObjectProperty intelligentObjectProperty = (IntelligentObjectProperty) target;
        if (intelligentObjectProperty != null) {
            return intelligentObjectProperty.getStringPropertyDefinitionInfo().Name();
        }

        return null;
    }

    public String GetDisplayNameForKey(Object target) {
        if (target == ItemEditPolicy.name) {
            return "Name";
        }

        if (target == ItemEditPolicy.description) {
            return "Description";
        }

        IntelligentObjectProperty intelligentObjectProperty = (IntelligentObjectProperty) target;
        if (intelligentObjectProperty != null) {
            return intelligentObjectProperty.getStringPropertyDefinitionInfo()
                    .GetDisplayName(this.objectDefinition.getPropertyDefinitions());
        }

        return null;
    }

    public String SearchableValueFor(Object target) {
        if (target == ItemEditPolicy.name) {
            return this.InstanceName();
        }

        if (target == ItemEditPolicy.description) {
            return this.Description();
        }

        IntelligentObjectProperty intelligentObjectProperty = (IntelligentObjectProperty) target;
        if (intelligentObjectProperty != null) {
            return intelligentObjectProperty.StringValue();
        }

        return null;
    }

    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    protected boolean IsDerivedName() {
        return false;
    }


    public IntelligentObjectDefinition Parent() {
        return this.parent;
    }

    public void Parent(IntelligentObjectDefinition value) {
        this.parent = value;

        for (IntelligentObjectProperty objectProperty : this.properties.values) {
            objectProperty.NotifyObjectParentChanged(this.parent);
        }
    }


    public String DefinitionName() {
        return this.objectDefinition.Name();
    }

    public String getDefinitionName() {
        return objectDefinition.Name();
    }

    public String InstanceName() {
        return this.name;
    }

    public void InstanceName(String name) {
        String oldNameError = this.checkNameValid();
        String _name = this.name;
        if (!this.InstanceNameChanging(_name, name)) {
            return;
        }
        this.name = name;
        this.InstanceNameChanged(_name, this.name);
        this.propertyChanged("Name");
        if (this.parent != null) {
            String text2 = this.checkNameValid();
            if (oldNameError == null && text2 != null) {
                this.Parent().method_417(this, "Name");
                return;
            }

            if (oldNameError != null && text2 == null) {
                this.Parent().method_418(this, "Name");
                ;
            }
        }
    }

    public String Description() {
        return this.description;
    }

    public void Description(String description) {
        this.description = description;
        this.propertyChanged("Description");
    }


    public void changeNameProperty() {
        if (this.Parent() != null) {
            if (this.checkNameValid() != null) {
                this.Parent().method_417(this, "Name");
                return;
            }
            this.Parent().method_418(this, "Name");
        }
    }


    public Properties getProperties() {
        return this.properties;
    }

    public void setProperties(Properties properties) {
        this.properties = properties;
    }


    protected boolean isDerivedName() {
        return false;
    }

    protected boolean caresAboutNameError() {
        return true;
    }


    protected boolean InstanceNameChanging(String oldName, String newName) {
        return true;
    }

    protected void InstanceNameChanged(String oldName, String newName) {
        if (this.parent != null) {
            this.Parent().instanceNameChanged(oldName, newName, this);
        }
    }


    protected boolean isDeriveName() {
        return false;
    }

    protected boolean isCaresAboutNameError() {
        return true;
    }

    protected String checkNameValid() {
        if (this.isDeriveName() || !this.isCaresAboutNameError()) {
            return null;
        }
        StringBuffer error = new StringBuffer();
        boolean r = NameValid.isValid(this.InstanceName(), error);
        if (r) {
            return error.toString();
        }
        if (this.Parent() != null) {
            String result = this.Parent().checkNameMultiple(this.InstanceName());
            if (result == null) {
                return error.toString();
            }
        }
        r = this.isDuplicateIdentifier(this.InstanceName(), error);
        return error.toString();
    }

    protected void propertyChanged(String value) {
        this.NotifyPropertyChanged(value, value);
    }

    protected void NotifyPropertyChanged(String v1, String v2) {

    }

    @Override
    public String toString() {
        return this.InstanceName();
    }

    public Properties getPropertyGroupInstance(PropertyGroupClass propertyGroupClass) {
        return this.properties;
    }

    public void destroyInstance() {
        for (IntelligentObjectProperty objectProperty : this.properties.values) {
            objectProperty.destroyInstance();
        }

        if (!this.objectDefinition.IsJustAFactory()) {
            this.objectDefinition.getAssociatedInstances().remove(this);
        }
    }

    public void copyProperties(AbsPropertyObject properties) {
        this.copyProperties(properties, -1);
    }

    public void copyProperties(AbsPropertyObject properties, int count) {
        if (properties == null) {
            throw new IllegalArgumentException();
        }
        this.properties.copy(properties.getProperties(), count);
    }

    public String getGridObjectClassName() {
        return this.objectDefinition.Name() + " Instance";
    }

    public String getGridObjectDescription() {
        return this.objectDefinition.Description();
    }

    public String getGridObjectInstanceName() {
        return this.InstanceName();
    }

    public GridItemProperties getGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        // TODO: 2021/11/11
        return gridItemProperties;
    }

    public IntelligentObjectProperty updatePropertyChange(int param0, Object param1) {
        // TODO: 2021/11/11
        return null;
    }

    public String[] displayedValuesNeeded(int param0) {
        return new String[0];
    }


    public boolean notifyParenOfErrors() {
        return true;
    }

    public String getDefaultValueOverrideFor(IntelligentObjectProperty objectProperty) {
        return null;
    }

    public IAutoComplete getAutoCompleteObject(String name, IntelligentObjectDefinition objectFacility) {
        return this.objectDefinition.getAutoCompleteObject(name, objectFacility);
    }

//    public void getAutoCompleteChoices(List<CompleteChoice> results, )

    public boolean IsOwnedBy(GridObjectDefinition gridObjectDefinition) {
        return this.parent == gridObjectDefinition;
    }

    public String ObjectName() {
        return this.InstanceName();
    }

    public void ObjectName(String name) {
        StringBuffer error = new StringBuffer();
        if (!Strings.isNullOrEmpty(name) && this.validIdentifierFormat(name, error)) {
            this.InstanceName(name);
        }
    }

    public String postCompileCheck(IntelligentObjectProperty objectProperty) {
        return null;
    }

    boolean isVisiable(IntelligentObjectProperty objectProperty) {
        return true;
    }

    protected void LoadOldDefaultValuesForLoadFrom(IntelligentObjectXml objectXml) {

    }

    public String getProjectItemName() {
        if (this.parent == null) {
            return null;
        }
        if (this.parent.ActiveModel() != null) {
            return this.parent.ActiveModel().getName();
        }
        return this.parent.Name();
    }

    public String itemName() {
        return this.InstanceName();
    }

    public String itemTypeName() {
        return this.getGridObjectClassName();
    }

    public String getNameForKey(Object key) {
        if (key == ItemEditPolicy.getName()) {
            return "Name";
        }
        if (key == ItemEditPolicy.getDescription()) {
            return "Description";
        }

        if (key instanceof IntelligentObjectProperty) {
            IntelligentObjectProperty objectProperty = (IntelligentObjectProperty) key;
            return objectProperty.getStringPropertyDefinitionInfo().Name();
        }
        return null;
    }

    public String getDisplayForKey(Object key) {
        if (key == ItemEditPolicy.getName()) {
            return "Name";
        }
        if (key == ItemEditPolicy.getDescription()) {
            return "Description";
        }

        if (key instanceof IntelligentObjectProperty) {
            IntelligentObjectProperty objectProperty = (IntelligentObjectProperty) key;
            return objectProperty.getStringPropertyDefinitionInfo().DisplayName();
        }
        return null;
    }

    public String searchableValueFor(Object value) {
        if (value == ItemEditPolicy.getName()) {
            return this.InstanceName();
        }

        if (value == ItemEditPolicy.getDescription()) {
            return this.Description();
        }

        if (value instanceof IntelligentObjectProperty) {
            return ((IntelligentObjectProperty) value).getStringValue();
        }
        return null;
    }

    protected boolean validIdentifierFormat(String identifier, StringBuffer error) {
        boolean r = NameValid.isValid(identifier, error);
        if (r) {
            return false;
        }
        if (!this.InstanceName().equals(identifier)) {
            if (this.Parent() == null) {
                return false;
            }
            r = this.Parent().IsValidIdentifier(identifier, error);
            if (r) {
                return false;
            }

            r = this.isDuplicateIdentifier(identifier, error);
            return r;
        }
        return r;
    }

    private boolean isDuplicateIdentifier(String identifier, StringBuffer error) {
        if (this.Parent() == null) {
            return false;
        }
        boolean r = this.Parent().isDuplicateIdentifier(identifier, error);
        return r;
    }

    public GridObjectDefinition getObjectDefinition() {
        return this.objectDefinition;
    }

    public IntelligentObjectDefinition getIntelligentObjectFacility() {
        return parent;
    }


    public void submitToSearch(ItemEditPolicy itemEditPolicy,
                               ActiveModel activeModel) {

    }

    public boolean isAReferenceProperty(String name) {
        StringPropertyDefinition stringPropertyDefinition =
                this.objectDefinition.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(name);
        if (stringPropertyDefinition != null) {
            IntelligentObjectProperty intelligentObjectProperty =
                    this.properties.values.get(stringPropertyDefinition.getOverRidePropertyIndex());
            return intelligentObjectProperty.IsAReference();
        }
        return false;
    }

    public boolean canBeReferenceProperty(String name) {
        StringPropertyDefinition stringPropertyDefinition =
                this.objectDefinition.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(name);
        return stringPropertyDefinition != null;
    }

    public Object getDefaultValueFor(String name) {
        StringPropertyDefinition stringPropertyDefinition =
                this.objectDefinition.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(name);
        if (stringPropertyDefinition != null) {
            IntelligentObjectProperty intelligentObjectFacility =
                    this.properties.values.get(stringPropertyDefinition.getOverRidePropertyIndex());
            return intelligentObjectFacility.getDefaultName(this.getObjectDefinition().getPropertyDefinitions());
        }
        return null;
    }

    void GetAdditionalItemProperties(PropertyDescriptorCollection propertyDescriptorCollection,
                                     PropertyDescriptors propertyDescriptors) {

    }

    String getErrorFor(Properties properties, String s) {
        return null;
    }

    Object getAdditionalDetailsFor(Properties properties, String s1) {
        return null;
    }

    public void DoVersionUpgrade(IntelligentObjectXml intelligentObjectXml) {

    }

    public IntelligentObjectProperty GetPropertyForLoad(String s1, IntelligentObjectXml intelligentObjectXml) {
        return null;
    }

    public boolean ShouldAlwaysWriteOutPropertyValue(IntelligentObjectProperty intelligentObjectProperty) {
        return false;
    }

    public void PostCompileCheck(IntelligentObjectProperty intelligentObjectProperty, StringBuilder error) {

    }

    @Browsable(false)
    public boolean NotifyParentOfErrors() {
        return true;
    }

    public void DestroyInstance() {
        for (IntelligentObjectProperty intelligentObjectProperty : this.properties.values) {
            intelligentObjectProperty.DestroyInstance();
        }

        if (!this.objectDefinition.IsJustAFactory()) {
            this.objectDefinition.getAssociatedInstances().remove(this);
        }
    }

    protected boolean readXmlProperties(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return this.properties.readXmlProperties(xmlReader, intelligentObjectXml);
    }

    public Properties GetPropertyGroupInstance(PropertyGroupClass propertyGroupClass) {
        return this.properties;
    }

    @Override
    public String GetGridObjectClassName() {
        return this.objectDefinition.Name() + " Instance";
    }

    @Override
    public String GetGridObjectDescription() {
        return this.objectDefinition.Description();
    }

    @Override
    public String GetGridObjectInstanceName() {
        return this.InstanceName();
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        for (IntelligentObjectProperty propertyInstance : this.properties.values) {
            GridItemProperty gridItemProperty =
                    propertyInstance.GetGridItemProperty(this.properties.PropertyDefinitions);
            if (gridItemProperty != null) {
                if (propertyInstance.getStringPropertyDefinitionInfo().ParentPropertyDefinition() != null) {
                    gridItemProperty.Parent(gridItemProperties.stream().filter((GridItemProperty prop) ->
                                    Objects.equals(prop.name,
                                            propertyInstance.getStringPropertyDefinitionInfo().ParentPropertyDefinition()
                                                    .Name()))
                            .findFirst().orElse(null));
                } else {
                    gridItemProperty.Parent(gridItemProperties.getGridItemPropertyByName(gridItemProperty.CategoryName()));
                }

                gridItemProperty.ComplexityLevel(propertyInstance.getStringPropertyDefinitionInfo()
                        .GetComplexityLevel(this.properties.PropertyDefinitions));
                gridItemProperties.add(gridItemProperty);
            } else {
                GridItemProperties itemProperties = new GridItemProperties();
                propertyInstance.AlternateGetGridItemProperties(this.properties.PropertyDefinitions, itemProperties);
                for (GridItemProperty itemProperty : itemProperties) {
                    if (itemProperty.Parent() == null) {
                        if (propertyInstance.getStringPropertyDefinitionInfo().ParentPropertyDefinition() != null) {
                            itemProperty.Parent(gridItemProperties.stream().filter((GridItemProperty prop) ->
                                    prop.name == propertyInstance.getStringPropertyDefinitionInfo()
                                            .ParentPropertyDefinition().Name()).findFirst().orElse(null));
                        } else {
                            itemProperty.Parent(gridItemProperties.getGridItemPropertyByName(itemProperty.CategoryName()));
                        }
                    }

                    itemProperty.ComplexityLevel(propertyInstance.getStringPropertyDefinitionInfo()
                            .GetComplexityLevel(this.properties.PropertyDefinitions));
                    gridItemProperties.add(itemProperty);
                }
            }
        }
        return gridItemProperties;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int count, Object name) {
        if (count == 501) {
            this.InstanceName((String) name);
            return null;
        }

        if (count < 1000 || count >= 1000000) {
            return null;
        }

        int num;
        if (count >= 30000) {
            num = count - 30000;
        } else if (count >= 10000) {
            num = count - 10000;
        } else if (count >= 20000) {
            num = count - 20000;
        } else {
            num = count - 1000;
        }

        if (num >= 0 && num < this.properties.size()) {
            IntelligentObjectProperty intelligentObjectProperty = this.properties.get(num);
            intelligentObjectProperty.UpdateGridPropertyValue(count, name);
            return intelligentObjectProperty;
        }

        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int count) {
        if (count == 501) {
            return null;
        }

        if (count >= 30000) {
            count -= 30000;
        } else if (count >= 10000) {
            count -= 10000;
        } else if (count >= 20000) {
            count -= 20000;
        } else {
            count -= 1000;
        }

        if (count >= 0 && count < this.properties.size()) {
            IntelligentObjectProperty intelligentObjectProperty = this.properties.get(count);
            return intelligentObjectProperty.GetListValues();
        }

        return null;
    }

    public void PropertyUpdating(IntelligentObjectProperty intelligentObjectProperty) {
        if (this.Parent() != null && this.Parent().activeModel != null &&
                this.Parent().activeModel.getIntelligentObjectDefinition() ==
                        this.Parent() &&
                this.Parent().activeModel.projectDefinition != null) {
            ISearch param = intelligentObjectProperty.getStringPropertyDefinitionInfo().IsTableProperty() ?
                    intelligentObjectProperty.getProperties() : this;
            this.Parent().activeModel.projectDefinition.getItemEditPolicy()
                    .method_0(param, intelligentObjectProperty, this.Parent().activeModel);
        }
    }

    public Object PropertyUpdated(IntelligentObjectProperty intelligentObjectProperty, boolean param1,
                                  Object target) {
        this.NotifyPropertyChanged(intelligentObjectProperty.Name(), intelligentObjectProperty.DisplayName());
        return null;
    }

    private IntelligentObjectProperty findPropertyByName(String name) {
        return this.properties.findByName(name);
    }

    public boolean IsVisible(IntelligentObjectProperty intelligentObjectProperty) {
        return true;
    }
}
