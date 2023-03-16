package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.utils.simu.IProjectOperator;
import org.licho.brain.utils.simu.system.IDisposable;

import java.text.MessageFormat;

/**
 *
 */
public abstract class AbsBaseItemView implements IDisposable, IItemView {
    public static String string_1 = "__HOST_VIEW_NOT_FOUND__";
    protected String hostView;

    protected abstract Object CreateHostView(IProjectOperator project);

    protected  void OnCreatedHostView(){};

    protected abstract String ViewTitleFormat();

    public abstract ItemTypeEnum ItemType();

    public abstract int ViewType();

    @Override
    public Object HostView() {
        return this.hostView;
    }

    @Override
    public boolean OpenIfClosed() {
        return false;
    }

    @Override
    public boolean IsSubView() {
        return false;
    }

    public Object CreateViewIfNeeded(IProjectOperator project) {
        if (this.hostView == null) {
            this.hostView = (String) this.CreateHostView(project);
            if (this.hostView == null) {
                this.hostView = AbsBaseItemView.string_1;
            }
            this.OnCreatedHostView();
            return this.hostView;
        }
        return null;
    }

    protected IViewControl ViewContainerFrom(IProjectOperator project) {
        if (project != null) {
            return project.PrimaryViewContainer();
        }
        return null;
    }

    public abstract IViewInfo ViewUI();

    @Override
    public void ShowView(IProjectOperator project) {
        this.CreateViewIfNeeded(project);
        IViewControl viewControl = this.ViewContainerFrom(project);
        if (viewControl != null) {
            viewControl.ShowView(this.HostView());
        }
    }

    @Override
    public void CloseView(IProjectOperator project) {
        if (this.hostView != null) {
            if (project != null) {
                IViewControl viewControl = this.ViewContainerFrom(project);
                if (viewControl != null) {
                    viewControl.CloseView(this.hostView);
                }
            }
            this.hostView = null;
        }
    }

    @Override
    public void UpdateName(IProjectOperator project, String name) {
        if (this.hostView != null) {
            IViewControl viewControl = this.ViewContainerFrom(project);
            if (viewControl != null) {
                viewControl.UpdateName(this.hostView, MessageFormat.format(this.ViewTitleFormat(), name));
            }
        }
    }

    @Override
    public boolean CheckCloseView(Object view) {
        if (view == this.hostView) {
            this.hostView = null;
            return true;
        }
        return false;
    }

    @Override
    public boolean RemoveViewOnClose() {
        return false;
    }

    @Override
    public void Dispose() {
        this.Dispose(true);
    }

    protected void Dispose(boolean param0) {
    }
}
