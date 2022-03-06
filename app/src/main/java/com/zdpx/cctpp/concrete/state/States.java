package com.zdpx.cctpp.concrete.state;

import com.zdpx.cctpp.simuApi.IState;
import com.zdpx.cctpp.simuApi.IStates;

import java.util.Iterator;

/**
 *
 */
public class States implements IStates {
    @Override
    public IState getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IState getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IState> iterator() {
        return null;
    }
}
