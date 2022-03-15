package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.IView;
import org.licho.brain.concrete.enu.Enum82;
import org.licho.brain.concrete.fake.Timer;
import org.licho.brain.utils.simu.ILog;
import org.licho.brain.utils.simu.IProject;
import org.licho.brain.utils.simu.system.IDisposable;

import java.io.InputStream;
import java.util.TimerTask;

/**
 *
 */
public class Project implements IDisposable, IProject {

    private IView view;
    private Control control;

    @Override
    public ILog Log() {
        return null;
    }

    @Override
    public IView StaticViewContainer() {
        return this.view;
    }

    @Override
    public InputStream CreateReadStream(String fileName) {
        return FileStreamOperator.CreateReadStream(fileName);
    }

    @Override
    public void NotifyError(String error) {

    }

    @Override
    public boolean AskYesNoQuestion(String param0) {
        return true;
    }

    @Override
    public Enum82 AskYesNoCancelQuestion(String askSaveCurrentProject) {
        return null;
    }

    @Override
    public Object startTimer(int timeInterval, Object target, Action<Object> eventMethod) {
        if (timeInterval <= 0) {
            this.DeferExecution(() -> eventMethod.apply(target));
            return this;
        }
        Project.TargetTimerAction targetTimerAction = new Project.TargetTimerAction(timeInterval, target, eventMethod);
        targetTimerAction.enableTimer();
        return targetTimerAction;
    }

    public void CancelCountdown(Object target) {
        if (target instanceof Project.TargetTimerAction) {
            Project.TargetTimerAction targetTimerAction = (Project.TargetTimerAction) target;
            targetTimerAction.unEnabledTimer();
        }
    }

    @Override
    public IViewControl PrimaryViewContainer() {
        return null;
    }


    public void DeferExecution(Runnable action) {
        if (this.control != null) {
            this.control.BeginInvoke(action::run);
            return;
        }

        action.run();
    }

    @Override
    public void Dispose() {

    }

    public static class TargetTimerAction implements IDisposable {
        private Timer timer;
        private Object target;
        private Action<Object> action;

        public TargetTimerAction(int timeInterval, Object targetParam, Action<Object> eventMethod) {
            this.target = targetParam;
            this.action = eventMethod;
            this.timer = new Timer();
            this.timer.Interval = timeInterval;
            timer.timerTask = new TimerTask() {
                @Override
                public void run() {
                    action.apply(target);
                }
            };
        }

        public void enableTimer() {
            if (this.timer != null) {
                this.timer.Enabled(true);
            }
        }

        public void unEnabledTimer() {
            if (this.timer != null) {
                this.timer.Enabled(false);
            }
            this.dispose();
        }

        private void dispose() {
            if (this.timer != null) {
                this.timer.Dispose();
                this.timer = null;
            }
        }


        @Override
        public void Dispose() {
            this.dispose();
        }
    }
}
