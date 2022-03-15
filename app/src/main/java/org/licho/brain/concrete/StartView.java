package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.utils.simu.IProject;

/**
 *
 */
public class StartView extends AbsBaseItemView {
    private SomViewUI somViewUi;

    public StartView(SimioProjectManager simioProjectManager) {
        this.somViewUi = new SomViewUI(simioProjectManager);
    }

    public SomViewUI getSomViewUI() {
        return this.somViewUi;
    }

    @Override
    protected Object CreateHostView(IProject project) {
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
