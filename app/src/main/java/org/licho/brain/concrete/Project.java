package org.licho.brain.concrete;

import org.licho.brain.utils.simu.ILog;
import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public class Project implements IDisposable {
    private final ProjectManager projectManager;
    private final ILog log;
    public ProjectDefinition currentProjectDefinition = new ProjectDefinition();
	private OperatorRecord operatorRecord;

    public Project(ProjectManager projectManager, ILog log) {
        this.projectManager = projectManager;
        this.log = log;
        this.method_3();

    }
	public OperatorRecord getOperatorRecord()
	{
		return this.operatorRecord;
	}

    private void method_3() {
    }


    private void method_4() {
    }

    public String getFileName() {
        if (this.currentProjectDefinition != null) {
            return this.currentProjectDefinition.getFileName();
        }
        return null;
    }

    public void setFileName(String name) {
        if (this.currentProjectDefinition != null) {
            this.currentProjectDefinition.setFileName(name);
        }
    }

    @Override
    public void Dispose() {
        		if (this.currentProjectDefinition != null)
		{
			this.currentProjectDefinition.Dispose();
			this.currentProjectDefinition = null;
		}
		this.method_4();
    }

}
