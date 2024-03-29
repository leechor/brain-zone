package org.licho.brain.concrete;

import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public class ActionRun {
    private final Runnable action;
    private int waitCount;

    public ActionRun(Runnable action) {
        this.action = action;
    }

    public boolean empty() {
        return this.waitCount > 0;
    }

    public void waitAction() {
        this.waitCount++;
    }

    public void run() {
        this.waitCount--;
        if (this.empty() && this.action != null) {
            this.action.run();
        }
    }

    public IDisposable dispose() {
        return new ActionRunWrapper(this);
    }

    public IDisposable disposable() {
        return new ActionRun.ActionRunBuilder(this);
    }

    private static class ActionRunWrapper implements IDisposable {
        private final ActionRun action;

        public ActionRunWrapper(ActionRun action) {
            this.action = action;
        }

        public void Dispose() {
            this.action.run();
        }
    }

    public class ActionRunBuilder implements IDisposable {
        private final ActionRun actionRun;

        public ActionRunBuilder(ActionRun actionRun) {
            this.actionRun = actionRun;
            this.actionRun.waitAction();
        }

        public void Dispose() {
            this.actionRun.run();
        }
    }
}
