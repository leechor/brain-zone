package org.licho.brain.concrete.state;

import org.licho.brain.brainApi.IState;
import org.licho.brain.brainApi.IStates;

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
