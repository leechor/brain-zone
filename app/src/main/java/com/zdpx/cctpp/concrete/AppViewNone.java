package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.ItemTypeEnum;
import com.zdpx.cctpp.simioEnums.AppViewType;

import java.util.ArrayList;

/**
 *
 */
public class AppViewNone extends AbsView<AppViewType> {
    public final SomViewUI SomViewUi;
    public final SimBitsViewUI SimBitsViewUi;

    public AppViewNone(SimioProjectManager simioProjectManager) {
        super(simioProjectManager);
        StartView startView = new StartView(simioProjectManager);
        this.SomViewUi = startView.getSomViewUI();
        SimBitsView simBitsView = new SimBitsView(simioProjectManager);
        this.SimBitsViewUi = simBitsView.SimBitsViewUI();
        this.ProjectViews = new ArrayList<>();
        this.ProjectViews.add(startView);
        this.ProjectViews.add(simBitsView);
    }

    @Override
    protected int modelViewTypeToInt(AppViewType target) {
        return target.ordinal();
    }

    @Override
    protected AppViewType ToVal(int index) {
        return AppViewType.values()[index];
    }

    @Override
    protected AppViewType NoneType() {
        return AppViewType.None;
    }

    @Override
    protected ItemTypeEnum ItemType() {
        return ItemTypeEnum.Four;
    }

}
