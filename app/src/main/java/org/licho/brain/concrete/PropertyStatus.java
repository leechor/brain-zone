package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.event.EventHandler;
import org.licho.brain.utils.simu.IAdvancedProperties;
import org.licho.brain.utils.simu.IReselect;
import org.licho.brain.utils.simu.system.Color;
import org.licho.brain.utils.simu.system.IDisposable;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */
public class PropertyStatus implements IDisposable, IPropertyStatus {
    private IPropertyUpdateOperator propertyUpdateOperator;
    private IGridObject gridObject;
    private Map<String, Object> gridObjectClassNameStates = new HashMap<>();
    private GridObjectDefinition gridObjectDefinition;
    private ProjectManager projectManager;
    private boolean bool_0;
    private Map<GridItemProperty, Object> gridItemPropertyObjectMap = new HashMap<>();
    private Map<Object, GridItemProperty> objectGridItemPropertyMap = new HashMap<>();

    public PropertyStatus(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    @Override
    public void Dispose() {

    }

    @Override
    public void SetValue(Object param0, Object param1) {

    }

    @Override
    public String ValidateValue(Object param0, Object param1) {
        return null;
    }

    @Override
    public void PropertySelected(Object param0) {

    }

    @Override
    public boolean PropertyEngaged(Object param0) {
        return false;
    }

    @Override
    public void PropertyButtonClicked(Object param0) {

    }

    @Override
    public void SetEnabled(Object param0, boolean param1) {

    }

    @Override
    public Object[] GetDisplayedValues(Object param0) {
        return new Object[0];
    }

    @Override
    public String GetErrorText(Object param0) {
        return null;
    }

    public boolean reSelect(IGridObject gridObject, GridObjectDefinition gridObjectDefinition, boolean bool_0) {
        this.setGridObjectClassNameStates();
        if (this.gridObject == gridObject && this.gridObjectDefinition == gridObjectDefinition && !bool_0) {
            return false;
        }
        this.unRegisterPropertyChanged();
        this.gridObject = gridObject;
        this.gridObjectDefinition = gridObjectDefinition;
        this.registerPropertyChanged();
        if (this.propertyUpdateOperator != null) {
            this.propertyUpdateOperator.Clear();
        }
        this.objectGridItemPropertyMap.clear();
        this.gridItemPropertyObjectMap.clear();
        if (this.gridObject != null && this.propertyUpdateOperator != null) {
            GridItemProperties gridItemProperties = new GridItemProperties();
            this.gridObject.getPropertyItemList(gridItemProperties, gridObjectDefinition);

            List<GridItemProperty> itemProperties = gridItemProperties.stream()
                    .sorted(Comparator.comparing(this::method_8)).collect(Collectors.toList());
            gridItemProperties.clear();
            gridItemProperties.addAll(itemProperties);
            this.setGridItemPropertiesHaveAdvanced(gridItemProperties);
            try (var tmp = this.createPropertyUpdateOperatorWrapper(this.method_7())) {
                this.processProperty(gridItemProperties);
                return true;
            }
        }
        this.setGridItemPropertiesHaveAdvanced(null);
        return true;
    }

    private void processProperty(GridItemProperties gridItemProperties) {
        this.processChildrenProperty(gridItemProperties, null);
    }

    private Object method_7() {
        return null;
    }

    private void setGridItemPropertiesHaveAdvanced(GridItemProperties gridItemProperties) {
        if (this.gridObject instanceof IAdvancedProperties) {
            Boolean hasAdvancedProperties = ((IAdvancedProperties) this.gridObject).HasAdvancedProperties();
            if (hasAdvancedProperties != null) {
                this.projectManager.isHasAdvancedProperties(hasAdvancedProperties);
                return;
            }
        }

        boolean haveAdvanced;
        if (gridItemProperties != null) {
            haveAdvanced = gridItemProperties.stream()
                    .anyMatch(t -> t.ComplexityLevel() == ProductComplexityLevel.Advanced);
        } else {
            haveAdvanced = false;
        }
        this.projectManager.isHasAdvancedProperties(haveAdvanced);
    }

    private int method_8(GridItemProperty gridItemProperty) {
        if (gridItemProperty.type == null) {
            if (gridItemProperty.Parent() != null) {
                return 20;
            }
            if (gridItemProperty.name.equalsIgnoreCase(EngineResources.CategoryName_Animation)) {
                return 9;
            }
            if (gridItemProperty.name.equalsIgnoreCase(EngineResources.CategoryName_General)) {
                return 8;
            }
            return 0;
        } else {
            if (gridItemProperty.Parent() != null && gridItemProperty.Parent().type == null && gridItemProperty.Parent().Parent() == null) {
                return 10;
            }
            return 100;
        }
    }

    private void registerPropertyChanged() {
        if (this.gridObject instanceof INotifyPropertyChanged) {
            INotifyPropertyChanged.PropertyChanged.unSubscribe(this::method_3);
        }
        if (this.gridObject instanceof IReselect) {
            EventHandler.unSubscribe(((IReselect) this.gridObject).Reselect, (o, t) -> this.reSelect(t));
        }
    }

    private void unRegisterPropertyChanged() {
        if (this.gridObject instanceof INotifyPropertyChanged) {
            INotifyPropertyChanged.PropertyChanged.subscribe(this::method_3);
        }
        if (this.gridObject instanceof IReselect) {
            EventHandler.subscribe(((IReselect) this.gridObject).Reselect, (o, t) -> this.reSelect(t));
        }
    }

    private void reSelect(IGridObject gridObject) {
        this.reSelect();
    }

    public void reSelect() {
        this.reSelect(this.gridObject, this.gridObjectDefinition, true);
    }

    private void method_3(Object sender, PropertyChangedEventArgs e) {
        if (!this.method_2()) {
            if (e.PropertyName() != null) {
                for (GridItemProperty gridItemProperty : this.gridItemPropertyObjectMap.keySet()) {
                    if (Objects.equals(gridItemProperty.DisplayName(), e.PropertyName()) && gridItemProperty.getPropertyOperator() != null) {
                        this.updateProperty(gridItemProperty.indexVersion,
                                gridItemProperty.getPropertyOperator().GetProperty());
                    }
                }
            }
            this.method_14(Integer.MAX_VALUE);
        }
    }

    private void method_14(int indexVersion) {
        GridItemProperties gridItemProperties = new GridItemProperties();
        Map<GridItemProperty, Integer> objectVersionMap = new HashMap<>();
        for (var entry : this.objectGridItemPropertyMap.entrySet()) {
            if (entry.getValue().getPropertyOperator() == null) {
                continue;
            }

            boolean isVisible = entry.getValue().getPropertyOperator().IsVisible();
            boolean isEnabled = entry.getValue().getPropertyOperator().IsEnabled();
            Color color = entry.getValue().getPropertyOperator().GetColor();
            boolean sameVersion = isVisible && entry.getValue().indexVersion == indexVersion;
            boolean haveError = entry.getValue().getErrorText() != null;
            int imageIndex = entry.getValue().getPropertyOperator().ImageIndex();
            if (this.propertyUpdateOperator != null) {
                this.propertyUpdateOperator.SetPresentation(entry.getKey(), isVisible, isEnabled, sameVersion, haveError
                        , imageIndex, color);
            }

            if (isVisible && entry.getValue().Parent() != null) {
                for (GridItemProperty parent = entry.getValue().Parent(); parent != null; parent = parent.Parent()) {
                    int num = 0;
                    num = objectVersionMap.get(parent);
                    objectVersionMap.put(parent, num + 1);
                }
            }

            if (isVisible && this.propertyUpdateOperator != null) {
                this.propertyUpdateOperator.UpdateProperty(entry.getKey(),
                        entry.getValue().getPropertyOperator().GetProperty());
            }

            if (entry.getValue().getPropertyOperator() instanceof IGridItemPropertiesOperator) {
                gridItemProperties.add(entry.getValue());
            }

            ActiveModel activeModel = this.projectManager.getActiveModel();
            if (activeModel != null) {
                if (this.gridObject instanceof IReferencedObjectOperator) {
                    IReferencedObjectOperator referencedObjectOperator = (IReferencedObjectOperator) this.gridObject;
                    for (var gridObject : referencedObjectOperator.ReferencedObjects()) {
                        activeModel.getErrors().ResetAllPropertiesError(gridObject);
                    }
                } else {
                    activeModel.getErrors().ResetAllPropertiesError(this.gridObject);
                }
            }

            try (var t = this.createPropertyUpdateOperatorWrapper((this.propertyUpdateOperator != null) ?
                    this.propertyUpdateOperator.GetStates() : null)) {
                for (var gridItemProperty : gridItemProperties) {
                    IGridItemPropertiesOperator propertyOperator =
                            (IGridItemPropertiesOperator) gridItemProperty.getPropertyOperator();
                    GridItemProperties itemProperties = new GridItemProperties();
                    itemProperties = propertyOperator.GetGridPropertyItemList(itemProperties,
                            this.gridObjectDefinition);

                    if (itemProperties == null) {
                        continue;
                    }

                    List<Object> list = new ArrayList<>();
                    for (var tmp : this.objectGridItemPropertyMap.entrySet()) {
                        if (tmp.getValue().Parent() == gridItemProperty && tmp.getValue().method_6()) {
                            if (this.propertyUpdateOperator != null) {
                                this.propertyUpdateOperator.RemoveProperty(tmp.getKey());
                            }
                            this.gridItemPropertyObjectMap.remove(tmp.getValue());
                            list.add(tmp.getKey());
                        }
                    }
                    list.forEach(i -> this.objectGridItemPropertyMap.remove(i));
                    itemProperties.forEach(g -> {
                        g.Parent(gridItemProperty);
                        g.method_7(true);
                    });
                    this.processChildrenProperty(itemProperties, objectVersionMap);
                }
            }
        }

        for (var entry : this.objectGridItemPropertyMap.entrySet()) {
            if (entry.getValue().type != null) {
                continue;
            }

            int count = objectVersionMap.get(entry.getValue());
            boolean isVisible = count != 0;

            if (this.propertyUpdateOperator != null) {
                this.propertyUpdateOperator.SetPresentation(entry.getKey(), isVisible, true, false, false, -1,
                        Color.Empty());
            }
        }
    }

    private void processChildrenProperty(GridItemProperties gridItemProperties,
                                         Map<GridItemProperty, Integer> visibleChildren) {
        boolean notOwnedBy = false;
        if (this.gridObjectDefinition != null) {
            if (this.gridObject instanceof IOwner) {
                IOwner owner = (IOwner) this.gridObject;
                notOwnedBy = !owner.IsOwnedBy(this.gridObjectDefinition);
            }
        }

        List<PropertyStatus.GridItemPropertyOperatorCombine> gridItemPropertyOperatorCombines = new ArrayList<>();
        if (visibleChildren == null) {
            visibleChildren = new HashMap<>();
        }

        if (this.projectManager.isHasAdvancedProperties() && this.projectManager.ComplexityLevel != ProductComplexityLevel.Advanced) {
            List<GridItemProperty> itemProperties = new ArrayList<>();
            for (GridItemProperty gridItemProperty : gridItemProperties) {
                if (gridItemProperty.type == null) {
                    itemProperties.add(gridItemProperty);
                    gridItemProperty.setInitiallyExpanded(true);
                } else if (gridItemProperty.ComplexityLevel().ordinal() <= this.projectManager.ComplexityLevel.ordinal()) {
                    itemProperties.add(gridItemProperty);
                } else if (!gridItemProperty.method_13()) {
                    itemProperties.add(gridItemProperty);
                    gridItemProperty.ReadOnly(true);
                }
            }
            gridItemProperties.clear();
            gridItemProperties.addAll(itemProperties);
        }

        for (var gridItemProperty : gridItemProperties) {
            Object category = null;
            Object parentObject = this.gridItemPropertyObjectMap.get(gridItemProperty.Parent());
            if (gridItemProperty.Parent() != null && parentObject == null) {
                String categoryName = gridItemProperty.Parent().CategoryName();
                GridItemProperty itemProperty = gridItemProperties.stream()
                        .filter(t -> Objects.equals(t.DisplayName(), categoryName))
                        .findFirst()
                        .orElse(null);
                if (itemProperty != null) {
                    parentObject = this.gridItemPropertyObjectMap.get(itemProperty);
                }
            }

            boolean initiallyExpanded = false;
            if (gridItemProperty.type == null) {
                String description = gridItemProperty.getDescription();
                if (description == null) {
                    description = this.gridObject.getObjectDescription();
                }

                if (this.propertyUpdateOperator != null) {
                    category =
                            this.propertyUpdateOperator.AddCategory(gridItemProperty.DisplayName(), description,
                                    parentObject, gridItemProperty.getInitiallyExpanded());
                }
                initiallyExpanded = gridItemProperty.getInitiallyExpanded();
            } else if (parentObject != null && this.propertyUpdateOperator != null) {
                category = this.propertyUpdateOperator.AddProperty(gridItemProperty.DisplayName(),
                        gridItemProperty.getDescription(), gridItemProperty.indexVersion, parentObject,
                        gridItemProperty.type, gridItemProperty.property, gridItemProperty.object_1,
                        gridItemProperty.getPropertyGridFeel(),
                        gridItemProperty.ReadOnly() || (notOwnedBy && !gridItemProperty.getIsRepeatingProperty()),
                        gridItemProperty.getInitiallyExpanded());
            }

            if (gridItemProperty.getPropertyOperator() != null) {
                boolean isVisible = gridItemProperty.getPropertyOperator().IsVisible();
                boolean isEnabled = gridItemProperty.getPropertyOperator().IsEnabled();
                Color color = gridItemProperty.getPropertyOperator().GetColor();
                boolean haveError = false;
                haveError = (gridItemProperty.getPropertyOperator().ErrorText() != null);

                int imageIndex = gridItemProperty.getPropertyOperator().ImageIndex();
                if (this.propertyUpdateOperator != null) {
                    this.propertyUpdateOperator.SetPresentation(category, isVisible, isEnabled, initiallyExpanded,
                            haveError, imageIndex, color);
                }
                if (gridItemProperty.Parent() != null && isVisible) {
                    for (GridItemProperty parent = gridItemProperty.Parent(); parent != null; parent =
                            parent.Parent()) {
                        int num = visibleChildren.get(parent);
                        visibleChildren.put(parent, num + 1);
                    }
                }
            }

            if (category == null) {
                continue;
            }

            if (gridItemProperty.getPropertyOperator() instanceof IGridItemPropertiesOperator) {
                IGridItemPropertiesOperator itemPropertiesOperator =
                        (IGridItemPropertiesOperator) gridItemProperty.getPropertyOperator();
                var tmp = new PropertyStatus.GridItemPropertyOperatorCombine();
                tmp.ItemPropertiesOperator = itemPropertiesOperator;
                tmp.GridItemProperty = gridItemProperty;
                gridItemPropertyOperatorCombines.add(tmp);

                this.objectGridItemPropertyMap.put(category, gridItemProperty);
                this.gridItemPropertyObjectMap.put(gridItemProperty, category);
            }
        }

        for (var info : gridItemPropertyOperatorCombines) {
            GridItemProperties gridPropertyItemList = new GridItemProperties();
            gridPropertyItemList = info.ItemPropertiesOperator.GetGridPropertyItemList(gridPropertyItemList,
                    this.gridObjectDefinition);
            if (gridPropertyItemList != null && gridPropertyItemList.size() > 0) {
                gridPropertyItemList.forEach(t -> {
                    t.Parent(info.GridItemProperty);
                    t.method_7(true);
                });
                this.processChildrenProperty(gridPropertyItemList, visibleChildren);
            }
        }

        for (var gridItemProperty : gridItemProperties) {
            if (gridItemProperty.type != null) {
                continue;
            }

            Object target = this.gridItemPropertyObjectMap.get(gridItemProperty);
            int count = visibleChildren.get(gridItemProperty);
            boolean isVisible = count != 0;

            if (this.propertyUpdateOperator != null) {
                this.propertyUpdateOperator.SetPresentation(target, isVisible, true, false, false, -1, Color.Empty());
            }
        }
    }


    private IDisposable createPropertyUpdateOperatorWrapper(Object target) {
        return new PropertyStatus.PropertyUpdateOperatorWrapper(this.propertyUpdateOperator, target);
    }

    private void updateProperty(int indexVersion, Object property) {
        for (var keyValuePair : this.gridItemPropertyObjectMap.entrySet()) {
            if (keyValuePair.getKey().indexVersion == indexVersion) {
                if (this.propertyUpdateOperator != null) {
                    this.propertyUpdateOperator.UpdateProperty(keyValuePair.getValue(), property);
                    break;
                }
                break;
            }
        }
    }

    private boolean method_2() {
        if (this.projectManager != null && this.projectManager.getPropertyStatus() != null) {
            return this.projectManager.getPropertyStatus().bool_0;
        }
        return this.bool_0;
    }

    private void setGridObjectClassNameStates() {
        if (this.propertyUpdateOperator != null && this.gridObject != null) {
            this.gridObjectClassNameStates.put(this.gridObject.getObjectClassName(),
                    this.propertyUpdateOperator.GetStates());
        }
    }

    private class PropertyUpdateOperatorWrapper implements IDisposable {
        private IPropertyUpdateOperator propertyUpdateOperator;
        private Object target;

        public PropertyUpdateOperatorWrapper(IPropertyUpdateOperator propertyUpdateOperator, Object target) {
            this.propertyUpdateOperator = propertyUpdateOperator;
            this.target = target;
            if (this.propertyUpdateOperator != null) {
                this.propertyUpdateOperator.BeginUpdate();
            }
        }

        @Override
        public void Dispose() {
            if (this.propertyUpdateOperator != null) {
                this.propertyUpdateOperator.EndUpdate(this.target);
            }
        }
    }

    public static class GridItemPropertyOperatorCombine {
        public IGridItemPropertiesOperator ItemPropertiesOperator;
        public GridItemProperty GridItemProperty;
    }
}
