package org.licho.brain.concrete;

import org.licho.brain.concrete.enu.Enum86;
import org.licho.brain.utils.simu.IProjectOperator;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public abstract class AbsSubItemView extends AbsBaseSubItemView {
    private IViewControl viewControl;
    private List<AbsBaseSubItemView> absBaseSubItemViews = new ArrayList<>();
    private boolean bool_0;

    public IViewControl getViewControl() {
        return this.viewControl;
    }

    public List<AbsBaseSubItemView> getAbsBaseSubItemViews() {
        return this.absBaseSubItemViews;
    }

    public AbsSubItemView(AbsSubItemView absSubItemView) {
        super(absSubItemView);
    }

    @Override
    public Object CreateViewIfNeeded(IProjectOperator project) {
        Object obj = null;
        if (this.hostView == null) {
            super.CreateViewIfNeeded(project);
            for (AbsBaseSubItemView absBaseSubItemView : this.absBaseSubItemViews) {
                Object obj2 = absBaseSubItemView.CreateSubViewIfNeeded(project);
                if (obj == null && obj2 != null) {
                    obj = obj2;
                }
            }
        }
        return obj;
    }

    @Override
    public Object CreateSubViewIfNeeded(IProjectOperator project) {
        return this.CreateViewIfNeeded(project);
    }

    @Override
    public void UpdateSubViewName(IProjectOperator project, String name) {
        this.updateProjectViewName(project, name);
    }


    protected Enum86 vmethod_0() {
        return Enum86.Zero;
    }


    @Override
    protected Object CreateHostView(IProjectOperator project) {
        if (super.Parent() == null && project != null && project.PrimaryViewContainer() != null) {
            return this.CreateViewUsing(project.PrimaryViewContainer());
        }
        return super.CreateHostView(project);
    }

    @Override
    protected Object CreateViewUsing(IViewControl viewControl) {
        this.viewControl = viewControl.imethod_0(this.vmethod_0(), this.ItemType(), this.ViewType(), this.vmethod_1());
        return this.viewControl;
    }

    @Override
    public void CloseView(IProjectOperator project) {
        List<AbsBaseSubItemView> absBaseSubItemViews = new ArrayList<>(this.absBaseSubItemViews);
        for (AbsBaseSubItemView absBaseSubItemView : absBaseSubItemViews) {
            absBaseSubItemView.CloseView(project);
        }
        super.CloseView(project);
    }

    @Override
    public void UpdateName(IProjectOperator param0, String param1) {
        this.updateProjectViewName(param0, param1);
    }

    private void updateProjectViewName(IProjectOperator project, String name) {
        if (!this.bool_0) {
            this.bool_0 = true;
            super.UpdateName(project, name);
            for (AbsBaseSubItemView absBaseSubItemView : this.absBaseSubItemViews) {
                absBaseSubItemView.UpdateSubViewName(project, name);
            }
            this.bool_0 = false;
        }
    }

    protected abstract String vmethod_1();

    @Override
    public IViewInfo ViewUI() {
        return new AbsSubItemView.SubViewInfo();
    }

    public class SubViewInfo implements IViewInfo {
        @Override
        public Iterable<Object> SelectedObjects() {
            return null;
        }

        @Override
        public GridObjectDefinition SelectionContext() {
            return null;
        }

        @Override
        public String Description() {
            return null;
        }

        @Override
        public boolean OwnsObject(Object param0) {
            return false;
        }

        @Override
        public void SelectObject(Object param0, String param1) {

        }
    }
}
