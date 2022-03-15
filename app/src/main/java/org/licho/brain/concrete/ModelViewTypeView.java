package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.brainEnums.ModelViewType;

/**
 *
 */
public class ModelViewTypeView extends AbsView<ModelViewType>{
    public BaseViewInfo BaseSelectObject;

    public ModelViewTypeView(SimioProjectManager simioProjectManager, ActiveModel activeModel) {
        super(simioProjectManager);

    }

    @Override
    public void Dispose() {

    }

    @Override
    protected ModelViewType NoneType() {
        return null;
    }

    @Override
    protected ModelViewType ToVal(int index) {
        return null;
    }

    @Override
    protected ItemTypeEnum ItemType() {
        return null;
    }

    @Override
    protected int modelViewTypeToInt(ModelViewType target) {
        return 0;
    }


}
