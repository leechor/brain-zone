package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.ItemTypeEnum;
import com.zdpx.cctpp.simioEnums.SymbolViewType;

/**
 *
 */
public class SymbolViewTypeView extends AbsView<SymbolViewType>{
    @Override
    protected SymbolViewType NoneType() {
        return null;
    }

    @Override
    protected SymbolViewType ToVal(int index) {
        return null;
    }

    @Override
    protected ItemTypeEnum ItemType() {
        return null;
    }

    public SymbolViewTypeView(SimioProjectManager simioProjectManager) {
        super(simioProjectManager);
    }

    @Override
    protected int modelViewTypeToInt(SymbolViewType target) {
		return target.ordinal();
    }

    @Override
    public void Dispose() {

    }
}
