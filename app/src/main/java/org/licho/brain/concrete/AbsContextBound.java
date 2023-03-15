package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.utils.simu.IContextBound;
import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public class AbsContextBound extends DefaultFlowMaterial implements IDisposable, IContextBound {
    private int minimumNumberOfFramesToDisplayIdleAnimation;
    private IntelligentObjectDefinition selectionContext;
    private ActiveModel activeModel;
    private ActionRun actionRun;
    private Symbol symbol;

    public AbsContextBound(IntelligentObjectDefinition assigner, Interface197 param1) {
        super();
        Action action = null;
        this.minimumNumberOfFramesToDisplayIdleAnimation = 10;
        if (assigner == null) {
            throw new IllegalArgumentException();
        }
        this.selectionContext = assigner;
        this.actionRun = new ActionRun(() -> {
            this.OnLoadFinished();
            this.InvalidateSymbol();
            super.method_45(null, null, true, false);
        });
    }

    protected void InvalidateSymbol() {
        if (this.symbol != null) {
            this.symbol.Dispose();
            this.symbol = null;
        }
    }

    public void OnLoadFinished() {
    }


    @Override
    public void UpdateErrors() {

    }

    @Override
    public IDisposable LoadTransaction() {
        return this.actionRun.disposable();
    }

    @Override
    public void NotifyOtherContextBound(IContextBound contextBound) {

    }

    @Override
    public void Dispose() {

    }

    public void setActiveModel(ActiveModel activeModel) {
        if (this.activeModel != activeModel) {
            this.activeModel = activeModel;
            // TODO: 2022/1/27
//            if (this.activeModel != null && this.Animates) {
//                this.activeModel.Event_1 += this.method_91;
//                this.activeModel.Event_7 += this.method_92;
//                this.activeModel.Event_2 += this.method_93;
//            }
        }
    }
}
