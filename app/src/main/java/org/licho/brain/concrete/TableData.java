package org.licho.brain.concrete;

import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public class TableData {
    private final TableInfo tableInfo;
    private final PropertyDescriptors propertyDescriptors;

    TableData(Schema schema, String name, IntelligentObjectDefinition intelligentObjectDefinition) {
        this.tableInfo = (TableInfo) schema.BaseDefinition().CreateInstance(name);
        this.tableInfo.Parent(intelligentObjectDefinition);
        this.propertyDescriptors =
                ((RepeatStringPropertyRow) this.tableInfo.properties.values.get(0)).getPropertyDescriptors();
    }

    public PropertyDescriptors Rows() {
        return this.propertyDescriptors;
    }

    public IDisposable dispose() {
        return this.tableInfo.dispose();
    }

    public AbsPropertyObject getTableProperty() {
        return this.tableInfo;
    }

    public void resetRowsBindings() {
        this.Rows().ResetBindings();
        ;
    }

    public String Name() {
        return this.tableInfo.InstanceName();
    }

    public void Name(String value) {
        this.tableInfo.InstanceName(value);
    }
}
