package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.simuApi.IRow;
import com.zdpx.cctpp.simuApi.IRows;
import com.zdpx.cctpp.utils.simu.IValues;
import com.zdpx.cctpp.utils.simu.system.ITypedList;
import com.zdpx.cctpp.utils.simu.system.ListChangedEventArgs;
import com.zdpx.cctpp.utils.simu.system.ListChangedType;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Iterator;

import static com.zdpx.cctpp.utils.simu.system.ListChangedType.PropertyDescriptorAdded;
import static com.zdpx.cctpp.utils.simu.system.ListChangedType.PropertyDescriptorDeleted;

/**
 *
 */
public class PropertyDescriptors extends BindingList<Properties> implements ITypedList, IRows, IValues {
    private RepeatStringPropertyRow repeatStringPropertyRow;

    public PropertyDescriptors(RepeatStringPropertyRow repeatStringPropertyRow) {
        this.repeatStringPropertyRow = repeatStringPropertyRow;
        this.init();
        super.AllowNew(true);
        super.AllowRemove(true);
        super.AllowEdit(true);
    }

    private void init() {

        var tmp = this.repeatStringPropertyRow.getPropertyObject().objectDefinition;
        if (tmp instanceof TableDefinition) {
            TableDefinition tableDefinition = (TableDefinition) tmp;
            tableDefinition.Group().addListChanged(this::onListChanged);
        }
    }

    	private void onListChanged(Object sender, ListChangedEventArgs e)
	{
		PropertyDefinitions propertyDefinitions =
                ((RepeatingPropertyDefinition)(this.repeatStringPropertyRow.getStringPropertyDefinitionInfo())).propertyDefinitions;
		switch (e.ListChangedType())
		{
		case ItemAdded:
		{
			StringPropertyDefinition stringPropertyDefinition = propertyDefinitions.get(e.NewIndex());
            try {
                this.OnListChanged(new ListChangedEventArgs(PropertyDescriptorAdded, new GridObjectDefinitionPropertyDescriptor(stringPropertyDefinition)));
            } catch (IntrospectionException ex) {
                ex.printStackTrace();
            }
            return;
		}
		case ItemDeleted:
			this.OnListChanged(new ListChangedEventArgs(PropertyDescriptorDeleted, e.NewIndex()));
			return;
		case ItemMoved:
			break;
		case ItemChanged:
			this.OnListChanged(new ListChangedEventArgs(ListChangedType.PropertyDescriptorChanged, e.NewIndex()));
			break;
		default:
			return;
		}
	}

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IRow getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IRow> iterator() {
        return null;
    }

    @Override
    public String GetListName(PropertyDescriptor[] listAccessors) {
        return null;
    }

    @Override
    public PropertyDescriptorCollection GetItemProperties(PropertyDescriptor[] listAccessors) {
        return null;
    }
}
