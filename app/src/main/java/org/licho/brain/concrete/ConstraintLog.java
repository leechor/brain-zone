package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IConstraintLog;
import org.licho.brain.utils.simu.system.ITypedList;

import java.beans.PropertyDescriptor;

/**
 *
 */
public class ConstraintLog extends BindingList<ConstraintRecord> implements IConstraintLog, ITypedList {
    private final ActiveModel activeModel;

    public ConstraintLog(ActiveModel activeModel) {
        this.activeModel = activeModel;

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
