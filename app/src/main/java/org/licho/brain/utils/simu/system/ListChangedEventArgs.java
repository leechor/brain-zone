package org.licho.brain.utils.simu.system;


import org.licho.brain.event.EventArgs;

/**
 *
 */
public class ListChangedEventArgs extends EventArgs {
    private ListChangedType listChangedType;
    private int newIndex;
    private int oldIndex;
    private PropertyDescriptor propDesc;

    public ListChangedEventArgs(ListChangedType listChangedType, int newIndex) {
        this(listChangedType, newIndex, -1);
    }

    public ListChangedEventArgs(ListChangedType listChangedType, int newIndex, PropertyDescriptor propDesc) {
        this(listChangedType, newIndex);
        this.propDesc = propDesc;
        this.oldIndex = newIndex;
    }

    public ListChangedEventArgs(ListChangedType listChangedType, PropertyDescriptor propDesc) {

        this.listChangedType = listChangedType;
        this.propDesc = propDesc;
    }

    public ListChangedEventArgs(ListChangedType listChangedType, int newIndex, int oldIndex) {
        this.listChangedType = listChangedType;
        this.newIndex = newIndex;
        this.oldIndex = oldIndex;
    }

    public ListChangedType ListChangedType() {
        return listChangedType;
    }

    public int NewIndex() {
        return newIndex;
    }

    public int OldIndex() {
        return oldIndex;
    }

    public PropertyDescriptor PropertyDescriptor() {
        return propDesc;
    }
}

