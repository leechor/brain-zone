package org.licho.brain.concrete;

import org.licho.brain.enu.ItemTypeEnum;
import org.licho.brain.brainEnums.ProjectViewType;

import java.util.ArrayList;

/**
 *
 */
public class ProjectViewTypeView extends AbsView<ProjectViewType> {
    @Override
    protected ProjectViewType NoneType() {
        return ProjectViewType.None;
    }

    @Override
    protected ProjectViewType ToVal(int index) {
        return ProjectViewType.values()[index];
    }

    @Override
    protected ItemTypeEnum ItemType() {
        return ItemTypeEnum.values()[3];
    }

    public ProjectViewTypeView(ProjectManager projectManager) {
        super(projectManager);
        ProjectViewItemView projectViewItemView = new ProjectViewItemView();
//		ModelsViewItemView modelsViewItemView = new ModelsViewItemView(projectViewItemView, simioProjectManager,
//		simioProjectManager.Project.SimioProject);
//		this.FragmentLicenseInfo = modelsViewItemView.method_0();
//		ExperimentsViewItemView experimentsViewItemView = new ExperimentsViewItemView(projectViewItemView,
//		simioProjectManager, simioProjectManager.Project.SimioProject);
//		this.ExperimentsView = experimentsViewItemView.method_0();
//		SymbolsViewItemView symbolsViewItemView = new SymbolsViewItemView(projectViewItemView, simioProjectManager,
//		simioProjectManager.Project.SimioProject);
//		this.SymbolsView = symbolsViewItemView.method_0();
//		TexturesViewItemView texturesViewItemView = new TexturesViewItemView(projectViewItemView, simioProjectManager,
//		simioProjectManager.Project.SimioProject);
//		this.TexturesView = texturesViewItemView.method_0();
//		PathDecoratorsViewItemView pathDecoratorsViewItemView = new PathDecoratorsViewItemView(projectViewItemView,
//		simioProjectManager, simioProjectManager.Project.SimioProject);
//		this.PathDecoratorsView = pathDecoratorsViewItemView.method_0();
//		this.ProjectViews = List.of(
//			projectViewItemView,
//			modelsViewItemView,
//			experimentsViewItemView,
//			symbolsViewItemView,
//			texturesViewItemView,
//			pathDecoratorsViewItemView);
//
//		this.CurrentSearchView = new CurrentSearchView(simioProjectManager, simioProjectManager.Project.SimioProject);
        this.ProjectViews = new ArrayList<>();
    }

    @Override
    protected int modelViewTypeToInt(ProjectViewType target) {
        return target.ordinal();
    }


    @Override
    public void Dispose() {

    }
}
