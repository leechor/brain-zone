package com.zdpx.cctpp.concrete;

/**
 *
 */
public class SomViewUI implements IViewInfo {
    private SimioProjectManager simioProjectManager;

    public SomViewUI(SimioProjectManager simioProjectManager) {
        this.simioProjectManager = simioProjectManager;
    }

    public SimioProjectManager getSimioProjectManager() {
        return this.simioProjectManager;
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
