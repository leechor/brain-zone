package org.licho.brain.utils.simu;


import org.licho.brain.IFunction.Action;
import org.licho.brain.IView;
import org.licho.brain.concrete.IViewControl;
import org.licho.brain.concrete.enu.Enum82;

import java.io.InputStream;

/**
 *
 */
public interface IProjectOperator {
    ILog Log();

    IView StaticViewContainer();

    InputStream CreateReadStream(String fileName);

    void NotifyError(String error);

    boolean AskYesNoQuestion(String param0);

    Enum82 AskYesNoCancelQuestion(String askSaveCurrentProject);

    Object startTimer(int timeInterval, Object target, Action<Object> eventMethod);

    void cancelCountdown(Object actionTarget);

    IViewControl PrimaryViewContainer();
}
