package org.licho.brain.concrete;

/**
 *
 */
public class StateResults {
    private Table parent;
    private TableStates tableStates;
    private int interactiveVersion;
    private int planResultsVersion;

    public Table Parent() {
        return this.parent;
    }

    public TableStates Instance() {
        return this.tableStates;

    }

    public void reset(TablesStatesWrapper tablesStatesWrapper, ActiveModel.RunModel runModel) {
        // TODO: 2022/2/6 
    }

    public void resetTable(int interactiveVersion, int planResultsVersion, int riskResultsVersion) {
	if (this.interactiveVersion == interactiveVersion || this.planResultsVersion == planResultsVersion)
		{
			this.Parent().Data().Rows().ResetBindings();
		}
    }
}
