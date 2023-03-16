package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.brainEnums.AppViewType;

import java.util.ArrayList;

/**
 *
 */
public class AppViewNone extends AbsView<AppViewType> {
    public final SomViewUI SomViewUi;
    public final SimBitsViewUI SimBitsViewUi;

    public AppViewNone(ProjectManager projectManager) {
        super(projectManager);
        StartView startView = new StartView(projectManager);
        this.SomViewUi = startView.getSomViewUI();
        SimBitsView simBitsView = new SimBitsView(projectManager);
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
