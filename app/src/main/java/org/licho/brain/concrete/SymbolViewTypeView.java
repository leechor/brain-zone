package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.brainEnums.SymbolViewType;

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

    public SymbolViewTypeView(ProjectManager projectManager) {
        super(projectManager);
    }

    @Override
    protected int modelViewTypeToInt(SymbolViewType target) {
		return target.ordinal();
    }

    @Override
    public void Dispose() {

    }
}
