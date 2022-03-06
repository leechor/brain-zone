package com.zdpx.cctpp.example.war;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.concrete.IntelligentObjectDefinitionFactory;
import com.zdpx.cctpp.concrete.LibraryInfo;
import com.zdpx.cctpp.concrete.Project;
import com.zdpx.cctpp.concrete.SimioProject;
import com.zdpx.cctpp.concrete.SimioProjectDefinition;
import com.zdpx.cctpp.concrete.SimioProjectManager;
import com.zdpx.cctpp.concrete.StepDefinitionWrapper;
import com.zdpx.cctpp.enu.StandardType;
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

    private void initStartSimioProjectManager(SimioProjectDefinition simioProjectDefinition) {
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
        return this.loadLibrary(fileName, libraryName, (SimioProject simioProject) -> {
            IntelligentObjectDefinitionFactory.initStandardLibrary(simioProject,
                    EnumSet.allOf(StandardType.class));
        });

    }

    private LibraryInfo loadLibrary(String fileName, String libraryName, Action<SimioProject> createLibAction) {
        return null;
    }

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(Game.class);
        logger.info("Hello World !!");
        Project project = new Project();
        SimioProjectManager projectManager = new SimioProjectManager(project);

        StepDefinitionWrapper.init();

//        var path = "C:\\Users\\Public\\Documents\\Simio\\SimBits\\AnimatePathDensity.spfx";
        var path = "/Users/sunlichao/Downloads/simio/Simio_/SimBits/AnimatePathDensity.spfx";
        projectManager.loadProject(path, 0, null);

        projectManager.execute();
    }
}
