package org.licho.brain.concrete;

import org.licho.brain.utils.simu.ILog;
import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public class Project_1 implements IDisposable {
    private final SimioProjectManager simioProjectManager;
    private final ILog log;
    public ProjectDefinition SimioProject = new ProjectDefinition();
	private OperatorRecord operatorRecord;

    public Project_1(SimioProjectManager simioProjectManager, ILog log) {
        this.simioProjectManager = simioProjectManager;
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
        if (this.SimioProject != null) {
            return this.SimioProject.getFileName();
        }
        return null;
    }

    public void setFileName(String name) {
        if (this.SimioProject != null) {
            this.SimioProject.setFileName(name);
        }
    }

    @Override
    public void Dispose() {
        		if (this.SimioProject != null)
		{
			this.SimioProject.Dispose();
			this.SimioProject = null;
		}
		this.method_4();
    }

}
