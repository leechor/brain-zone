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
    public void initContextBound(IntelligentObjectDefinition intelligentObjectDefinition) {
        super.initContextBound(intelligentObjectDefinition);
        if (intelligentObjectDefinition.getAnimationSetup() == null) {
            AnimationSetup animationSetup = new AnimationSetup(intelligentObjectDefinition, null);
            intelligentObjectDefinition.setAnimationSetup(animationSetup);
            if (intelligentObjectDefinition.activeModel != null) {
                animationSetup.setActiveModel(intelligentObjectDefinition.activeModel);
            }
        }
        if (intelligentObjectDefinition.getDefaultAdditionalSymbol() == null) {
            DefaultAdditionalSymbol defaultAdditionalSymbol = new DefaultAdditionalSymbol(intelligentObjectDefinition
					, null);
            intelligentObjectDefinition.DefaultAdditionalSymbolNotifyOtherContextBound(defaultAdditionalSymbol);
            if (intelligentObjectDefinition.activeModel != null) {
                defaultAdditionalSymbol.setActiveModel(intelligentObjectDefinition.activeModel);
            }
        }
        if (intelligentObjectDefinition.getDashboard() == null) {
            Dashboard dashboard = new Dashboard(intelligentObjectDefinition, null);
            intelligentObjectDefinition.DashboardNotifyOtherContextBound(dashboard);
            if (intelligentObjectDefinition.activeModel != null) {
                dashboard.setActiveModel(intelligentObjectDefinition.activeModel);
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
