package org.licho.brain.concrete;

import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.utils.simu.system.Color;

/**
 *
 */
public interface IPropertyUpdateOperator {
	IPropertyStatus PropertySource(IPropertyStatus value);
	void Clear();
	Object AddProperty(String param0, String param1, int param2, Object param3, Class<?> param4, Object param5,
                       Object param6, PropertyGridFeel param7, boolean param8, boolean param9);
	void RemoveProperty(Object param0);
	Object AddCategory(String param0, String param1, Object param2, boolean param3);
	void SetPresentation(Object param0, boolean param1, boolean param2, boolean param3, boolean param4, int param5,
                         Color param6);
	void SelectProperty(Object param0, boolean param1);
	void UpdateProperty(Object param0, Object param1);
	void BeginUpdate();
	void EndUpdate(Object param0);
	Object GetStates();

}
