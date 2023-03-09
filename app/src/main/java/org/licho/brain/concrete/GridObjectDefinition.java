package org.licho.brain.concrete;

import lombok.extern.slf4j.Slf4j;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum13;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.PropertyGroupClass;
import org.licho.brain.event.EventHandler;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * definition model({@link AbsPropertyObject})
 */
@Slf4j
public abstract class GridObjectDefinition implements IGridObject, IAutoComplete {
    /**
     * object name
     */
    private String name;
    /**
     * description
     */
    private String description;

    protected PropertyDefinitions propertyDefinitions;
    private final List<AbsPropertyObject> associatedInstances;
    private int count;
    private EventHandler<PropertyChangedEventArgs> propertyDescriptionChangedEventHandler;


    public GridObjectDefinition(String name) {
        this.name = name;
        this.propertyDefinitions = new PropertyDefinitions(this, null);
        this.associatedInstances = new ArrayList<>();
        this.description = "";
    }

    public String Name() {
        return name;
    }

    public void Name(String name) {
        this.name = name;
    }

    public String ExpressionIdentifier() {
        return this.Name();
    }


    public String Description() {
        return description;
    }

    public void Description(String value) {
        this.description = value;
        this.OnDescriptionChanged();
    }

    protected void OnDescriptionChanged() {

    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public PropertyDefinitions getPropertyDefinitions() {
        return this.propertyDefinitions;
    }

    public PropertyDefinitions GetPropertyDefinitions(PropertyGroupClass propertyDefinitions) {
        return this.getPropertyDefinitions();
    }

    public List<AbsPropertyObject> getAssociatedInstances() {
        return this.associatedInstances;
    }

    public List<AbsPropertyObject> getAllAssociatedInstancesOfThisPropertyBagList() {
        return this.getAssociatedInstances();
    }

    public abstract AbsPropertyObject CreateInstance(String name);

    public void DestroyInstance(AbsPropertyObject propertyObject) {
        this.associatedInstances.remove(propertyObject);
    }

    public String getObjectClassName() {
        return this.Name() + " Definition";
    }

    public String getObjectDescription() {
        return this.Description();
    }

    public String GetGridObjectInstanceName() {
        return this.name;
    }

    public boolean IsJustAFactory() {
        return false;
    }

    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        for (PropertyDefinitionFacade propertyDefinitionFacade : this.propertyDefinitions.getPropertyDefinitionList()) {
            GridItemProperty gridItemProperty;
            if (propertyDefinitionFacade.Parent() != null) {
                gridItemProperty =
                        gridItemProperties.getGridItemPropertyByName(propertyDefinitionFacade.Parent().Name());
                gridItemProperties.add(new GridItemProperty(propertyDefinitionFacade.Name(),
                        propertyDefinitionFacade.DisplayName(),
                        gridItemProperty,
                        propertyDefinitionFacade.Description(),
                        propertyDefinitionFacade.InitiallyExpanded()));
            }
        }

        for (StringPropertyDefinition propertyDefinition : this.propertyDefinitions.values) {
            GridItemProperty gridItemProperty = propertyDefinition.GetGridItemProperties(propertyDefinitions);
            if (propertyDefinition.ParentPropertyDefinition() != null) {
                gridItemProperty.Parent(gridItemProperties.stream().filter(t ->
                                t.name.equals(propertyDefinition.ParentPropertyDefinition().Name()))
                        .findFirst()
                        .orElse(null));
            } else {
                gridItemProperty.Parent(gridItemProperties.getGridItemPropertyByName(gridItemProperty.CategoryName()));
            }
            gridItemProperties.add(gridItemProperty);
        }
        return gridItemProperties;
    }

    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object value) {
        if (param0 >= 1000 && param0 < 1000000) {
            int index;
            if (param0 >= 30000) {
                index = param0 - 30000;
            } else if (param0 >= 10000) {
                index = param0 - 10000;
            } else if (param0 >= 20000) {
                index = param0 - 20000;
            } else {
                index = param0 - 1000;
            }
            StringPropertyDefinition stringPropertyDefinition = this.propertyDefinitions.values.get(index);
            stringPropertyDefinition.UpdateGridPropertyValue(param0, value);
        }
        return null;
    }

    public List<AbsPropertyObject> AllAssociatedInstancesOfThisPropertyBagList() {
        return this.getAssociatedInstances();
    }

    public IAutoComplete getAutoCompleteObject(String name, IntelligentObjectDefinition objectFacility) {
        return this.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(name);

    }

    public String[] DisplayedValuesNeeded(int index) {
        return null;
    }


    public void NotifyPropertyDefinitionRenamed(StringPropertyDefinition stringPropertyDefinition, String param1) {
    }

    public void NotifyPropertyInserted(PropertyDefinitions propertyDefinitions,
                                       StringPropertyDefinition stringPropertyDefinition, int param2,
                                       Map<Properties, IntelligentObjectProperty> originalPropertyInstances) {
    }

    public void NotifyPropertyRemoved(PropertyDefinitions propertyDefinitions,
                                      StringPropertyDefinition stringPropertyDefinition, int param2,
                                      Map<Properties, IntelligentObjectProperty> originalPropertyInstances) {
    }

    public void NotifyPropertyRequiredChanged(PropertyDefinitions propertyDefinitions,
                                              StringPropertyDefinition stringPropertyDefinition) {
    }

    public void NotifyNumericPropertyDefaultUnitSubTypeChanged(NumericDataPropertyDefinition
                                                                       numericDataPropertyDefinition,
                                                               PropertyDefinitions propertyDefinitions, int param2
            , int param3) {
    }

    public void NotifyPropertyMoved(PropertyDefinitions propertyDefinitions, int param1, int param2) {
    }

    public void NotifyPropertyReplaced(PropertyDefinitions propertyDefinitions, int param1,
                                       StringPropertyDefinition stringPropertyDefinition,
                                       StringPropertyDefinition stringPropertyDefinition1,
                                       StringPropertyDefinition.Class36 param4) {
    }

    public void NotifyPropertyDefaultChanged(PropertyDefinitions propertyDefinitions,
                                             StringPropertyDefinition stringPropertyDefinition,
                                             String param2, String param3) {
    }


    public void processPropertyDefinitions(Action<StringPropertyDefinition> action) {
        this.getPropertyDefinitions().process(t -> {
            if (t.IsOwnedBy(this)) {
                action.apply(t);
            }
        });
    }

    public void GetAutoCompleteChoices(List<CompleteChoice> results, Enum13 enum13,
                                       IntelligentObjectDefinition intelligentObjectDefinition, boolean param3) {
        for (StringPropertyDefinition stringPropertyDefinition : this.getPropertyDefinitions().values) {
            if (stringPropertyDefinition.IsVisible(this.getPropertyDefinitions()) &&
                    !stringPropertyDefinition.isObsoleteProperty() &&
                    stringPropertyDefinition.GetComplexityLevel(this.getPropertyDefinitions()) == ProductComplexityLevel.Basic) {
                // TODO: 2021/12/9 if (512)
                CompleteChoice completeChoice = new CompleteChoice(stringPropertyDefinition.Name(), 0,
                        stringPropertyDefinition.GetDescription(this.getPropertyDefinitions()));
                completeChoice.method_4(true);
                completeChoice.method_6(true);
                results.add(completeChoice);
            }
        }
    }

    public void process(Action<StringPropertyDefinition> action) {
        this.getPropertyDefinitions().process((StringPropertyDefinition stringPropertyDefinitionInfo) -> {
            if (stringPropertyDefinitionInfo.IsOwnedBy(this)) {
                action.apply(stringPropertyDefinitionInfo);
            }
        });
    }
}
