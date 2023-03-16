package org.licho.brain.concrete;

/**
 *
 */
public class SomViewUI implements IViewInfo {
    private final ProjectManager projectManager;

    public SomViewUI(ProjectManager projectManager) {
        this.projectManager = projectManager;
    }

    public ProjectManager getSimioProjectManager() {
        return this.projectManager;
    }


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
