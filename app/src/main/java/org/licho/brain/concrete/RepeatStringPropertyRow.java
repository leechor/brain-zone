package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.api.IExecutionContext;
import org.licho.brain.api.IProperties;
import org.licho.brain.api.IPropertyReaders;
import org.licho.brain.api.IRepeatingProperty;
import org.licho.brain.api.IRepeatingPropertyReader;
import org.licho.brain.api.IRows;
import org.licho.brain.utils.simu.IListData;
import org.licho.brain.utils.simu.system.IBindingList;

/**
 *
 */
public class RepeatStringPropertyRow extends IntelligentObjectProperty implements IRepeatingProperty, IListData,
        IRepeatingPropertyReader {

    public PropertyDescriptors PropertyDescriptors;


    private Table table;

    public RepeatStringPropertyRow(StringPropertyDefinition propertyDefinitionInfo, Properties properties) {
        super(propertyDefinitionInfo, properties);
        this.PropertyDescriptors = new PropertyDescriptors(this);
        super.setError(this.validRepeatGroup());
    }

    private String validRepeatGroup() {
        if (super.getStringPropertyDefinition().RequiredValue() && this.PropertyDescriptors.size() == 0) {
            return EngineResources.Error_RepeatGroup_RequiredButEmpty;
        }
        for (Properties p : this.PropertyDescriptors.values) {
            for (IntelligentObjectProperty intelligentObjectProperty : p.values) {
                if (intelligentObjectProperty.validProperty() && intelligentObjectProperty.getError() != null) {
                    return EngineResources.Error_RepeatGroup_ContainsErrors;
                }
            }
        }
        return null;
    }

    @Override
    public IBindingList ListData() {
        return null;
    }

    @Override
    public boolean getIsReadOnly() {
        return false;
    }

    @Override
    public IRows getRows() {
        return null;
    }

    @Override
    public IProperties AddRow() {
        return null;
    }

    @Override
    public void RemoveRow(int index) {

    }

    @Override
    public int GetCount(IExecutionContext context) {
        return 0;
    }

    @Override
    public IPropertyReaders GetRow(int index, IExecutionContext context) {
        return null;
    }

    public PropertyDescriptors getPropertyDescriptors() {
        return PropertyDescriptors;
    }

    public void setPropertyDescriptors(PropertyDescriptors propertyDescriptors) {
        this.PropertyDescriptors = propertyDescriptors;
    }

    @Override
    public PropertyDescriptors Tuples() {
        return this.PropertyDescriptors;
    }

    public PropertiesRunSpaceWrapper getPropertiesRunSpaceWrapper(IRunSpace runSpace,
                                                                  IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (!this.isRepeatStringProperty()) {
            if (runSpace != null) {
                Properties properties = runSpace.GetTableRowReferences().getProperties(this, runSpace,
                        intelligentObjectRunSpace);
                if (properties != null) {
                    return new PropertiesRunSpaceWrapper(properties, intelligentObjectRunSpace);
                }

                if (runSpace.AssociatedObjectRunSpace() instanceof EntityRunSpace) {
                    EntityRunSpace entityRunSpace = (EntityRunSpace) runSpace.AssociatedObjectRunSpace();
                    return new PropertiesRunSpaceWrapper(entityRunSpace.tableRowReferences.getProperties(this,
                            runSpace, intelligentObjectRunSpace), intelligentObjectRunSpace);
                }
            }
            return new PropertiesRunSpaceWrapper(null, null);
        }
        return new PropertiesRunSpaceWrapper(this.PropertyDescriptors.get(0),
                intelligentObjectRunSpace.ParentObjectRunSpace);
    }

    public boolean isRepeatStringProperty() {
        return (super.getReference() instanceof RepeatingPropertyDefinition && super.getReference() != null) || this.table != null;
    }

    public int method_80(IRunSpace runSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                         AbsBaseRunSpace absBaseRunSpace) {
        if (!this.isRepeatStringProperty()) {
            return this.PropertyDescriptors.Count();
        }
        if (this.table == null) {
            IntelligentObject intelligentObject = (IntelligentObject) intelligentObjectRunSpace.myElementInstance;
            RepeatStringPropertyRow repeatStringPropertyRow =
                    (RepeatStringPropertyRow) intelligentObject.properties.get(super.getReference().overRidePropertyIndex);
            return repeatStringPropertyRow.method_80(runSpace, intelligentObjectRunSpace.CurrentParentObjectRunSpace()
                    , absBaseRunSpace);
        }
        AbsBaseRunSpace baseRunSpace = TokenRunSpaceOperator.smethod_0(this.table, runSpace, absBaseRunSpace);
        if (baseRunSpace != null) {
            return baseRunSpace.tableRowReferences.getCount(this.table, null);
        }
        return this.table.Rows().Count();
    }

    public void setStringValues(String[][] stringValue) {
        for (int i = 0; i < stringValue.length; i++) {
            Properties propertyGroupInstance = this.AddPropertyGroupInstance(null);
            for (int j = 0; j < stringValue[0].length; j++) {
                propertyGroupInstance.get(j).StringValue(stringValue[i][j]);
            }
        }
    }

    public Properties addNewProperties() {
        if (super.getReference() != null && this.PropertyDescriptors.size() >= 1) {
            return null;
        }
        return this.PropertyDescriptors.AddNew();
    }

    public Properties AddPropertyGroupInstance(Properties properties) {
        if (super.getReference() != null && this.PropertyDescriptors.size() >= 1) {
            return null;
        }
        if (properties == null) {
            return this.addNewProperties();
        }
        this.PropertyDescriptors.Add(properties);
        return properties;
    }
}
