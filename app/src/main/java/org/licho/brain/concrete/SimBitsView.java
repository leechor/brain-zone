package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.utils.simu.IProject;

/**
 *
 */
public class SimBitsView extends AbsBaseItemView{

	public SimBitsView(ProjectManager projectManager)
	{
		this.simBitsViewUi = new SimBitsViewUI(projectManager);
	}

	public SimBitsViewUI SimBitsViewUI()
	{
			return this.simBitsViewUi;
	}

    @Override
	protected  Object CreateHostView(IProject project)
	{
		if (project != null && project.PrimaryViewContainer() != null)
		{
			return project.PrimaryViewContainer().imethod_59(this.simBitsViewUi);
		}
		return null;
	}

    @Override
	protected  String ViewTitleFormat()
	{
			return Resources.SimBitsViewTitle;
	}

    @Override
	public ItemTypeEnum ItemType()
	{
			return ItemTypeEnum.Four;
	}

    @Override
	public  int ViewType()
	{
			return 2;
	}

    @Override
	public  IViewInfo ViewUI()
	{
			return this.simBitsViewUi;
	}

	private SimBitsViewUI simBitsViewUi;

}
