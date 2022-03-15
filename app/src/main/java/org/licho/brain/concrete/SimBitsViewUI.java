package org.licho.brain.concrete;

/**
 *
 */
public class SimBitsViewUI implements IViewInfo {
    private SimioProjectManager simioProjectManager;

    public SimBitsViewUI(SimioProjectManager simioProjectManager) {
        this.simioProjectManager = simioProjectManager;
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
