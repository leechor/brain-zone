package org.licho.brain.concrete;

import org.licho.brain.concrete.fixed.FixedRunSpace;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
public class TargetResults {
    private Table table;
    private int interactiveVersion = -1;
    private int planResultsVersion = -1;
    private int riskResultsVersion = -1;
    private List<TargetResults.ResultsMapWrapper> resultsMapWrappers = new ArrayList<>();

    public void resetTable() {
        this.table.Data().Rows().ResetBindings();
    }

    public boolean method_7(FixedRunSpace fixedRunSpace, Enum68 enum68, int version) {
        // TODO: 2022/2/6
        return false;
    }

    public void init() {
        for (TargetResults.ResultsMapWrapper resultsMapWrapper : this.resultsMapWrappers) {
            resultsMapWrapper.init();
        }
        this.planResultsVersion = -1;
    }

    public void resetTable(int interactiveVersion, int planResultsVersion, int riskResultsVersion) {
        if (this.getInteractiveVersion() == interactiveVersion || this.gePlanResultsVersion() == planResultsVersion || this.getRiskResultsVersion() == riskResultsVersion) {
            this.table.Data().Rows().ResetBindings();
        }
    }

    private int getRiskResultsVersion() {
        return this.riskResultsVersion;
    }

    private int gePlanResultsVersion() {
        return this.interactiveVersion;
    }

    private int getInteractiveVersion() {
        return this.planResultsVersion;
    }

    public enum Enum68 {
        Zero,
        One
    }

    public static class ResultsMapWrapper {
        public void init() {
            this.resultsMap.entrySet().forEach(entry -> entry.setValue(entry.getValue().initTargetResult()));
        }

        public void update() {
            this.resultsMap.entrySet().forEach(entry -> entry.setValue(entry.getValue().getTargetResult()));
        }

        public final Map<Target, TargetResult> resultsMap = new HashMap<>();
    }
}

