package org.licho.brain.concrete;

import org.licho.brain.utils.simu.system.ITypedList;

import java.beans.PropertyDescriptor;

/**
 *
 */
public class PropertiesTypeList extends BindingList<Properties> implements ITypedList {
    private final Table table;

    public PropertiesTypeList(Table table) {
        this.table = table;
        this.reloadTableData();
        if (this.table != null) {
//            this.table.Data().Rows().ListChanged += this.ListChangedHandler;
        }
    }

    private void reloadTableData() {
        this.ClearItems();
        if (this.table != null) {
            for (Properties item : this.table.Data().Rows().values) {
                super.add(item);
            }
        }
    }

    @Override
    public String GetListName(PropertyDescriptor[] listAccessors) {
        return null;
    }

    @Override
    public PropertyDescriptorCollection GetItemProperties(PropertyDescriptor[] listAccessors) {
        return null;
    }

    public void reloadTableDataOuter() {
        this.reloadTableData();
    }
}
