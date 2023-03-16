package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IProjectOperator;

/**
 *
 */
public abstract class AbsBaseSubItemView extends AbsBaseItemView {
    private AbsSubItemView parent;

    protected AbsSubItemView Parent() {
        return this.parent;
    }

    public AbsBaseSubItemView(AbsSubItemView absSubItemView) {
        this.parent = absSubItemView;
        if (this.parent != null) {
            this.parent.getAbsBaseSubItemViews().add(this);
        }
    }

    @Override
    public Object CreateViewIfNeeded(IProjectOperator project) {
        if (this.Parent() != null) {
            this.Parent().CreateViewIfNeeded(project);
        }
        if (super.HostView() == null) {
            super.CreateViewIfNeeded(project);
        }
        return super.HostView();
    }

    public Object CreateSubViewIfNeeded(IProjectOperator project) {
        return super.CreateViewIfNeeded(project);
    }

    protected boolean UpdateNameForParent() {
        return true;
    }

    @Override
    public void UpdateName(IProjectOperator project, String name) {
        super.UpdateName(project, name);
        if (this.Parent() != null && this.UpdateNameForParent()) {
            this.Parent().UpdateName(project, name);
        }
    }

    public void UpdateSubViewName(IProjectOperator project, String name) {
        super.UpdateName(project, name);
    }

    @Override
    protected Object CreateHostView(IProjectOperator project) {
        if (this.Parent() != null && this.Parent().getViewControl() != null) {
            return this.CreateViewUsing(this.Parent().getViewControl());
        }
        return null;
    }

    protected abstract Object CreateViewUsing(IViewControl viewControl);

    @Override
    public void ShowView(IProjectOperator project) {
        if (this.parent != null) {
            this.parent.ShowView(project);
            if (this.parent.getViewControl() != null) {
                this.parent.getViewControl().ShowView(super.HostView());
            }
        }
        super.ShowView(project);
    }


    @Override
    protected IViewControl ViewContainerFrom(IProjectOperator project) {
        if (this.parent != null) {
            return this.parent.getViewControl();
        }
        return super.ViewContainerFrom(project);
    }

    @Override
    public boolean IsSubView() {
        return this.parent != null;
    }

}
