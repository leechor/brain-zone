package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;

/**
 *
 */
public abstract class AbsProjectViewItemView extends AbsSubItemView {
    public AbsProjectViewItemView(AbsSubItemView absSubItemView) {
        super(absSubItemView);
    }

    @Override
    public ItemTypeEnum ItemType() {
        return ItemTypeEnum.Three;
    }
}
