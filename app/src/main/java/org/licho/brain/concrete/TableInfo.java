package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.utils.simu.system.IDisposable;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 */
public class TableInfo extends AbsPropertyObject {
    private Set<StringPropertyDefinition> stringPropertyDefinitionSet = new HashSet<>();

    public TableInfo(GridObjectDefinition definition, String name) {
        super(definition, name);
    }

    @Override
    public String GetGridObjectClassName() {
        return null;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
        return new String[0];
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
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    public IDisposable dispose() {
        return this.getActionRun().disposable();
    }

    private ActionRun getActionRun() {
        return new ActionRun(this::method_14);
    }

    private void method_14() {
        for (StringPropertyDefinition stringPropertyDefinition : this.stringPropertyDefinitionSet) {
            Map<String, IntelligentObjectProperty> intelligentObjectProperties = new HashMap<>();
            Table parent =  ((TableDefinition)this.objectDefinition).Parent().Parent();

            for (Properties properties : parent.Data().Rows().values) {
                IntelligentObjectProperty intelligentObjectProperty =
                        properties.values.get(stringPropertyDefinition.overRidePropertyIndex);
                String stringValue = intelligentObjectProperty.StringValue();
                IntelligentObjectProperty objectProperty = intelligentObjectProperties.get(stringValue);
                if (objectProperty != null) {
                    if (objectProperty.getError() == null) {
                        objectProperty.setError(EngineResources.Error_DuplicateKeyValue);
                        parent.Parent().NotifyIntelligentObjectPropertyErrorEvent(objectProperty);
                    }
                }else{
                    intelligentObjectProperties.put(stringValue, intelligentObjectProperty);
                }
            }
        }
        this.stringPropertyDefinitionSet.clear();
    }
}
