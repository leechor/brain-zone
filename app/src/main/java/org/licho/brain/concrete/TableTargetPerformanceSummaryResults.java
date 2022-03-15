package org.licho.brain.concrete;

/**
 *
 */
public class TableTargetPerformanceSummaryResults extends BindingList<TableTargetPerformanceSummaryResult> {
	private final ActiveModel activeModel;

	public TableTargetPerformanceSummaryResults(ActiveModel activeModel) {
        this.activeModel = activeModel;
    }

    public void flush() {
        // TODO: 2021/12/17 
    }
}
