package org.licho.brain.concrete;

/**
 *
 */
public interface IGridItemPropertyObject {
	GridItemProperty GetGridItemProperty(PropertyDefinitions definitions);

	void AlternateGetGridItemProperties(PropertyDefinitions definitions, GridItemProperties gridItemProperties);

	void UpdateGridPropertyValue(int param0, Object param1);

	String[] GetListValues();
}
