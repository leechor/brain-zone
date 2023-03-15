package org.licho.brain.concrete;

import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public class Facade extends IntelligentObjectXml {

    public Facade() {
        super(IntelligentObjectXml.ModeType.Zero);
    }

    @Override
    public void initContextBound(IntelligentObjectDefinition assigner) {
        super.initContextBound(assigner);
        if (assigner.getAnimationSetup() == null) {
            AnimationSetup animationSetup = new AnimationSetup(assigner, null);
            assigner.setAnimationSetup(animationSetup);
            if (assigner.activeModel != null) {
                animationSetup.setActiveModel(assigner.activeModel);
            }
        }
        if (assigner.getDefaultAdditionalSymbol() == null) {
            DefaultAdditionalSymbol defaultAdditionalSymbol = new DefaultAdditionalSymbol(assigner
					, null);
            assigner.DefaultAdditionalSymbolNotifyOtherContextBound(defaultAdditionalSymbol);
            if (assigner.activeModel != null) {
                defaultAdditionalSymbol.setActiveModel(assigner.activeModel);
            }
        }
        if (assigner.getDashboard() == null) {
            Dashboard dashboard = new Dashboard(assigner, null);
            assigner.DashboardNotifyOtherContextBound(dashboard);
            if (assigner.activeModel != null) {
                dashboard.setActiveModel(assigner.activeModel);
            }
        }
    }

	@Override
		public IDisposable createFacadeWrapper()
	{
		return new Facade.FacadeWrapper(this);
	}

    public Symbol getSymbolByGuid(Guid guid) {
        // TODO: 2022/1/28 
        return null;
    }

    public AbsBaseMaterial getAbsBaseMaterialByGuid(Guid guid) {
        return null;
    }

    public Decorator getDecoratorByGuid(Guid guid) {
        // TODO: 2022/1/28 
        return null;
    }

    public class FacadeWrapper implements IDisposable {
		public FacadeWrapper(Facade facade)
		{
			this.facade = facade;
			this.facade.push();
		}

		public void Dispose()
		{
			this.facade.dispose();
		}

		private Facade facade;		}

	private void push() {
		// TODO: 2022/1/27
	}

	private void dispose() {
		// TODO: 2022/1/27 
	}

}
