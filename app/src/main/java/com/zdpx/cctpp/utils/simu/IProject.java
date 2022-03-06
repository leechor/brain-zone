package com.zdpx.cctpp.utils.simu;


import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.IView;
import com.zdpx.cctpp.concrete.IViewControl;
import com.zdpx.cctpp.concrete.enu.Enum82;

import java.io.InputStream;

/**
 *
 */
public interface IProject {
    ILog Log();

    IView StaticViewContainer();

    InputStream CreateReadStream(String fileName);

    void NotifyError(String error);

    boolean AskYesNoQuestion(String param0);

    Enum82 AskYesNoCancelQuestion(String askSaveCurrentProject);

    Object startTimer(int timeInterval, Object target, Action<Object> eventMethod);

    void CancelCountdown(Object actionTarget);

    IViewControl PrimaryViewContainer();
}
