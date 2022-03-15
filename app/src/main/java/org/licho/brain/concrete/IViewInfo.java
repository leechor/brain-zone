package org.licho.brain.concrete;

/**
 *
 */
public interface IViewInfo {
	Iterable<Object> SelectedObjects();
	GridObjectDefinition SelectionContext();
	String Description();
	boolean OwnsObject(Object param0);
	void SelectObject(Object param0, String param1);

}
