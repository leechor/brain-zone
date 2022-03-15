package org.licho.brain.concrete;

import cn.hutool.core.io.file.FileNameUtil;
import org.licho.brain.utils.simu.IFilesStreamOperator;
import org.licho.brain.utils.simu.IProjectConfigInstance;

/**
 *
 */
public class ProjectConfig implements IProjectConfigInstance {
    private static IProjectConfigInstance instance = new ProjectConfig();

    public static IProjectConfigInstance Instance() {
        return ProjectConfig.instance;

    }

    @Override
    public IFilesStreamOperator getFileStreamOperator(StringBuffer fileName) {
        IFilesStreamOperator result = null;
        String ext = FileNameUtil.extName(fileName.toString());
        if ("spfx".equals(ext)) {
            String fileType = "Project";
            result = new ZipProject(fileType);
        }
        return result;
    }
}
