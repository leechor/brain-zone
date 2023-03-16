package org.licho.brain.example.war;

import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.IntelligentObjectDefinitionFactory;
import org.licho.brain.concrete.LibraryInfo;
import org.licho.brain.concrete.ProjectOperator;
import org.licho.brain.concrete.ProjectDefinition;
import org.licho.brain.concrete.BaseProjectDefinition;
import org.licho.brain.concrete.ProjectManager;
import org.licho.brain.concrete.StepDefinitionWrapper;
import org.licho.brain.enu.StandardType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author licho
 * @since 2021/10/21
 */
public class Game {

    private void initStartSimioProjectManager(BaseProjectDefinition baseProjectDefinition) {
        List<LibraryInfo> libraryInfos = new ArrayList<>();
        libraryInfos.add(this.loadStandardLibrary());
        libraryInfos.add(this.loadFlowLibrary());

    }

    private LibraryInfo loadFlowLibrary() {
        return null;
    }

    private LibraryInfo loadStandardLibrary() {
        String fileName = "StandardLibrary.spfx";
        String libraryName = "Standard Library";
        return this.loadLibrary(fileName, libraryName, (ProjectDefinition simioProject) -> {
            IntelligentObjectDefinitionFactory.initStandardLibrary(simioProject,
                    EnumSet.allOf(StandardType.class));
        });

    }

    private LibraryInfo loadLibrary(String fileName, String libraryName, Action<ProjectDefinition> createLibAction) {
        return null;
    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Game.class);
        logger.info("Hello World !!");
        ProjectOperator project = new ProjectOperator();
        ProjectManager projectManager = new ProjectManager(project);

        StepDefinitionWrapper.init();

//        var path = "C:\\Users\\Public\\Documents\\Simio\\SimBits\\AnimatePathDensity.spfx";
        var path = "/Users/sunlichao/Downloads/Model.spfx";
        projectManager.loadProject(path, 0, null);

        projectManager.execute();
    }
}
