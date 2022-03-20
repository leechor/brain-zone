package org.licho.brain.concrete;

import org.licho.brain.api.IRow;
import org.licho.brain.api.IRows;
import org.licho.brain.utils.simu.IValues;
import org.licho.brain.utils.simu.system.ITypedList;
import org.licho.brain.utils.simu.system.ListChangedEventArgs;
import org.licho.brain.utils.simu.system.ListChangedType;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.util.Iterator;

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
                ((RepeatingPropertyDefinition)(this.repeatStringPropertyRow.getStringPropertyDefinition())).propertyDefinitions;
		switch (e.ListChangedType())
		{
		case ItemAdded:
		{
			StringPropertyDefinition stringPropertyDefinition = propertyDefinitions.get(e.NewIndex());
            try {
                this.OnListChanged(new ListChangedEventArgs(ListChangedType.PropertyDescriptorAdded, new GridObjectDefinitionPropertyDescriptor(stringPropertyDefinition)));
            } catch (IntrospectionException ex) {
                ex.printStackTrace();
            }
            return;
		}
		case ItemDeleted:
			this.OnListChanged(new ListChangedEventArgs(ListChangedType.PropertyDescriptorDeleted, e.NewIndex()));
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
