package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.ItemTypeEnum;
import com.zdpx.cctpp.simioEnums.ModelViewType;

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
