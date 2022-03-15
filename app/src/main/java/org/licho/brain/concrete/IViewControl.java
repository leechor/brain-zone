package org.licho.brain.concrete;

import org.licho.brain.concrete.enu.Enum86;
import org.licho.brain.enu.ItemTypeEnum;

/**
 *
 */
public interface IViewControl {
    Object imethod_59(SimBitsViewUI simBitsViewUi);

    Object createView(SomViewUI somViewUi);

	IViewControl imethod_0(Enum86 param0, ItemTypeEnum param1, int viewType, String param3);

    void ShowView(Object hostView);

    void CloseView(String hostView);

    void UpdateName(String hostView, String format);
}
