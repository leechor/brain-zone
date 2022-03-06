package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.ItemTypeEnum;
import com.zdpx.cctpp.utils.simu.IProject;
import com.zdpx.cctpp.utils.simu.system.IDisposable;

import java.util.List;

/**
 *
 */
public abstract class AbsView<T> implements IDisposable {

    private SimioProjectManager simioProjectManager;

    protected abstract T NoneType();

    protected abstract T ToVal(int index);

    protected abstract ItemTypeEnum ItemType();


    public AbsView(SimioProjectManager simioProjectManager) {
        this.simioProjectManager = simioProjectManager;
    }

    protected abstract int modelViewTypeToInt(T target);

    protected List<IItemView> ProjectViews;


    public Object getViewByType(T type) {
        int num = this.modelViewTypeToInt(type);
        for (int i = 0; i < this.ProjectViews.size(); i++) {
            IItemView itemView = this.ProjectViews.get(i);
            if (itemView.ViewType() == num) {
                return itemView.HostView();
            }
        }
        return null;
    }

    public Object createView(T type, IProject project, String name) {
        return this.createView(type, project, name, false);
    }

    public Object createView(T type, IProject project, String name, boolean param3) {
        IItemView view = this.ProjectViews.stream()
                .filter((IItemView itemView) -> itemView.ViewType() == this.modelViewTypeToInt(type))
                .findFirst().orElse(null);
        if (view == null) {
            return null;
        }
        Object obj = view.CreateViewIfNeeded(project);
        if (obj != null) {
            if (obj == AbsBaseItemView.string_1 && param3) {
                view.CloseView(project);
                obj = null;
            } else {
                view.UpdateName(project, name);
            }
        }
        if (obj != null) {
            return obj;
        }
        return view.HostView();
    }

    public void updateViewsName(IProject project, String name) {
        this.ProjectViews.forEach(view -> view.UpdateName(project, name));
    }

    @Override
    public void Dispose() {
        this.Dispose(true);
    }

    protected void Dispose(boolean param0) {
        if (param0) {
            for (IItemView itemView : this.ProjectViews) {

                if (itemView.ViewUI() instanceof IDisposable) {
                    IDisposable disposable = (IDisposable) itemView.ViewUI();
                    disposable.Dispose();
                }
            }
            this.ProjectViews.clear();
        }
    }

    public void showView(Object param, IProject project) {
        // TODO: 2022/2/11 
    }
}
