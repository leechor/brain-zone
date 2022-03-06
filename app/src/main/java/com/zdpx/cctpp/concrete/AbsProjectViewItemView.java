package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.ItemTypeEnum;

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
