package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TablesStatesWrapper {

    private StateResults stateResults;

    private IntelligentObjectRunSpace intelligentObject;

    private MayApplication mayApplication;

    private final List<TableStatesElementRunSpace> tableStatesElementRunSpaces;

    public TablesStatesWrapper(StateResults stateResults, IntelligentObjectRunSpace intelligentObjectRunSpace,
                               MayApplication mayApplication) {
        this.stateResults = stateResults;
        this.intelligentObject = intelligentObjectRunSpace;
        this.mayApplication = mayApplication;
        this.tableStatesElementRunSpaces = new ArrayList<>();
        for (var p : this.stateResults.Parent().Data().Rows()) {
            this.method_0();
        }
    }

    StateResults States() {
        return this.stateResults;
    }

    public int method_0() {
        this.tableStatesElementRunSpaces.add((TableStatesElementRunSpace) this.stateResults.Instance().CreateRunSpaceWithPopulation(this.intelligentObject, this.mayApplication));
        return this.tableStatesElementRunSpaces.size() - 1;
    }

    public int getCount() {
        return this.tableStatesElementRunSpaces.size();
    }

    public AbsBaseRunSpace getByIndex(int index) {
        if (index >= 0 && index < this.tableStatesElementRunSpaces.size()) {
            return this.tableStatesElementRunSpaces.get(index);
        }
        return null;
    }

    public int getIndex(AbsBaseRunSpace absBaseRunSpace) {
        return this.tableStatesElementRunSpaces.indexOf((TableStatesElementRunSpace) absBaseRunSpace);
    }

    public void actionAll(Action<AbsBaseRunSpace> action) {
        for (TableStatesElementRunSpace obj : this.tableStatesElementRunSpaces) {
            action.apply(obj);
        }
    }

}
