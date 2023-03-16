package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.utils.simu.IProjectOperator;

/**
 *
 */
public class StartView extends AbsBaseItemView {
    private SomViewUI somViewUi;

    public StartView(ProjectManager projectManager) {
        this.somViewUi = new SomViewUI(projectManager);
    }

    public SomViewUI getSomViewUI() {
        return this.somViewUi;
    }

    @Override
    protected Object CreateHostView(IProjectOperator project) {
        if (project != null && project.PrimaryViewContainer() != null) {
            return project.PrimaryViewContainer().createView(this.somViewUi);
        }
        return null;
    }

    @Override
    protected String ViewTitleFormat() {
        return Resources.StartViewTitle;
    }

    @Override
    public ItemTypeEnum ItemType() {
        return ItemTypeEnum.Four;
    }

    @Override
    public int ViewType() {
        return 1;
    }

    @Override
    public IViewInfo ViewUI() {
        return this.somViewUi;
    }
}
