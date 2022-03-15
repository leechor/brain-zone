package org.licho.brain.concrete;

/**
 *
 */
public class ProjectViewItemView extends AbsProjectViewItemView {
    public ProjectViewItemView() {
        super(null);
    }


    @Override
    protected String ViewTitleFormat() {
        return Resources.ProjectViewTitle;
    }

    @Override
    protected String vmethod_1() {
        return null;
    }

    @Override
    public int ViewType() {
        return 1;
    }
}
